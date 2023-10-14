package com.github.fsousa1987.effecti.testbackend.domain.service.impl;

import com.github.fsousa1987.effecti.testbackend.api.response.BatchBidResponse;
import com.github.fsousa1987.effecti.testbackend.api.response.BidResponse;
import com.github.fsousa1987.effecti.testbackend.domain.entity.BidsEntity;
import com.github.fsousa1987.effecti.testbackend.domain.repository.BiddingRepository;
import com.github.fsousa1987.effecti.testbackend.domain.service.BiddingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.github.fsousa1987.effecti.testbackend.core.scraping.BiddingDataScraping.scrape;
import static com.github.fsousa1987.effecti.testbackend.core.scraping.MountEntity.mount;

@Service
@RequiredArgsConstructor
public class BiddingServiceImpl implements BiddingService {

    private final BiddingRepository repository;

    private final ModelMapper mapper;

    @Transactional
    public BatchBidResponse searchBidsFromWeb() {
        List<String> dataScraping = scrape();
        List<BidsEntity> bids = mount(dataScraping);

        List<BidsEntity> bidsEntities = checkIfThereAreNewBidsForPersistence(bids);

        List<BidResponse> bidList = bidsEntities.stream()
                .map(bidding -> mapper.map(bidding, BidResponse.class)).toList();

        BatchBidResponse bidsResponse = new BatchBidResponse();
        bidsResponse.setContent(bidList);

        return bidsResponse;
    }

    private List<BidsEntity> checkIfThereAreNewBidsForPersistence(List<BidsEntity> bidList) {
        List<BidsEntity> bidsFound = repository.findAll();

        if (bidsFound.isEmpty()) {
            return repository.saveAll(bidList);
        }

        List<BidsEntity> list = bidsFound.stream().filter(bidding -> !bidsFound.contains(bidding)).toList();

        if (!list.isEmpty()) {
            list = repository.saveAll(list);
            bidsFound.addAll(list);
            return bidsFound;
        }
        return bidsFound;
    }

}
