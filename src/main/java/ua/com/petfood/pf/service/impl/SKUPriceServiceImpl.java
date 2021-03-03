package ua.com.petfood.pf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.model.SKUPrice;
import ua.com.petfood.pf.repository.SKUPriceRepository;
import ua.com.petfood.pf.service.SKUPriceService;

@Service
public class SKUPriceServiceImpl implements SKUPriceService {

    private final SKUPriceRepository skuPriceRepository;

    @Autowired
    public SKUPriceServiceImpl(final SKUPriceRepository skuPriceRepository) {
        this.skuPriceRepository = skuPriceRepository;
    }

    @Override
    public List<SKUPrice> findSKUItemsWithPricesByAnimalCategory(final Long animalCategoryId) {

        return skuPriceRepository.findSkuPricesByPetCategory(animalCategoryId);
    }
}
