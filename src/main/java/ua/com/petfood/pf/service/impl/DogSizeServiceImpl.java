package ua.com.petfood.pf.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.petfood.pf.model.DogSize;
import ua.com.petfood.pf.repository.DogSizeRepository;
import ua.com.petfood.pf.service.DogSizeService;

@Service
public class DogSizeServiceImpl implements DogSizeService {

    private DogSizeRepository dogSizeRepository;

    @Autowired
    public DogSizeServiceImpl(DogSizeRepository dogSizeRepository) {
        this.dogSizeRepository = dogSizeRepository;
    }

    @Override
    public List<DogSize> getDogSizes() {
        return Optional.of(dogSizeRepository.findAll()).orElseGet(Collections::emptyList);
    }
}
