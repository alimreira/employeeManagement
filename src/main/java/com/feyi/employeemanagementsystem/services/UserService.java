package com.feyi.employeemanagementsystem.services;

import com.feyi.employeemanagementsystem.dto.UserRegistrationDto;
import com.feyi.employeemanagementsystem.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    Optional<User> getUser(Long id);
    User getUserInfo();
}
