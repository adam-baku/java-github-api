package com.adambaku.githubapi.common.exception;

import java.util.HashMap;
import java.util.Map;

public abstract class ApplicationExceptionAbstract extends RuntimeException
{
    public ApplicationExceptionAbstract(String message)
    {
        super(message);
    }

    public ApplicationExceptionAbstract(String message, Throwable cause)
    {
        super(message, cause);
    }

    public enum ExceptionClass
    {
        NOT_FOUND,
        NOT_VALID,
        CRITICAL,
        UNAUTHORIZED,
        TIMEOUT,
        BAD_REQUEST,
        FAIL
    }

    protected static ExceptionClass CODE;
    protected Map<String, String> data = new HashMap<>();

    public ExceptionClass getCode()
    {
        return CODE;
    }

    public Map<String, String> getData()
    {
        return data;
    }
}
