package fr.fradigoy.backspringbash.entity;

import fr.fradigoy.backspringbash.domain.PostBean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String body;

    public Post(PostBean postBean) {

        this.title = postBean.getTitle();
        this.body = postBean.getBody();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
