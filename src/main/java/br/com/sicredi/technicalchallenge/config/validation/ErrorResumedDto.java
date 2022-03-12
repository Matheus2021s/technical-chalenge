package br.com.sicredi.technicalchallenge.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResumedDto {
    private String httpStatus;
    private String error;
}
