package br.edu.ifs.cybertraining.infra.exceptions;

public class RegraDeNegocioException extends RuntimeException {
    public RegraDeNegocioException(String messange){
        super(messange);
    }
}
