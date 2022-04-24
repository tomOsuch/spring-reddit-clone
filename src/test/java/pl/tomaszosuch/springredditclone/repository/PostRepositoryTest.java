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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private SubredditRepository subredditRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testPostSaveAndFindAll() {
        //Given
        User user = new User(1L, "test user", "test password", "test@test.pl", Instant.now(), true);
        userRepository.save(user);
        Subreddit subreddit = new Subreddit();
        subredditRepository.save(subreddit);
        Post post = new Post(1L, "test post", "www.tet.pl", "test", 0, Instant.now(), user, subreddit);
        postRepository.save(post);
        //When
        List<Post> resultFindAll = postRepository.findAll();
        //Then
        assertEquals(1, resultFindAll.size());
        //CleanUp
        postRepository.deleteAll();
    }

    @Test
    public void testPostFindAllBySubreddit() {
        //Given
        User user = new User(1L, "test user", "test password", "test@test.pl", Instant.now(), true);
        userRepository.save(user);
        Subreddit subreddit = new Subreddit();
        subredditRepository.save(subreddit);
        Post post = new Post(1L, "test post", "www.tet.pl", "test", 0, Instant.now(), user, subreddit);
        postRepository.save(post);
        //When
        List<Post> resultFind = postRepository.findAllBySubreddit(subreddit);
        //Then
        assertEquals(1, resultFind.size());
        //CleanUp
        postRepository.deleteAll();
    }

    @Test
    public void testPostFindByUser() {
        //Given
        User user = new User(1L, "test user", "test password", "test@test.pl", Instant.now(), true);
        userRepository.save(user);
        Subreddit subreddit = new Subreddit();
        subredditRepository.save(subreddit);
        Post post1 = new Post(1L, "test post", "www.tet.pl", "test", 0, Instant.now(), user, subreddit);
        postRepository.save(post1);
        //When
        List<Post> resultFind = postRepository.findByUser(user);
        //Then
        assertEquals(1, resultFind.size());
        //CleanUp
        postRepository.deleteAll();
    }

    @Test
    public void testPostDeleteById() {
        //Given
        Post post1 = new Post(1L, "test post", "www.tet.pl", "test", 0, Instant.now(), null, null);
        postRepository.save(post1);
        Long postId = post1.getPostId();
        //When
        postRepository.deleteByPostId(postId);
        Optional<Post> resultDelete = postRepository.findById(postId);
        //Then
        assertFalse(resultDelete.isPresent());
    }
}
