package org.tpokora.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tpokora.users.dao.UserRepository;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserDetailsImpl;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return Optional.ofNullable(optionalUser).orElseThrow(()->new UsernameNotFoundException("Username Not Found"))
                .map(UserDetailsImpl::new).get();
    }
}
