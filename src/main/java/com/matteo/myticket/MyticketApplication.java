package com.matteo.myticket;

import com.matteo.myticket.model.*;
import com.matteo.myticket.repo.BusinessRepo;
import com.matteo.myticket.repo.ManagerRepo;
import com.matteo.myticket.repo.OrderRepo;
import com.matteo.myticket.repo.UserRepo;
import com.matteo.myticket.service.BusinessService;
import com.matteo.myticket.service.ManagerService;
import com.matteo.myticket.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootApplication
public class MyticketApplication {


    public static void main(String[] args) {
        SpringApplication.run(MyticketApplication.class, args);
    }

}
