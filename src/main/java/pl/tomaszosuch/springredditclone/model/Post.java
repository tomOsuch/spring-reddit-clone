package pl.tomaszosuch.springredditclone.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String postName;
    private String url;
    private String description;
    private Integer voteCount = 0;
    private Instant createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "userId",
            referencedColumnName = "userId"
    )
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id",
            referencedColumnName = "id"
    )
    private Subreddit subreddit;

    @OneToMany(
            targetEntity = Vote.class,
            mappedBy = "post",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Vote> votes;

    @OneToMany(
            targetEntity = Comment.class,
            mappedBy = "post",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Comment> comments;

    public Post(Long postId, String postName, String url, String description, Integer voteCount, Instant createdDate, User user, Subreddit subreddit) {
        this.postId = postId;
        this.postName = postName;
        this.url = url;
        this.description = description;
        this.voteCount = voteCount;
        this.createdDate = createdDate;
        this.user = user;
        this.subreddit = subreddit;
    }
}
