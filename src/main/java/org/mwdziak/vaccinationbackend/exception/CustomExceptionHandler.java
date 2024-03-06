package org.mwdziak.vaccinationbackend.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler({RuntimeException.class})
    public ProblemDetail handleSecurityExceptions(Exception e) {
        ProblemDetail problemDetail;
        if (e instanceof ExpiredJwtException) {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                "Jwt expired"
            );
        } else if (e instanceof UsernameNotFoundException) {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                "User not found"
            );
        } else if (e instanceof BadCredentialsException) {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                "Bad credentials"
            );

        } else if (e instanceof JwtException) {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                "Invalid token"
            );
        } else if (e instanceof TokenBlacklistedException) {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(401),
                "Token blacklisted"
            );
        } else if (e instanceof UserAlreadyExistsException) {
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(409),
                "User already exists"
            );
        } else {
            logger.error("Unhandled exception", e);
            problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(500),
                "Internal server error"
            );
        }

        return problemDetail;
    }
}
