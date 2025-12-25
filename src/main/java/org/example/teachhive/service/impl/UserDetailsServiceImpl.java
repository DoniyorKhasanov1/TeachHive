package org.example.teachhive.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.teachhive.exception.ErrorCodes;
import org.example.teachhive.exception.RestException;
import org.example.teachhive.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @since 2025-12-24
 * @author Doniyor Khasanov (doniyork334@gmail.com)
 * @implNote This class is used to retrieve user by its username*/
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private  UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findByUsername(username)
                .orElseThrow(() -> new RestException("User not found", ErrorCodes.NotFound));
    }
}
