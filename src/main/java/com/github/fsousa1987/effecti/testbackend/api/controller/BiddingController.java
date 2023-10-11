package com.github.fsousa1987.effecti.testbackend.api.controller;

import com.github.fsousa1987.effecti.testbackend.api.response.BidsResponse;
import com.github.fsousa1987.effecti.testbackend.domain.service.impl.BiddingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/bids")
@RequiredArgsConstructor
public class BiddingController {

    private final BiddingServiceImpl service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BidsResponse> searchBidsFromWeb() {
        BidsResponse bidsResponse = service.searchBidsFromWeb();
        return ResponseEntity.ok().body(bidsResponse);
    }

}
