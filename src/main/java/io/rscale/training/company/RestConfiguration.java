package io.rscale.training.company;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import io.rscale.training.company.Company;


@Configuration
public class RestConfiguration extends RepositoryRestConfigurerAdapter {

   @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Company.class);
    }
	
}
