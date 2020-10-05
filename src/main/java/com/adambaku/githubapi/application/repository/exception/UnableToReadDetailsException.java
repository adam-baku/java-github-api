package com.adambaku.githubapi.application.repository.exception;

import com.adambaku.githubapi.common.exception.ApplicationExceptionAbstract;
import com.adambaku.githubapi.common.github.GitHubApiResponseInterface;

public class UnableToReadDetailsException extends ApplicationExceptionAbstract
{
    static {
        CODE = ExceptionClass.CRITICAL;
    }

    private GitHubApiResponseInterface apiResponse;

    public UnableToReadDetailsException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public static UnableToReadDetailsException forApiResponse(GitHubApiResponseInterface apiResponse, Throwable cause)
    {
        var self = new UnableToReadDetailsException("Unable to read response body string.", cause);
        self.apiResponse = apiResponse;

        return self;
    }
}
