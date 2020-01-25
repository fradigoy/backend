package fr.fradigoy.backspringbash.config;

import fr.fradigoy.backspringbash.domain.PostBean;
import fr.fradigoy.backspringbash.reader.RESTPostReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RESTPostJobConfig {

    @Bean
    ItemReader<PostBean> restPostItemReader(Environment environment, RestTemplate restTemplate){

        return new RESTPostReader(environment.getRequiredProperty("https://jsonplaceholder.typicode.com/posts"), restTemplate);

    }

}
