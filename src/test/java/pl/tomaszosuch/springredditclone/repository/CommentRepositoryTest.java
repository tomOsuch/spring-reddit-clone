package pl.tomaszosuch.springredditclone.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.springredditclone.model.Comment;
import pl.tomaszosuch.springredditclone.model.Post;
import pl.tomaszosuch.springredditclone.model.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void testCommentSaveAndFindAll() {
        //Given
        Comment comment = new Comment(1L, "test comment", null, Instant.now(), null);
        commentRepository.save(comment);
        //When
        List<Comment> resultSaveAndFindAll = commentRepository.findAll();
        //Then
        assertEquals(1, resultSaveAndFindAll.size());
        //ClenUp
        commentRepository.deleteAll();;
    }

    @Test
    public void testCommentFindByPost() {
        //Given
        User user = new User(1L, "test user", "test password", "test@test.pl", Instant.now(), true);
        userRepository.save(user);
        Post post = new Post(1L, "test post", "www.tet.pl", "test", 0, Instant.now(), user, null);
        postRepository.save(post);
        Comment comment = new Comment(1L, "test comment", post, Instant.now(), user);
        commentRepository.save(comment);
        //When
        List<Comment> resultFindByPost = commentRepository.findByPost(post);
        //Then
        assertEquals(1, resultFindByPost.size());
        //CleanUp
        commentRepository.deleteAll();
    }

    @Test
    public void testCommentFindAllByUser() {
        //Given
        User user = new User(1L, "test user", "test password", "test@test.pl", Instant.now(), true);
        userRepository.save(user);
        Post post = new Post(1L, "test post", "www.tet.pl", "test", 0, Instant.now(), user, null);
        postRepository.save(post);
        Comment comment = new Comment(1L, "test comment", post, Instant.now(), user);
        commentRepository.save(comment);
        //When
        List<Comment> resultFindAllByUser = commentRepository.findAllByUser(user);
        //Then
        assertEquals(1, resultFindAllByUser.size());
        //CleanUp
        commentRepository.deleteAll();
    }

    @Test
    public void testCommentDeleteById() {
        //Given
        Comment comment = new Comment(1L, "test comment", null, Instant.now(), null);
        commentRepository.save(comment);
        Long id = comment.getId();
        //When
        commentRepository.deleteById(id);
        Optional<Comment> resultDeleteById = commentRepository.findById(id);
        //Then
        assertFalse(resultDeleteById.isPresent());
    }
}
