package pt.iade.AquaPoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.iade.AquaPoint.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}