package pl.tomaszosuch.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.tomaszosuch.springredditclone.model.Comment;
import pl.tomaszosuch.springredditclone.model.Post;
import pl.tomaszosuch.springredditclone.model.User;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAll();
    List<Comment> findByPost(Post post);
    List<Comment> findAllByUser(User user);
    Optional<Comment> findById(Long id);
    Comment save(Comment comment);
    void deleteById(Long id);
}
