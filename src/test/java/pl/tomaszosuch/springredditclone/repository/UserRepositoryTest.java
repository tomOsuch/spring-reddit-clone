package pl.tomaszosuch.springredditclone.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.springredditclone.model.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindAllUser() {
        //Given
        User user = new User(1L, "test user", "test password", "test@test.pl", Instant.now(), true);
        userRepository.save(user);
        //When
        List<User> users = userRepository.findAll();
        //Then
        assertEquals(1, users.size());
        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testFindByUserId() {
        //Given
        User user = new User(1L, "test user", "test password", "test@test.pl", Instant.now(), true);
        userRepository.save(user);
        Long id = user.getUserId();
        //When
        Optional<User> resultFindById = userRepository.findById(id);
        //Then
        assertTrue(resultFindById.isPresent());
        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUserDeleteById() {
        //Given
        User user = new User(1L, "test user", "test password", "test@test.pl", Instant.now(), true);
        userRepository.save(user);
        Long id = user.getUserId();
        //When
        userRepository.deleteByUserId(id);
        Optional<User> resultDeleteById = userRepository.findById(id);
        //Then
        assertFalse(resultDeleteById.isPresent());
    }
}
