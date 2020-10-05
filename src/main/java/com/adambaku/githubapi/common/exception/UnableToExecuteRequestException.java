package com.adambaku.githubapi.common.exception;

import java.util.HashMap;
import java.util.Map;

public class UnableToExecuteRequestException extends ApplicationExceptionAbstract
{
    protected static ExceptionClass CODE = ExceptionClass.FAIL;
    protected Map<String, String> data = new HashMap<>();

    public UnableToExecuteRequestException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public static UnableToExecuteRequestException fromHttpClientException(Throwable throwable)
    {
        return new UnableToExecuteRequestException("Unable to execute github api request.", throwable);
    }
}
