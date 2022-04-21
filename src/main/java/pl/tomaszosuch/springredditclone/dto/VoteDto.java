package pl.tomaszosuch.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.tomaszosuch.springredditclone.model.VoteType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {

    private VoteType voteType;
}
