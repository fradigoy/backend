package fr.fradigoy.backspringbash.processor;

import fr.fradigoy.backspringbash.domain.PostBean;
import org.springframework.batch.item.ItemProcessor;

public class PostItemProcessor implements ItemProcessor<PostBean, PostBean> {
    @Override
    public PostBean process(PostBean postBean) throws Exception {
        final String title = postBean.getTitle();
        final String body = postBean.getBody();

        final PostBean processedPost = new PostBean(title,body);

        return processedPost;
    }
}
