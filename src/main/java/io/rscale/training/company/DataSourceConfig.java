package io.rscale.training.company;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.cloud.config.java.AbstractCloudConfig;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
@Profile("cloud")
public class DataSourceConfig extends AbstractCloudConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    public DataSourceConfig() {
        logger.info(this.getClass() + " loaded");
    }

    @Bean
    public DataSource dataSource() throws Exception {
        DataSource dataSource = cloud().getSingletonServiceConnector(DataSource.class, null);
        if ( !isMySQL(dataSource)) {
            logger.error("MySQL required when running cloud profile.");
            throw new UnsatisfiedDependencyException("javax.sql.DataSource", "javax.sql.DataSource", "javax.sql.DataSource", "MySQL required when running cloud profile.");
        }
        return dataSource;
    }

    private boolean isMySQL(DataSource dataSource) throws Exception {
      /* implement me!
	  try {
		  MysqlDataSource temp = (MysqlDataSource) dataSource;
		  return true;
	  } catch(ClassCastException e) {
		  System.out.println("not using a mysql db");
		  e.printStackTrace();
	  }
	  return false;*/
	  return dataSource.getConnection().getMetaData().getDriverName().toLowerCase().contains("mysql");
    }

}
