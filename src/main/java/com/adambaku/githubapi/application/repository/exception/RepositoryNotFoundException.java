package com.adambaku.githubapi.application.repository.exception;

import com.adambaku.githubapi.application.repository.query.GetDetailsQuery;
import com.adambaku.githubapi.common.exception.ApplicationExceptionAbstract;

import java.util.HashMap;
import java.util.Map;

public class RepositoryNotFoundException extends ApplicationExceptionAbstract
{
    protected static ExceptionClass CODE = ExceptionClass.NOT_FOUND;
    protected Map<String, String> data = new HashMap<>();

    public RepositoryNotFoundException(String message)
    {
        super(message);
    }

    public static RepositoryNotFoundException forQuery(GetDetailsQuery query)
    {
        var self = new RepositoryNotFoundException("Repository not found.");
        self.data.put("owner", query.owner);
        self.data.put("repository_name", query.repositoryName);

        return self;
    }
}
