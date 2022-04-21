package pl.tomaszosuch.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszosuch.springredditclone.model.Post;
import pl.tomaszosuch.springredditclone.model.User;
import pl.tomaszosuch.springredditclone.model.Vote;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
