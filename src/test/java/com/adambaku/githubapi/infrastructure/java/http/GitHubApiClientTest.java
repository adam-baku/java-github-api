package com.adambaku.githubapi.infrastructure.java.http;

import com.adambaku.githubapi.common.exception.UnableToExecuteRequestException;
import com.adambaku.githubapi.common.github.GitHubApiResponseInterface;
import com.adambaku.githubapi.infrastructure.java.UrlProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GitHubApiClientTest
{
    @InjectMocks
    private GitHubApiClient apiClient;

    @Mock
    private HttpClient httpClient;

    @Mock(answer = Answers.RETURNS_SELF)
    private HttpRequest.Builder requestBuilder;

    @Mock
    private UrlProvider urlProvider;

    @Test
    void willReturnApiResponse() throws IOException, InterruptedException
    {
        //given
        when(urlProvider.url(UrlProvider.Url.REPOSITORY_DETAILS)).thenReturn("https://api.github.com/repos/{owner}/{repository-name}");
        when(requestBuilder.build()).thenReturn(mock(HttpRequest.class));
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(mock(HttpResponse.class));

        //when
        GitHubApiResponseInterface result = apiClient.GetRepositoryDetails("any_owner", "any_repo_name");

        //then
        assertNotNull(result);

        verify(requestBuilder).GET();
        verify(requestBuilder).uri(URI.create("https://api.github.com/repos/any_owner/any_repo_name"));
        verify(requestBuilder).header("accept", "application/json");
        verify(requestBuilder).timeout(Duration.ofSeconds(5));
        verify(requestBuilder).build();
    }

    @Test
    void throwCustomExceptionWhenHttpClientExceptionOccurred() throws IOException, InterruptedException
    {
        //given
        when(urlProvider.url(UrlProvider.Url.REPOSITORY_DETAILS)).thenReturn("https://api.github.com/repos/{owner}/{repository-name}");
        when(requestBuilder.build()).thenReturn(mock(HttpRequest.class));
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenThrow(new IOException());

        //when
        //then
        assertThrows(
            UnableToExecuteRequestException.class,
            () -> apiClient.GetRepositoryDetails("any_owner", "any_repo_name")
        );

        verify(requestBuilder).GET();
        verify(requestBuilder).uri(URI.create("https://api.github.com/repos/any_owner/any_repo_name"));
        verify(requestBuilder).header("accept", "application/json");
        verify(requestBuilder).timeout(Duration.ofSeconds(5));
        verify(requestBuilder).build();
    }
}