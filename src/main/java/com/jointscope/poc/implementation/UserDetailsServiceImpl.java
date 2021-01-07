package com.jointscope.poc.implementation;

import com.jointscope.poc.model.Employee;
import com.jointscope.poc.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin")) {
            return new org.springframework.security.core.userdetails.User("admin",
                    "$2y$12$hB78DWrzbbs3s7lkzI9U5eKXN7sCLwPOnfs7u8ufQcZr3QUhKTkRm", Collections.emptyList());
        }
        Employee employee = employeeRepository.findById(Integer.parseInt(username)).orElse(null);

        if (employee == null) {
            throw new UsernameNotFoundException(username);
        }

        logger.info(employee.getPassword());
        return new org.springframework.security.core.userdetails.User(employee.getId() + "", employee.getPassword(),
                Collections.emptyList());
    }
}
