package io.getarrays.userservice.service;

import io.getarrays.userservice.models.RoleModel;
import io.getarrays.userservice.models.UserModel;
import io.getarrays.userservice.repositories.RoleRepository;
import io.getarrays.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Override
    public UserModel saveUser(UserModel user) {
        log.info("Saving new user {} into the database", user.getName());
        return userRepository.save(user);
    }

    @Override
    public RoleModel saveRole(RoleModel role) {
        log.info("Saving new role {} into the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        UserModel userFound = new UserModel();
        RoleModel roleFound = new RoleModel();
        Optional<UserModel> userOptional = userRepository.findByUsername(username);
        Optional<RoleModel> roleOptional = roleRepository.findByName(roleName);

        if (userOptional.isPresent() && roleOptional.isPresent()){
            userFound = userOptional.get();
            roleFound = roleOptional.get();

            userFound.getRoles().add(roleFound);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User or Role not found");
        }

    }

    @Override
    public Optional<UserModel> getUser(String username) {
        log.info("Fetching user {}", username);
        userRepository.findByUsername(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"User do not exists")
        );
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<List<UserModel>> getUsers() {
        log.info("Fetching all users");

        return Optional.of(userRepository.findAll());
    }
}
