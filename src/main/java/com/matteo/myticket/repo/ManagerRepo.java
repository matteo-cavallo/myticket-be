package com.matteo.myticket.repo;

import com.matteo.myticket.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepo extends JpaRepository<Manager,Integer> {
}
