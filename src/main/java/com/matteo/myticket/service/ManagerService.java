package com.matteo.myticket.service;

import com.matteo.myticket.model.Business;
import com.matteo.myticket.model.Manager;
import com.matteo.myticket.model.User;
import com.matteo.myticket.repo.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService implements CRUDService<Manager>{

    @Autowired
    ManagerRepo managerRepo;

    @Override
    public Manager add(Manager manager) {
        return managerRepo.save(manager);
    }

    @Override
    public List<Manager> findAll() {
        return managerRepo.findAll();
    }

    @Override
    public Optional<Manager> findById(Integer id) {
        return managerRepo.findById(id);
    }


}
