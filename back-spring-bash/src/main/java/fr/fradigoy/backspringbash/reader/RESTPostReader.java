package fr.fradigoy.backspringbash.reader;

import fr.fradigoy.backspringbash.domain.PostBean;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RESTPostReader implements ItemReader<PostBean> {

    private final String URL_API;
    private final RestTemplate restTemplate;

    private int nextPostIndex;
    private List<PostBean> postBeanData;

    public RESTPostReader(String url_api, RestTemplate restTemplate) {
        URL_API = url_api;
        this.restTemplate = restTemplate;
        nextPostIndex = 0;
    }

    @Override
    public PostBean read() throws Exception {

        if(postDataIsNotInitialized()){
            postBeanData = fetchPostDataFromAPI();
        }

        PostBean nPostBean = null;

        if(nextPostIndex < postBeanData.size()){
            nPostBean = postBeanData.get(nextPostIndex);
            nextPostIndex++;
        }

        return nPostBean;
    }

    private List<PostBean> fetchPostDataFromAPI() {
        ResponseEntity<PostBean[]> response = restTemplate.getForEntity(URL_API, PostBean[].class);

        PostBean[] postBeanData = response.getBody();

        return Arrays.asList(postBeanData);

    }

    private boolean postDataIsNotInitialized(){
        return this.postBeanData == null;
    }
}
