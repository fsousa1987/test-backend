package com.github.fsousa1987.effecti.testbackend.domain.service.impl;

import com.github.fsousa1987.effecti.testbackend.api.response.BatchBidResponse;
import com.github.fsousa1987.effecti.testbackend.api.response.BidResponse;
import com.github.fsousa1987.effecti.testbackend.domain.entity.BidsEntity;
import com.github.fsousa1987.effecti.testbackend.domain.service.BiddingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.fsousa1987.effecti.testbackend.core.scraping.BiddingDataScraping.scrape;
import static com.github.fsousa1987.effecti.testbackend.core.scraping.MountEntity.mount;

@Service
@RequiredArgsConstructor
public class BiddingServiceImpl implements BiddingService {

    private final ModelMapper mapper;

    public BatchBidResponse searchBidsFromWeb() {
        List<String> dataScraping = scrape();
        List<BidsEntity> bids = mount(dataScraping);

        List<BidResponse> bidList = bids.stream()
                .map(bidding -> mapper.map(bidding, BidResponse.class)).toList();

        BatchBidResponse bidsResponse = new BatchBidResponse();
        bidsResponse.setContent(bidList);

        return bidsResponse;
    }
    
}
