package com.adambaku.githubapi.application.repository.query;

import com.adambaku.githubapi.application.repository.exception.RepositoryMovedException;
import com.adambaku.githubapi.application.repository.exception.RepositoryNotFoundException;
import com.adambaku.githubapi.application.repository.exception.UnableToReadDetailsException;
import com.adambaku.githubapi.application.repository.viewmodel.DetailsViewModel;
import com.adambaku.githubapi.common.github.GitHubApiClientInterface;
import com.adambaku.githubapi.common.github.GitHubApiResponseInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetDetailsQueryHandlerTest
{
    @InjectMocks
    private GetDetailsQueryHandler handler;

    @Mock
    private GitHubApiClientInterface client;

    @Mock
    private GitHubApiResponseInterface apiResponse;

    @Test
    void willReturnDetailsViewModel() throws IOException
    {
        //given
        String content = Files.readString(Paths.get("src/test/resources/repository/details.json"));

        when(apiResponse.statusCode()).thenReturn(200);
        when(apiResponse.body()).thenReturn(content);

        when(client.GetRepositoryDetails(anyString(), anyString())).thenReturn(apiResponse);

        //when
        DetailsViewModel result = handler.handle(new GetDetailsQuery("any_owner", "any_repo_name"));

        //then
        assertNotNull(result);
    }

    @ParameterizedTest
    @MethodSource("provideWrongStatusCodes")
    void willThrowExceptionWhenResponseIsNotOK(int givenStatusCode, Class<? extends Throwable> expectedException)
    {
        //given
        when(apiResponse.statusCode()).thenReturn(givenStatusCode);

        when(client.GetRepositoryDetails(anyString(), anyString())).thenReturn(apiResponse);

        //when
        //then
        assertThrows(
            expectedException,
            () -> handler.handle(new GetDetailsQuery("any_owner", "any_repo_name"))
        );
    }

    private static Stream<Arguments> provideWrongStatusCodes()
    {
        return Stream.of(
            Arguments.of(301, RepositoryMovedException.class),
            Arguments.of(403, RepositoryNotFoundException.class),
            Arguments.of(404, RepositoryNotFoundException.class)
        );
    }

    @Test
    void willThrowExceptionWhenCantProcessJsonBody()
    {
        //given
        when(apiResponse.statusCode()).thenReturn(200);
        when(apiResponse.body()).thenReturn("any wrong json body!");

        when(client.GetRepositoryDetails(anyString(), anyString())).thenReturn(apiResponse);

        //when
        //then
        assertThrows(
            UnableToReadDetailsException.class,
            () -> handler.handle(new GetDetailsQuery("any_owner", "any_repo_name"))
        );
    }
}