package com.github.fsousa1987.effecti.testbackend.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BidResponse {

    private String agency;

    private String uasgCode;

    private String biddingType;

    private String object;

    private String noticeDate;

    private String address;

    private String telephone;

    private String fax;

    private String deliveryProposal;

}
