package ua.com.petfood.pf.service;

import ua.com.petfood.pf.model.novaposhta.BranchData;
import ua.com.petfood.pf.model.novaposhta.CityData;
import ua.com.petfood.pf.model.novaposhta.RegionData;

import java.util.List;
import java.util.Optional;

public interface AddressCatalogService {

    List<RegionData> getRegionsList();

    List<CityData> getRegionCitiesList(String regionCode);

    List<BranchData> getBranchesInCityList(String cityCode);

    BranchData getBranchByID(String branchId);

}
