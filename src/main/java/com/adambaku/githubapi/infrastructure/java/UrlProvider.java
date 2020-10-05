package com.adambaku.githubapi.infrastructure.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class UrlProvider
{
    public enum Url
    {
        REPOSITORY_DETAILS("github.url.repository.details"),
        REPOSITORY_BRANCHES("github.url.repository.branches"),
        REPOSITORY_COMMITS("github.url.repository.commits"),
        USER_REPOSITORIES("github.url.user.repositories");

        private final String value;

        Url(String value)
        {
            this.value = value;
        }
    }

    @Autowired
    private Environment env;

    public String url(Url url)
    {
        return env.getProperty(url.value);
    }
}
