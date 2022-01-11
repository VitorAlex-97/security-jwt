package io.getarrays.userservice.repositories;

import io.getarrays.userservice.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    public Optional<RoleModel> findByName(String name);
}
