package com.adambaku.githubapi.web.repository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repositories")
public class DetailsController
{
    @GetMapping("/{owner}/{repository-name}")
    public void index(@PathVariable String owner, @PathVariable("repository-name") String repositoryName)
    {
        //@TODO
    }
}
