package fr.fradigoy.backspringbash.processor;

import fr.fradigoy.backspringbash.domain.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PostItemProcessor implements ItemProcessor<Post, Post> {

    private static final Logger log = LoggerFactory.getLogger(PostItemProcessor.class);

    @Override
    public Post process(Post post) throws Exception {
        final String title = post.getTitle();
        final String body = post.getBody();

        final Post processedPost = new Post(title,body);

        log.info("Convertir ( "+post+" ) into ( "+processedPost+" ) ");

        return processedPost;
    }
}
