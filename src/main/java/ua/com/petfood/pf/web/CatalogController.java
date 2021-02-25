package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.petfood.pf.service.SKUItemService;

@RestController
@RequestMapping(value = "/anon")
public class CatalogController {

    private SKUItemService skuItemService;

    @Autowired
    public CatalogController(SKUItemService skuItemService) {
        this.skuItemService = skuItemService;
    }

    @GetMapping(value = "/catalog/get/{id}")
    ResponseEntity getLineItemsForAnimalCategoryById(@PathVariable(name = "id") Long animalCategoryId) {
        return ResponseEntity.ok(skuItemService.getSKUItemsByAnimalCategoryID(animalCategoryId));
    }
}
