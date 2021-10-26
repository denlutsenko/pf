package ua.com.petfood.pf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.petfood.pf.service.impl.NovaPoshtaApiServiceImpl;

@RestController
@CrossOrigin
public class NovaPoshtaApiController {

    private final NovaPoshtaApiServiceImpl novaPoshtaApiServiceImpl;

    @Autowired
    public NovaPoshtaApiController(NovaPoshtaApiServiceImpl novaPoshtaApiServiceImpl) {
        this.novaPoshtaApiServiceImpl = novaPoshtaApiServiceImpl;
    }


    @GetMapping(value = "/admin/regions")
    public ResponseEntity<String> getRegions() {
        return novaPoshtaApiServiceImpl.getRegions();

    }

    @GetMapping(value = "/admin/cities")
    public ResponseEntity<String> getCities() {
        return novaPoshtaApiServiceImpl.getCities();

    }

    @GetMapping(value = "/admin/branches")
    public ResponseEntity<String> getBranches() {
        return novaPoshtaApiServiceImpl.getBranches();

    }
}
