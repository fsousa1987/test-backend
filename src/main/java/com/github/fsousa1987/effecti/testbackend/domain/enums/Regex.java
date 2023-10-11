package com.github.fsousa1987.effecti.testbackend.domain.enums;

import lombok.Getter;

@Getter
public enum Regex {

    REGEX_AGENCY_IDENTIFICATION("(.*?)(?=C�digo)"),
    UASG_REGEX_CODE("\\bUASG:\\s+\\d+\\b"),
    REGEX_BIDDING_TYPE("\\bUASG:\\s+\\d+\\b(.+?)(?= Objeto|$)"),
    REGEX_OBJECT_TYPE("Objeto:(.+?)(?= Edital|$)"),
    REGEX_NOTICE_DATE("Edital a partir de:(.+?)(?= Endere�o|$)"),
    REGEX_ADDRESS("Endere�o:(.+?)(?= Telefone|$)"),
    CONTACT_PHONE_REGEX("Telefone: (\\(0xx\\d{2}\\) \\d{8})"),
    CONTACT_FAX_REGEX("Fax: (\\(0xx\\d{2}\\) \\d{8})"),
    DELIVERY_PROPOSAL_REGEX("Entrega da Proposta:(.+?)(?= Hist�rico de eventos publicados|$)");

    private final String regex;

    Regex(String regex) {
        this.regex = regex;
    }
}
