package ru.itis.auctionmarketplace.security.userdetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.auctionmarketplace.model.AccountEntity;
import ru.itis.auctionmarketplace.repository.AccountRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BaseUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Неверное имя или пароль"));

        List<GrantedAuthority> roles = List.of(new SimpleGrantedAuthority("ROLE_" + account.getCommunityRoleType().name()));
        log.info("roles: {}", roles);
        return UserDetailsImpl.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(roles)
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .build();
    }


}
