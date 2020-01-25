package fr.fradigoy.backspringbash.reader;

import fr.fradigoy.backspringbash.domain.Post;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RESTPostReader implements ItemReader<Post> {

    private final String URL_API;
    private final RestTemplate restTemplate;

    private int nextPostIndex;
    private List<Post> postData;

    public RESTPostReader(String url_api, RestTemplate restTemplate) {
        URL_API = url_api;
        this.restTemplate = restTemplate;
        nextPostIndex = 0;
    }

    @Override
    public Post read() throws Exception {

        if(postDataIsNotInitialized()){
            postData = fetchPostDataFromAPI();
        }

        Post nPost = null;

        if(nextPostIndex < postData.size()){
            nPost = postData.get(nextPostIndex);
            nextPostIndex++;
        }

        return nPost;
    }

    private List<Post> fetchPostDataFromAPI() {
        ResponseEntity<Post[]> response = restTemplate.getForEntity(URL_API, Post[].class);

        Post[] postData = response.getBody();

        return Arrays.asList(postData);

    }

    private boolean postDataIsNotInitialized(){
        return this.postData == null;
    }
}
