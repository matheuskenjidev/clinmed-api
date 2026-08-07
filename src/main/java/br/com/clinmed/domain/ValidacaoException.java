package br.com.clinmed.domain;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
