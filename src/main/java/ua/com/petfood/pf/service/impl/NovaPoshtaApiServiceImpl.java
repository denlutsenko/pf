package ua.com.petfood.pf.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.com.petfood.pf.model.dto.novaposhta.*;
import ua.com.petfood.pf.model.novaposhta.BranchData;
import ua.com.petfood.pf.model.novaposhta.CityData;
import ua.com.petfood.pf.model.novaposhta.RegionData;
import ua.com.petfood.pf.repository.novaposhta.BranchRepository;
import ua.com.petfood.pf.repository.novaposhta.CityRepository;
import ua.com.petfood.pf.repository.novaposhta.RegionRepository;

import java.io.IOException;
import java.net.InetSocketAddress;

@Component
public class NovaPoshtaApiServiceImpl {
    @Value("${api.novaposhta.uri}")
    private String URI;
    @Value("${api.novaposhta.hostname}")
    private String HOSTNAME;
    @Value("${api.novaposhta.port}")
    private int PORT;
    @Value("${api.novaposhta.regions_body}")
    private String REGIONS_BODY;
    @Value("${api.novaposhta.cities_body}")
    private String CITIES_BODY;
    @Value("${api.novaposhta.branches_body}")
    private String BRANCHES_BODY;
    private final ObjectMapper objectMapper;
    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;
    private final BranchRepository branchRepository;


    @Autowired
    public NovaPoshtaApiServiceImpl(ObjectMapper objectMapper, RegionRepository regionRepository,
                                    CityRepository cityRepository, BranchRepository branchRepository) {
        this.objectMapper = objectMapper;
        this.regionRepository = regionRepository;
        this.cityRepository = cityRepository;
        this.branchRepository = branchRepository;
    }


    public ResponseEntity<String> getRegions() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(REGIONS_BODY, getHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(URI, request, String.class);
        try {
            RegionDTO regions = objectMapper.readValue(response.getBody(), RegionDTO.class);

            regions.getRegionDataLoad().stream()
                    .map(this::mapRegionDTOToRegionEntity)
                    .forEach(regionRepository::save);

        } catch (IOException e) {
            e.printStackTrace();
            //TODO find the right exception and way to log it
        }
        return response;
    }

    public ResponseEntity<String> getCities() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(CITIES_BODY, getHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(URI, request, String.class);
        try {
            CitiesDTO cities = objectMapper.readValue(response.getBody(), CitiesDTO.class);

            cities.getCityDataLoad().stream()
                    .map(this::mapCitiesDTOToCityEntity)
                    .forEach(cityRepository::save);

        } catch (IOException e) {
            e.printStackTrace();
            //TODO find the right exception and way to log it
        }
        return response;
    }


    public ResponseEntity<String> getBranches() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(BRANCHES_BODY, getHeaders());
        ResponseEntity<String> response = restTemplate.postForEntity(URI, request, String.class);
        try {
            BranchDTO branches = objectMapper.readValue(response.getBody(), BranchDTO.class);

            branches.getBranchDataLoad().stream()
                    .map(this::mapBranchDTOToBranchEntity)
                    .forEach(branchRepository::save);

        } catch (IOException e) {
            e.printStackTrace();
            //TODO find the right exception and way to log it
        }
        return response;
    }


    private RegionData mapRegionDTOToRegionEntity(RegionDataLoad regionDataLoad) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(regionDataLoad, RegionData.class);
    }

    private CityData mapCitiesDTOToCityEntity(CityDataLoad cityDataLoad) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cityDataLoad, CityData.class);
    }

    private BranchData mapBranchDTOToBranchEntity(BranchDataLoad branchDataLoad) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(branchDataLoad, BranchData.class);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentLength(145);
        httpHeaders.setConnection("keep-alive");
        httpHeaders.setHost(new InetSocketAddress(HOSTNAME, PORT));
        return httpHeaders;
    }

}
