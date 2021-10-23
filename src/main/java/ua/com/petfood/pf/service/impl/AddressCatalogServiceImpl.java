package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.exception.BadRequestException;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.novaposhta.BranchData;
import ua.com.petfood.pf.model.novaposhta.CityData;
import ua.com.petfood.pf.model.novaposhta.RegionData;
import ua.com.petfood.pf.repository.novaposhta.BranchRepository;
import ua.com.petfood.pf.repository.novaposhta.CityRepository;
import ua.com.petfood.pf.repository.novaposhta.RegionRepository;
import ua.com.petfood.pf.service.AddressCatalogService;

import java.util.List;
import java.util.Optional;

@Service
public class AddressCatalogServiceImpl implements AddressCatalogService {

    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public AddressCatalogServiceImpl(RegionRepository regionRepository, CityRepository cityRepository, BranchRepository branchRepository) {
        this.regionRepository = regionRepository;
        this.cityRepository = cityRepository;
        this.branchRepository = branchRepository;
    }

    public List<RegionData> getRegionsList() {
        return regionRepository.findAll();
    }

    public List<CityData> getRegionCitiesList(String regionCode) {
        return cityRepository.findByAreaOrderByDescription(regionCode);
    }

    @Override
    public List<BranchData> getBranchesInCityList(String cityCode) {
        return branchRepository.findByCityRefOrderByDescription(cityCode);
    }


    public BranchData getBranchByID(String branchId) {
        return branchRepository.findById(branchId).orElseThrow(()->new NotFoundException("Branch not found"));
    }
}
