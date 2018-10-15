package com.hubspot.garis.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.hubspot.garis.model.CountryResult;
import com.hubspot.garis.model.Partner;
import com.hubspot.garis.model.PartnerList;
import com.hubspot.garis.model.Winner;
import com.hubspot.garis.util.PartnersServiceProxy;
/**
 * @author Garis Suero <garis.suero@gmail.com>,<cuantico@gmail.com>
 */
public class PartnersConsumer {
    
    private final String TOKEN = "a39dc3af1cfdee708c0459c64d9c";
    private final String PARTNERS_URL = "https://candidate.hubapi.com/candidateTest/v1/partners?userKey=";
    private final String RESULTS_URL = "https://candidate.hubapi.com/candidateTest/v1/results?userKey=";
    private final long VIP_LIMIT = 65000;
    
    private Comparator<Partner> revenueComparator = new Comparator<Partner>() {
        @Override
        public int compare(Partner o1, Partner o2) {
            return Long.compare(o1.getRevenue(), o2.getRevenue());
        }
    };

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() throws MalformedURLException {
        PartnersServiceProxy client = new PartnersServiceProxy(TOKEN);
        try {
            PartnerList partners = client.getPartners(PARTNERS_URL);
            List<CountryResult> processedResults = getResults(partners);
            client.doPostPartnersResults(RESULTS_URL, processedResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testDelimiter() {
        List<String> data = new ArrayList<>();
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("d");
        data.add("e");
        data.add("h");
        data.add("i");
        
        List<String> result = data.stream()
        .map(this::formatter)
        .collect(Collectors.toList());
        System.out.println(result);
        
        String stringResult = data.stream()
        .map(this::formatter)
        .collect(Collectors.joining(", "));
        System.out.println(stringResult);
    }
    
    private String formatter(String input) {
        return String.format("(%s)", input);
    }

    private List<CountryResult> getResults(PartnerList list) {
        List<CountryResult> result = new ArrayList<>();
        List<String> countries = list.stream().map(e -> e.getCountry()).distinct().collect(Collectors.toList());

        for (String countryName : countries) {
            CountryResult data = new CountryResult();
            
            data.setName(countryName);
            Supplier<Stream<Partner>> supplier = () -> list.stream().filter(l -> l.getCountry().equalsIgnoreCase(countryName));
            data.setPartners(supplier.get().count());
            Supplier<Stream<Partner>> byRevenue = () -> supplier.get().filter(l -> l.getRevenue() >= VIP_LIMIT);
            Set<String> vips = byRevenue.get().map(Partner::getEmail).collect(Collectors.toSet());
            data.setVips(vips);
            Optional<Partner> opt = byRevenue.get().max(revenueComparator);
            data.setWinner(getWinner(opt));
            result.add(data);
        }
        return result;
    }

    private static Winner getWinner(Optional<Partner> partner) {
        if (partner.isPresent()) {
            Winner winner = new Winner();
            winner.setAmount(partner.get().getRevenue());
            winner.setEmail(partner.get().getEmail());
            return winner;
        }
        return null;
    }
}
