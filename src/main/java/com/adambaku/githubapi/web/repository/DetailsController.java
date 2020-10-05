package com.adambaku.githubapi.web.repository;

import com.adambaku.githubapi.application.repository.query.GetDetailsQuery;
import com.adambaku.githubapi.application.repository.query.GetDetailsQueryHandler;
import com.adambaku.githubapi.application.repository.viewmodel.DetailsViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repositories")
public class DetailsController
{
    @Autowired
    private ApplicationContext container;

    @GetMapping("/{owner}/{repository-name}")
    public ResponseEntity<DetailsViewModel> index(@PathVariable String owner, @PathVariable("repository-name") String repositoryName)
    {
        GetDetailsQueryHandler handler = container.getBean(GetDetailsQueryHandler.class);

        DetailsViewModel viewModel = handler.handle(new GetDetailsQuery(owner, repositoryName));

        return ResponseEntity.ok(viewModel);
    }
}
