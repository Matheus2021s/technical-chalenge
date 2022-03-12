package br.com.sicredi.technicalchallenge.config.validation;

import br.com.sicredi.technicalchallenge.exception.AssociadoHabilitiadoAVotarException;
import br.com.sicredi.technicalchallenge.exception.AssociadoNaoEncontradoException;
import br.com.sicredi.technicalchallenge.exception.SessaoDevotacaoInexitenteException;
import br.com.sicredi.technicalchallenge.exception.SessaoJaVotadaException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorValidationHandler {

    final MessageSource messageSource;

    public ErrorValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public List<ErrorDto> handle(MethodArgumentNotValidException e){

        List<ErrorDto> list  = new ArrayList<>();

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {

          String message = this.messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

          ErrorDto  errorDto = new ErrorDto(fieldError.getField(), message );

          list.add( errorDto ) ;

        }

        return list;
    }



    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            AssociadoNaoEncontradoException.class,
            SessaoJaVotadaException.class,
            AssociadoNaoEncontradoException.class,
            SessaoDevotacaoInexitenteException.class
        })
    public ErrorResumedDto handleCustomExceptions(RuntimeException e){
        String httpStatus = HttpStatus.BAD_REQUEST.value() + " - " + HttpStatus.BAD_REQUEST.getReasonPhrase();
        return new ErrorResumedDto( httpStatus , e.getMessage() );
    }
}
