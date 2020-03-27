package orion.zenite.controllers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import orion.zenite.exceptions.ApiErros;

// CLASSE PARA DAR UMA RESPONSTA FORMATADA PARA OS ERROS INTERNO

@RestControllerAdvice

public class HandleExceptionController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleSqlException(ConstraintViolationException ex){
        String erro = ex.getCause().toString();
        String mensagem = ex.getCause().toString().split(":")[1];

        boolean duplicado = erro.contains("UNIQUE KEY");
        boolean vazio = erro.contains("NULL");

        if(duplicado){
            mensagem = "Inserção de valores já existentes na base de dados.";
        }

        if (vazio) {
            mensagem = "Campos vazios. Por favor preencha corretamente campo " +
                erro.substring(erro.indexOf("'"), erro.lastIndexOf(","));

        }

        return new ApiErros(mensagem);
    }
}
