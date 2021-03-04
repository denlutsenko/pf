package ua.com.petfood.pf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.petfood.pf.model.SKUPrice;
import ua.com.petfood.pf.model.dto.OrderSKUItemDTO;
import ua.com.petfood.pf.repository.SKUPriceRepository;
import ua.com.petfood.pf.service.SKUPriceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SKUPriceServiceImpl implements SKUPriceService {

    private final SKUPriceRepository skuPriceRepository;

    @Autowired
    public SKUPriceServiceImpl(final SKUPriceRepository skuPriceRepository) {
        this.skuPriceRepository = skuPriceRepository;
    }

    @Override
    public BigDecimal getPriceForSingleItem(Long id) {
        return skuPriceRepository
                .findById(id)
                .orElseThrow()
                .getSellingPrice();
    }

    public List<SKUPrice> getPriceForMultipleItems(List<Long> ids) {
        return skuPriceRepository.findAllById(ids);
    }

    public BigDecimal calculateTotalOrderPrice(List<OrderSKUItemDTO> dtos) {
        List<SKUPrice> skuPrices = getPriceForMultipleItems(dtos
                .stream()
                .map(OrderSKUItemDTO::getSkuItemId)
                .collect(Collectors.toList()));
        BigDecimal totalPrice = new BigDecimal("0.0");

        for (OrderSKUItemDTO dto : dtos) {
            Long skuItemId = dto.getSkuItemId();
            int quantity = dto.getQuantity();
            totalPrice = totalPrice.add(skuPrices.stream()
                    .filter(p -> p.getSkuItem().getId() != null && p.getSkuItem().getId().equals(skuItemId))
                    .map(p -> p.getSellingPrice().multiply(new BigDecimal(quantity)))
                    .findFirst().orElseGet(() -> new BigDecimal("0.0")));
        }
        return totalPrice;
    }

    public List<SKUPrice> findSKUItemsWithPricesByAnimalCategory(final Long animalCategoryId) {

        return skuPriceRepository.findSkuPricesByPetCategory(animalCategoryId);
    }
}
