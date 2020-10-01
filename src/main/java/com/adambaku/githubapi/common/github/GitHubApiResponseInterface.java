package com.adambaku.githubapi.common.github;

import java.util.List;
import java.util.Map;

public interface GitHubApiResponseInterface
{
    int statusCode();
    Map<String, List<String>> headers();
    String body();
}
