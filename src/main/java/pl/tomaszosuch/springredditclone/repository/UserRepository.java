package pl.tomaszosuch.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszosuch.springredditclone.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    Optional<User> findByUsername(String username);
    User save(User user);
    void deleteByUserId(Long userId);
}
