package pl.tomaszosuch.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.tomaszosuch.springredditclone.model.Subreddit;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    List<Subreddit> findAll();
    Optional<Subreddit> findByName(String subredditName);
    Optional<Subreddit> findById(Long id);
    Subreddit save(Subreddit subreddit);
    void deleteById(Long id);
}
