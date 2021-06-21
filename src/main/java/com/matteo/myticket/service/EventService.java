package com.matteo.myticket.service;

import com.matteo.myticket.dto.BusinessInfoViewDTO;
import com.matteo.myticket.model.Business;
import com.matteo.myticket.model.Event;
import com.matteo.myticket.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements CRUDService<Event> {

    @Autowired
    EventRepo eventRepo;


    @Override
    public Event add(Event event) {
        return eventRepo.save(event);
    }

    @Override
    public List<Event> findAll() {
        return eventRepo.findAll();
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return eventRepo.findById(id);
    }

    public List<Event> findAllByBusiness(Integer id){
        return eventRepo.findAllByBusiness_Id(id);
    }

    public void deleteById(Integer id){
        eventRepo.deleteById(id);
    }

    public List<Event> findUpComing(){
        return eventRepo.findTop5ByOrderByDateAsc();
    }

    public BusinessInfoViewDTO businessInfoViewDTO(Integer id){
        Event event = eventRepo.getById(id);
        Business business = event.getBusiness();
        BusinessInfoViewDTO dto = new BusinessInfoViewDTO();
        dto.setName(business.getName());
        dto.setDescription(business.getDescription());
        return dto;
    }
}
