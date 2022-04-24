package pl.tomaszosuch.springredditclone.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.springredditclone.model.Post;
import pl.tomaszosuch.springredditclone.model.Subreddit;
import pl.tomaszosuch.springredditclone.model.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SubredditRepositoryTest {

    @Autowired
    private SubredditRepository subredditRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void testSubredditSaveAndFindAll() {
        //Given
        User user = new User(1L, "test user", "test password", "test@test.pl", Instant.now(), true);
        userRepository.save(user);
        Post post = new Post(1L, "test post", "www.tet.pl", "test", 0, Instant.now(), user, null);
        postRepository.save(post);
        Subreddit subreddit = new Subreddit(1L, "test name", "test description", List.of(post), Instant.now(), user);
        subredditRepository.save(subreddit);
        //When
        List<Subreddit> resultFindAll = subredditRepository.findAll();
        //Then
        assertEquals(1, resultFindAll.size());
        //CleanUp
        subredditRepository.deleteAll();
    }

    @Test
    public void testSubredditFindByName() {
        //Given
        User user = new User(1L, "test user", "test password", "test@test.pl", Instant.now(), true);
        userRepository.save(user);
        Post post = new Post(1L, "test post", "www.tet.pl", "test", 0, Instant.now(), user, null);
        postRepository.save(post);
        Subreddit subreddit = new Subreddit(1L, "test name", "test description", List.of(post), Instant.now(), user);
        subredditRepository.save(subreddit);
        //When
        Optional<Subreddit> resultFindByName = subredditRepository.findByName(subreddit.getName());
        //Then
        assertTrue(resultFindByName.isPresent());
        //CleanUp
        subredditRepository.deleteAll();
    }

    @Test
    public void testSubredditDeleteById() {
        //Given
        Subreddit subreddit = new Subreddit(1L, "test name", "test description", null, Instant.now(), null);
        subredditRepository.save(subreddit);
        Long id = subreddit.getId();
        //When
        subredditRepository.deleteById(id);
        Optional<Subreddit> resultDeleteById = subredditRepository.findById(id);
        //Then
        assertFalse(resultDeleteById.isPresent());
    }
}
