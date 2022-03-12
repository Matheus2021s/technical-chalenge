package br.com.sicredi.technicalchallenge.webclient;

import br.com.sicredi.technicalchallenge.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Getter
@Setter
@Component
public class AbleToVoteClient {

    private static final Logger LOGGER = LogManager.getLogger(AbleToVoteClient.class.getName());

    private static final String ENDPOINT_URI ="https://user-info.herokuapp.com/users/";

    private String cpf;


    public IsAbleToVoteHerokuResponse isAbleToVote(String cpf){
        if (!cpf.isEmpty()){
            setCpf(cpf);
            return isAbleToVote();
        }
        throw new IllegalArgumentException();
    }

    private IsAbleToVoteHerokuResponse isAbleToVote()  {

        try {

            RestTemplate restTemplate =  new RestTemplateBuilder().build();

            IsAbleToVoteHerokuResponse response = getFromClient( restTemplate );

            LOGGER.info( "Response : "+ response.getStatus() );

            return response;

        } catch ( Exception e ) {

         LOGGER.error(e);

        }
        return null;
    }


    private IsAbleToVoteHerokuResponse getFromClient(RestTemplate restTemplate ) {
        return  restTemplate.getForObject( getEndpointUri() , IsAbleToVoteHerokuResponse.class );
    }

    private String getEndpointUri() {
        String endpoint = UriComponentsBuilder.fromUri(URI.create(ENDPOINT_URI + StringUtil.removeSpecialCaracters(getCpf()))).toUriString();

        LOGGER.info( "Endpoint request: " + endpoint );

        return endpoint;
    }


}
