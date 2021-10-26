package ua.com.petfood.pf.service;

import org.springframework.http.ResponseEntity;

public interface NovaPoshtaApiService {
    ResponseEntity<String> getRegion();

    ResponseEntity<String> getCities();

    ResponseEntity<String> getBranches();

}
