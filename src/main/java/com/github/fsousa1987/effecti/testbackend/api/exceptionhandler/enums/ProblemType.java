package com.github.fsousa1987.effecti.testbackend.api.exceptionhandler.enums;

import lombok.Getter;

@Getter
public enum ProblemType {

    SERVICE_UNAVAILABLE_EXCEPTION();

    private final String title;

    ProblemType() {
        this.title = "Service temporarily unavailable";
    }
}
