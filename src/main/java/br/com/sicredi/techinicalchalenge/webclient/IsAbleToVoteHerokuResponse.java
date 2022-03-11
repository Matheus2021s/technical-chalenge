package br.com.sicredi.techinicalchalenge.webclient;

import br.com.sicredi.techinicalchalenge.model.enums.StatusIsAbleToVote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IsAbleToVoteHerokuResponse {

    private StatusIsAbleToVote status;
}
