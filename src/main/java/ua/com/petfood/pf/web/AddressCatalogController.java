package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.novaposhta.BranchData;
import ua.com.petfood.pf.model.novaposhta.CityData;
import ua.com.petfood.pf.model.novaposhta.RegionData;
import ua.com.petfood.pf.service.AddressCatalogService;

import java.util.List;
@RestController
@CrossOrigin
public class AddressCatalogController {

    private final AddressCatalogService addressCatalogService;

    @Autowired
    public AddressCatalogController(AddressCatalogService addressCatalogService) {
        this.addressCatalogService = addressCatalogService;
    }

    @GetMapping(value = "/api/regions")
    public ResponseEntity<List<RegionData>> getRegions() {
        return ResponseEntity.ok(addressCatalogService.getRegionsList());

    }

    @GetMapping(value = "/api/cities_by_region")
    public ResponseEntity<List<CityData>> getCitiesByRegionCode(@RequestBody String regionCode) {
        return ResponseEntity.ok(addressCatalogService.getRegionCitiesList(regionCode));

    }

    @GetMapping(value = "/api/branches_by_city")
    public ResponseEntity<List<BranchData>> getBranchesByCityCode(@RequestBody String cityCode) {
        return ResponseEntity.ok(addressCatalogService.getBranchesInCityList(cityCode));

    }

    @GetMapping(value = "/api/branch_by_code")
    public ResponseEntity<BranchData> getBranchByBranchCode(@RequestBody String branchCode) {
        return ResponseEntity.ok(addressCatalogService.getBranchByID(branchCode));

    }
}
