package ru.itis.auctionmarketplace.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itis.auctionmarketplace.dto.request.RegistrationForm;
import ru.itis.auctionmarketplace.exception.AccountAlreadyExistsException;

public interface AuthService{
    void register(RegistrationForm form);
}
