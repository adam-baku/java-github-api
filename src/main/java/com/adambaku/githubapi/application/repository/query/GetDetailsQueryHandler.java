package com.adambaku.githubapi.application.repository.query;

import com.adambaku.githubapi.application.repository.exception.RepositoryMovedException;
import com.adambaku.githubapi.application.repository.exception.RepositoryNotFoundException;
import com.adambaku.githubapi.application.repository.viewmodel.DetailsViewModel;
import com.adambaku.githubapi.common.exception.ApplicationExceptionAbstract;
import com.adambaku.githubapi.common.github.GitHubApiClientInterface;
import com.adambaku.githubapi.common.github.GitHubApiResponseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDetailsQueryHandler
{
    @Autowired
    private GitHubApiClientInterface client;

    public DetailsViewModel handle(GetDetailsQuery query)
        throws ApplicationExceptionAbstract
    {
        GitHubApiResponseInterface apiResponse = client.GetRepositoryDetails(query.owner, query.repositoryName);
        assertStatusCode(apiResponse, query);

        return new DetailsViewModel();
    }

    private void assertStatusCode(GitHubApiResponseInterface apiResponse, GetDetailsQuery query)
        throws ApplicationExceptionAbstract
    {
        switch (apiResponse.statusCode()) {
            case 200 -> { }
            case 301 -> throw RepositoryMovedException.forQuery(query, "TODO implement");
            case 403, 404 -> throw RepositoryNotFoundException.forQuery(query);
            default -> { /** @TODO implementation of "not recognized status code" exception */ }
        }
    }
}
