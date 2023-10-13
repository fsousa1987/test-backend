package com.github.fsousa1987.effecti.testbackend.api.controller;

import com.github.fsousa1987.effecti.testbackend.api.response.BatchBidResponse;
import com.github.fsousa1987.effecti.testbackend.domain.service.BiddingService;
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

    private final BiddingService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BatchBidResponse> searchBidsFromWeb() {
        BatchBidResponse bidsResponse = service.searchBidsFromWeb();
        return ResponseEntity.ok().body(bidsResponse);
    }

}
