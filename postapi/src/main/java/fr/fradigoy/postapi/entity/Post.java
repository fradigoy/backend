package fr.fradigoy.postapi.entity;


import fr.fradigoy.postapi.bean.PostBean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String body;

    public Post() {
    }

    public Post(PostBean postBean) {
        this.title = postBean.getTitle();
        this.body = postBean.getBody();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
