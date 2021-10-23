package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.petfood.pf.external.novaposhta.NovaPoshtaApiService;

@RestController
@CrossOrigin
public class NovaPoshtaApiController {

    private final NovaPoshtaApiService novaPoshtaApiService;

    @Autowired
    public NovaPoshtaApiController(NovaPoshtaApiService novaPoshtaApiService) {
        this.novaPoshtaApiService = novaPoshtaApiService;
    }


    @GetMapping(value = "/admin/regions")
    public ResponseEntity<String> getRegions() {
        return novaPoshtaApiService.getRegions();

    }

    @GetMapping(value = "/admin/cities")
    public ResponseEntity<String> getCities() {
        return novaPoshtaApiService.getCities();

    }

    @GetMapping(value = "/admin/branches")
    public ResponseEntity<String> getBranches() {
        return novaPoshtaApiService.getBranches();

    }
}
