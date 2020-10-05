package com.adambaku.githubapi.common.exception;

public class UnableToExecuteRequestException extends ApplicationExceptionAbstract
{
    static {
        CODE = ExceptionClass.FAIL;
    }

    public UnableToExecuteRequestException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public static UnableToExecuteRequestException fromHttpClientException(Throwable throwable)
    {
        return new UnableToExecuteRequestException("Unable to execute github api request.", throwable);
    }
}
