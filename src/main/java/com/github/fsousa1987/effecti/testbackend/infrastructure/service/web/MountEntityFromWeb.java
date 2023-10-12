package com.github.fsousa1987.effecti.testbackend.infrastructure.service.web;

import com.github.fsousa1987.effecti.testbackend.api.exceptionhandler.exceptions.ServiceUnavailableException;
import com.github.fsousa1987.effecti.testbackend.domain.entity.BidsEntity;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.fsousa1987.effecti.testbackend.domain.enums.Regex.*;
import static org.jsoup.Jsoup.parse;

public class MountEntityFromWeb {

    public static final String URL_BIDS = "http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp";

    public static List<BidsEntity> bidsQueryFromWebSite() {

        Document doc = connect();
        String normalizedHtml = getNormalizeHtml(doc);

        Elements fragmentRecovered = queryElementsFromHtml(normalizedHtml);

        if (fragmentRecovered.isEmpty()) {
            throw new ServiceUnavailableException("There is no bid yet for today's date. Please try again later");
        }

        List<String> events = new ArrayList<>();
        List<BidsEntity> bids = new ArrayList<>();

        for (Element element : fragmentRecovered) {
            events.add(element.toString()
                    .replaceAll("<.*?>", " ")
                    .replaceAll("\\s+", " "));
        }

        System.out.println("stop");

        events.forEach(event -> {
            BidsEntity assembledEntity = assembleEntityToJson(event);
            bids.add(assembledEntity);
        });

        return bids;
    }

    private static Elements queryElementsFromHtml(String normalizedHtml) {
        Document document = parse(normalizedHtml);
        return document.select("tr.tex3");
    }

    private static String getNormalizeHtml(Document doc) {
        String html = doc.toString();
        html = html.replaceAll("&nbsp;", "<b>");
        return StringUtils.normalizeSpace(html);
    }

    private static Document connect() {
        Document doc;

        try {
            doc = Jsoup.connect(URL_BIDS).get();
        } catch (Exception e) {
            throw new ServiceUnavailableException("Web consultation service temporarily unavailable. Try again later.");
        }
        return doc;
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

    private static String extractUASGCode(String agency, String regex) {
        String result = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(agency);
        if (matcher.find()) {
            result = matcher.group();
        }

        return result;
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

}
