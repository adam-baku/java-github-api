package com.adambaku.githubapi.application.repository.query;

public class GetDetailsQuery
{
    public final String owner;
    public final String repositoryName;

    public GetDetailsQuery(String owner, String repositoryName)
    {
        this.owner = owner;
        this.repositoryName = repositoryName;
    }
}
