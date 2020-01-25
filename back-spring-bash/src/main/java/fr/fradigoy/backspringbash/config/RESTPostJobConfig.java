package fr.fradigoy.backspringbash.config;

import fr.fradigoy.backspringbash.JobCompletionNotificationListener;
import fr.fradigoy.backspringbash.domain.Post;
import fr.fradigoy.backspringbash.processor.PostItemProcessor;
import fr.fradigoy.backspringbash.reader.RESTPostReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class RESTPostJobConfig {

    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;


    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public PostItemProcessor postItemProcessor(){
        return new PostItemProcessor();
    }

    @Bean
    public RESTPostReader restPostReader(){
        return new RESTPostReader("https://jsonplaceholder.typicode.com/posts", restTemplate());
    }

    @Bean
    public JdbcBatchItemWriter<Post> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Post>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO post (title, body) VALUES (:title, :body)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1){
        return jobs.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Post> writer){

        return steps.get("step1")
                .<Post, Post> chunk(10)
                .reader(restPostReader())
                .processor(postItemProcessor())
                .writer(writer)
                .build();
    }

    @Bean
    ItemReader<Post> restPostItemReader(Environment environment, RestTemplate restTemplate){

        return new RESTPostReader(environment.getRequiredProperty("https://jsonplaceholder.typicode.com/posts"), restTemplate);

    }

}
