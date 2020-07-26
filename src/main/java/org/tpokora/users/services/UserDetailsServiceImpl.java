package org.tpokora.users.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tpokora.common.services.PasswordEncoderGenerator;
import org.tpokora.users.dao.RolesRepository;
import org.tpokora.users.dao.UserRepository;
import org.tpokora.users.model.Role;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserDetailsImpl;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private UserRepository userRepository;
    private RolesRepository rolesRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    public User getUserById(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(UserDetailsImpl::new).orElseGet(() -> {
            LOGGER.debug(">>> User Not Found");
            return null;
        });
    }

    public UserDetails loadUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.map(UserDetailsImpl::new).orElseGet(() -> {
            LOGGER.debug(">>> User Not Found");
            return null;
        });
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

    public void updateUsername(int id, String newUsername) {
        userRepository.updateUsername(id, newUsername);
    }

    public void updateEmail(int id, String newEmail) {
        userRepository.updateEmail(id, newEmail);
    }

    public void updatePassword(int id, String newPassword) {
        userRepository.updatePassword(id, newPassword);
    }

    public List<Role> getAllRoles() {
        List<Role> roleList = this.rolesRepository.findAll();
        return roleList;
    }

    public Role getRole(String name) {
        Optional<Role> role = this.rolesRepository.findByName(name);
        return  Optional.ofNullable(role).orElseThrow(()->new EntityNotFoundException("Role Not Found"))
                .map(Role::new).get();
    }

    public Role createRole(Role role) {
        return this.rolesRepository.save(role);
    }
}
