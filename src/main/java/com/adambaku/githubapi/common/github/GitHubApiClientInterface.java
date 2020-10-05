package com.adambaku.githubapi.common.github;

public interface GitHubApiClientInterface
{
    GitHubApiResponseInterface GetRepositoryDetails(String owner, String repositoryName);
}
