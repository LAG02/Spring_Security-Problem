package webshop.backend.repo;

import webshop.backend.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}