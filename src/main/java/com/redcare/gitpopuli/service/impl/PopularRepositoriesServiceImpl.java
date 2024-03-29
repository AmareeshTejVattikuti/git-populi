package com.redcare.gitpopuli.service.impl;

import com.redcare.gitpopuli.client.GitHubApiClient;
import com.redcare.gitpopuli.model.Repository;
import com.redcare.gitpopuli.service.PopularRepositoriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PopularRepositoriesServiceImpl implements PopularRepositoriesService {

    private final GitHubApiClient gitHubApiClient;

    private static final int DEFAULT_TOP = 10;
    private static final int DEFAULT_PAGE = 1;
    private static final String DEFAULT_SORT = "stars";
    private static final String DEFAULT_ORDER = "desc";

    @Override
    @Cacheable(cacheNames = "popularRepositories", key = "{#top, #language, #since, #page}")
    public Flux<Repository> getPopularRepositories(Optional<Integer> top, Optional<String> language, Optional<LocalDate> since, Optional<Integer> page) {
        String query = buildQuery(language, since);
        log.info("Building GitHub search query: {}", query);
        return gitHubApiClient.searchRepositories(query, DEFAULT_SORT, DEFAULT_ORDER, top.orElse(DEFAULT_TOP), page.orElse(DEFAULT_PAGE));
    }

    private String buildQuery(Optional<String> language, Optional<LocalDate> since) {
        StringBuilder queryBuilder = new StringBuilder();
        language.ifPresent(lang -> queryBuilder.append("language:").append(lang).append("+"));
        since.ifPresent(date -> queryBuilder.append("created:").append(date).append("+"));

        return queryBuilder.toString();
    }
}