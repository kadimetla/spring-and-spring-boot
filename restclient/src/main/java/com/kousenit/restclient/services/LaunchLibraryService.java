package com.kousenit.restclient.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

import static com.kousenit.restclient.json.LaunchLibraryRecords.*;

@Service
public class LaunchLibraryService {
    private final RestClient client;
    private final String baseUrl = "https://ll.thespacedevs.com";

    public LaunchLibraryService() {
        this.client = RestClient.create(baseUrl);
    }

    public List<Expedition> getExpeditions() {
        return Objects.requireNonNull(client.get()
                        .uri(baseUrl + "/2.3.0/expeditions/?is_active=true&mode=detailed")
                        .retrieve()
                        .body(ExpeditionResponse.class))
                .results();
    }
}
