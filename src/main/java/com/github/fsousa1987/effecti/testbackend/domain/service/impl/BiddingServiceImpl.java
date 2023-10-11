package com.github.fsousa1987.effecti.testbackend.domain.service.impl;

import com.github.fsousa1987.effecti.testbackend.api.response.BatchBidResponse;
import com.github.fsousa1987.effecti.testbackend.api.response.BidResponse;
import com.github.fsousa1987.effecti.testbackend.domain.entity.BidsEntity;
import com.github.fsousa1987.effecti.testbackend.domain.service.BindingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.fsousa1987.effecti.testbackend.infrastructure.service.web.MountEntityFromWeb.bidsQueryFromWebSite;

@Service
@RequiredArgsConstructor
public class BiddingServiceImpl implements BindingService {

    private final ModelMapper mapper;

    public BatchBidResponse searchBidsFromWeb() {
        List<BidsEntity> bidsEntities = bidsQueryFromWebSite();
        List<BidResponse> bidList = bidsEntities.stream()
                .map(bid -> mapper.map(bid, BidResponse.class)).toList();

        BatchBidResponse bidsResponse = new BatchBidResponse();
        bidsResponse.setContent(bidList);

        return bidsResponse;
    }
}
