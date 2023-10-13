package com.github.fsousa1987.effecti.testbackend.core.scraping;

import com.github.fsousa1987.effecti.testbackend.domain.entity.BidsEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.fsousa1987.effecti.testbackend.domain.enums.Regex.*;

public class MountEntity {

    public static List<BidsEntity> mount(List<String> data) {

        List<BidsEntity> bids = new ArrayList<>();

        data.forEach(event -> {
            BidsEntity assembledEntity = assembleEntityToJson(event);
            bids.add(assembledEntity);
        });

        return bids;
    }

    private static BidsEntity assembleEntityToJson(String event) {

        String bidderName = entityMountForJson(event, REGEX_AGENCY_IDENTIFICATION.getRegex());
        String uasgCode = extractUASGCode(event, UASG_REGEX_CODE.getRegex());
        String biddingType = entityMountForJson(event, REGEX_BIDDING_TYPE.getRegex());
        String objectType = entityMountForJson(event, REGEX_OBJECT_TYPE.getRegex());
        String noticeDate = entityMountForJson(event, REGEX_NOTICE_DATE.getRegex());
        String address = entityMountForJson(event, REGEX_ADDRESS.getRegex());
        String contactPhone = entityMountForJson(event, CONTACT_PHONE_REGEX.getRegex());
        String contactFax = entityMountForJson(event, CONTACT_FAX_REGEX.getRegex());
        String deliveryProposal = entityMountForJson(event, DELIVERY_PROPOSAL_REGEX.getRegex());

        return mountEntityWithFields(bidderName, uasgCode, biddingType, objectType, noticeDate,
                address, contactPhone, contactFax, deliveryProposal);
    }

    private static String entityMountForJson(String agency, String regex) {
        String result = null;
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(agency);
        if (matcher.find()) {
            result = matcher.group(1).trim();
        }
        return result;
    }

    private static String extractUASGCode(String agency, String regex) {
        String result = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(agency);
        if (matcher.find()) {
            result = matcher.group();
        }

        return result;
    }

    private static BidsEntity mountEntityWithFields(
            String agency, String uasgCode, String biddingType, String objectType, String noticeDate, String address,
            String contactPhone, String contactFax, String deliveryProposal) {

        return BidsEntity.builder()
                .agency(agency)
                .uasgCode(uasgCode)
                .biddingType(biddingType)
                .object(objectType)
                .noticeDate(noticeDate)
                .address(address)
                .telephone(contactPhone)
                .fax(contactFax)
                .deliveryProposal(deliveryProposal)
                .build();
    }

}
