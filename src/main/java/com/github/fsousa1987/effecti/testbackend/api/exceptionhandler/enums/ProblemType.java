package com.github.fsousa1987.effecti.testbackend.api.exceptionhandler.enums;

import lombok.Getter;

@Getter
public enum ProblemType {

    SERVICE_UNAVAILABLE_EXCEPTION ("Service temporarily unavailable");

    private final String title;

    ProblemType(String title) {
        this.title = title;
    }
}
