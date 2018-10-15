package com.hubspot.garis.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hubspot.garis.model.CountryResult;
import com.hubspot.garis.model.PartnerList;

/**
 * @author Garis Suero <garis.suero@gmail.com>,<cuantico@gmail.com>
 */
public class PartnersServiceProxy {
    private String token;
    protected final ObjectMapper mapper = new ObjectMapper();

    public PartnersServiceProxy(String token) throws MalformedURLException {
        super();
        this.token = token;
    }

    public PartnerList getPartners(String url) throws IOException {
        HttpURLConnection conn = null;
        try {
            conn = getConnection("GET", url, null);
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            ObjectReader reader = mapper.readerFor(PartnerList.class).withRootName("partners");
            return reader.readValue(response.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public void doPostPartnersResults(String url, List<CountryResult> results) {
        HttpURLConnection conn = null;
        try {
            
            ObjectWriter writer = mapper.writer().withRootName("countries");
            String data = writer.writeValueAsString(results);
            conn = getConnection("POST", url, Integer.valueOf(data.length()));

            try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
               wr.write(data.getBytes());
               wr.flush();
               wr.close();
            }
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn !=  null) {
                conn.disconnect();
            }
        }
    }

    private HttpURLConnection getConnection(String method, String url, Integer size) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url + token).openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Accept", "application/json");
        if ("POST".equalsIgnoreCase(method)) {
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setInstanceFollowRedirects(false);
            conn.setDoOutput(true);
            if (size != null) {
                conn.setRequestProperty("Content-Length", Integer.toString(size));
            }
        }
        conn.setUseCaches(false);
        return conn;
    }

}
