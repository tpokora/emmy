package org.tpokora.users.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tpokora.common.services.PasswordEncoderGenerator;
import org.tpokora.users.dao.RolesRepository;
import org.tpokora.users.dao.UserRepository;
import org.tpokora.users.model.Role;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserDetailsImpl;

import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private RolesRepository rolesRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return Optional.ofNullable(optionalUser).orElseThrow(()->new UsernameNotFoundException("Username Not Found"))
                .map(UserDetailsImpl::new).get();
    }

    public List<User> getAllUsers() {
        List<User> userList = this.userRepository.findAll();
        return userList;
    }

    @Transactional
    public User saveUser(User newUser, @NotNull String roleName) {
        newUser.setPassword(PasswordEncoderGenerator.passwordEncoder(newUser.getPassword()));
        newUser = userRepository.saveAndFlush(newUser);
        Role role = this.rolesRepository.findByName(roleName).get();
        newUser.setRoles(Stream.of(role).collect(Collectors.toCollection(HashSet::new)));
        newUser = userRepository.saveAndFlush(newUser);
        return newUser;
    }
}
