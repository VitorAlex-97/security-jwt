package io.getarrays.userservice.service;

import io.getarrays.userservice.models.RoleModel;
import io.getarrays.userservice.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserModel saveUser(UserModel user);
    RoleModel saveRole(RoleModel role);
    void addRoleToUser(String username, String roleName);
    Optional<UserModel> getUser(String username);
    Optional<List<UserModel>>getUsers();
}
