package pl.tomaszosuch.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszosuch.springredditclone.model.Post;
import pl.tomaszosuch.springredditclone.model.Subreddit;
import pl.tomaszosuch.springredditclone.model.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAll();
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);
    Post save(Post post);
    void deleteByPostId(Long postId);
}
