package com.adambaku.githubapi.infrastructure.java.http;

import com.adambaku.githubapi.common.github.GitHubApiResponseInterface;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class GitHubApiResponse implements GitHubApiResponseInterface
{
    private final HttpResponse<byte[]> httpResponse;

    public GitHubApiResponse(HttpResponse<byte[]> httpResponse)
    {
        this.httpResponse = httpResponse;
    }

    @Override
    public int statusCode()
    {
        return httpResponse.statusCode();
    }

    @Override
    public Map<String, List<String>> headers()
    {
        return httpResponse.headers().map();
    }

    @Override
    public String body()
    {
        return new String(httpResponse.body());
    }
}
