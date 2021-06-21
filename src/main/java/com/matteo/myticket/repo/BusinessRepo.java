package com.matteo.myticket.repo;

import com.matteo.myticket.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepo extends JpaRepository<Business,Integer> {
}
