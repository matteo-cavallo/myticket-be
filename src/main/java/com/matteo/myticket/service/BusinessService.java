package com.matteo.myticket.service;

import com.matteo.myticket.model.Business;
import com.matteo.myticket.repo.BusinessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService implements CRUDService<Business> {

    @Autowired
    BusinessRepo businessRepo;

    @Override
    public Business add(Business business) {
        return businessRepo.save(business);
    }

    @Override
    public List<Business> findAll() {
        return businessRepo.findAll();
    }

    @Override
    public Optional<Business> findById(Integer id) {
        return businessRepo.findById(id);
    }
}
