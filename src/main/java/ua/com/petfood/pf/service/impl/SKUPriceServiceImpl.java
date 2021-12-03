package ua.com.petfood.pf.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.exception.NotFoundException;
import ua.com.petfood.pf.model.SKUPrice;
import ua.com.petfood.pf.model.dto.OrderSKUItems;
import ua.com.petfood.pf.repository.SKUPriceRepository;
import ua.com.petfood.pf.service.SKUPriceService;

@Service
public class SKUPriceServiceImpl implements SKUPriceService {

    private final SKUPriceRepository skuPriceRepository;

    @Autowired
    public SKUPriceServiceImpl(SKUPriceRepository skuPriceRepository) {
        this.skuPriceRepository = skuPriceRepository;
    }

    @Override
    public BigDecimal getPriceForSingleItem(Long id) {
        return skuPriceRepository.findById(id).orElseThrow().getSellingPrice();
    }

    public List<SKUPrice> getPriceForMultipleItems(List<Long> ids) {
        return skuPriceRepository.findAllById(ids);
    }

    public BigDecimal calculateTotalOrderPrice(List<OrderSKUItems> orderSKUItems) {
        List<SKUPrice> skuPrices = getPriceForMultipleItems(
                orderSKUItems.stream().map(OrderSKUItems::getSkuItemId).collect(Collectors.toList()));
        BigDecimal totalPrice = new BigDecimal(0.00);

        for(OrderSKUItems dto : orderSKUItems) {
            Long skuItemId = dto.getSkuItemId();
            int quantity = dto.getQuantity();
            totalPrice = totalPrice.add(skuPrices.stream()
                    .filter(p -> p.getSkuItem().getId() != null && p.getSkuItem().getId().equals(skuItemId))
                    .map(p -> p.getSellingPrice().multiply(new BigDecimal(quantity))).findFirst()
                    .orElseGet(() -> new BigDecimal(0.00)));
        }

        return totalPrice;
    }

    public List<SKUPrice> findSKUItemsWithPricesByAnimalCategory(final Long animalCategoryId) {

        return skuPriceRepository.findSkuPricesByPetCategory(animalCategoryId);
    }

    public SKUPrice getSkuPriceItemBySkuId(final Long id) {
        SKUPrice skuPriceBySkuId = skuPriceRepository.findSkuPriceBySkuId(id);
        if(skuPriceBySkuId != null) {
            return skuPriceBySkuId;
        } else {
            throw new NotFoundException("Sku item not found");
        }
    }
}
