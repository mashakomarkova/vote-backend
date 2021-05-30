package ua.nure.diploma.vote.config.jwt;

import javax.naming.AuthenticationException;

public class JwtAuthentificationException extends AuthenticationException {
    public JwtAuthentificationException(String explanation) {
        super(explanation);
    }

    public JwtAuthentificationException() {
    }
}