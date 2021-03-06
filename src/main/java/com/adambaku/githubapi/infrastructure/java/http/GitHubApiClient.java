package com.adambaku.githubapi.infrastructure.java.http;

import com.adambaku.githubapi.common.exception.ApplicationExceptionAbstract;
import com.adambaku.githubapi.common.exception.UnableToExecuteRequestException;
import com.adambaku.githubapi.common.github.GitHubApiClientInterface;
import com.adambaku.githubapi.common.github.GitHubApiResponseInterface;
import com.adambaku.githubapi.infrastructure.java.UrlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class GitHubApiClient implements GitHubApiClientInterface
{
    @Autowired
    private HttpClient httpClient;

    @Autowired
    private HttpRequest.Builder requestBuilder;

    @Autowired
    private UrlProvider urlProvider;

    public GitHubApiResponseInterface GetRepositoryDetails(String owner, String repositoryName)
        throws ApplicationExceptionAbstract
    {
        String preparedUrl = urlProvider.url(UrlProvider.Url.REPOSITORY_DETAILS)
            .replaceAll(String.format("\\{%s\\}", "owner"), owner)
            .replaceAll(String.format("\\{%s\\}", "repository-name"), repositoryName);

        HttpRequest request = requestBuilder.GET()
            .uri(URI.create(preparedUrl))
            .header("accept", "application/json")
            .timeout(Duration.ofSeconds(5))
            .build();

        try {
            HttpResponse<byte[]> response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
            return new GitHubApiResponse(response);

        } catch (IOException | InterruptedException exception) {
            throw UnableToExecuteRequestException.fromHttpClientException(exception);
        }
    }
}
