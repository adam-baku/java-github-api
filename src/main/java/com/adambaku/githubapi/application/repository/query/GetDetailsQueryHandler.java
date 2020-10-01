package com.adambaku.githubapi.application.repository.query;

import com.adambaku.githubapi.application.repository.viewmodel.DetailsViewModel;
import org.springframework.stereotype.Service;

@Service
public class GetDetailsQueryHandler
{
    public DetailsViewModel handle(GetDetailsQuery query)
    {
        //@TODO
        return new DetailsViewModel();
    }
}
