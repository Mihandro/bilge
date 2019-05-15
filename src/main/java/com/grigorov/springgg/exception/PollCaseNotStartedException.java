package com.grigorov.springgg.exception;

public class PollCaseNotStartedException extends RuntimeException {
    public PollCaseNotStartedException(){
        super("The poll case has not been started");
    }
}
