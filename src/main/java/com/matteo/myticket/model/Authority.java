package com.matteo.myticket.model;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public enum Authority {
    MANAGER,
    USER;
}
