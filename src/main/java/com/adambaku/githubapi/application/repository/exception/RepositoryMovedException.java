package com.adambaku.githubapi.application.repository.exception;

import com.adambaku.githubapi.application.repository.query.GetDetailsQuery;
import com.adambaku.githubapi.common.exception.ApplicationExceptionAbstract;

import java.util.HashMap;
import java.util.Map;

public class RepositoryMovedException extends ApplicationExceptionAbstract
{
    protected static ExceptionClass CODE = ExceptionClass.BAD_REQUEST;
    protected Map<String, String> data = new HashMap<>();

    public RepositoryMovedException(String message)
    {
        super(message);
    }

    public static RepositoryMovedException forQuery(GetDetailsQuery query, String movedTo)
    {
        var self = new RepositoryMovedException("Repository is moved permanently.");
        self.data.put("owner", query.owner);
        self.data.put("repository_name", query.repositoryName);
        self.data.put("moved_to", movedTo);

        return self;
    }
}
