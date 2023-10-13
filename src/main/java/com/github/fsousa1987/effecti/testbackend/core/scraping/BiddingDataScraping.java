package com.github.fsousa1987.effecti.testbackend.core.scraping;

import com.github.fsousa1987.effecti.testbackend.api.exceptionhandler.exceptions.ServiceUnavailableException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static org.jsoup.Jsoup.parse;

public class BiddingDataScraping {

    public static final String URL_BIDS = "http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp";

    public static List<String> scrape() {

        Document doc = connect();
        String normalizedHtml = getNormalizeHtml(doc);

        Elements fragmentRecovered = queryElementsFromHtml(normalizedHtml);

        if (fragmentRecovered.isEmpty()) {
            throw new ServiceUnavailableException("There is no bid yet for today's date. Please try again later");
        }

        List<String> events = new ArrayList<>();

        for (Element element : fragmentRecovered) {
            events.add(element.toString()
                    .replaceAll("<.*?>", " ")
                    .replaceAll("\\s+", " "));
        }

        return events;
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

    private static String getNormalizeHtml(Document doc) {
        String html = doc.toString();
        html = html.replaceAll("&nbsp;", "<b>");
        return StringUtils.normalizeSpace(html);
    }

    private static Elements queryElementsFromHtml(String normalizedHtml) {
        Document document = parse(normalizedHtml);
        return document.select("tr.tex3");
    }

}