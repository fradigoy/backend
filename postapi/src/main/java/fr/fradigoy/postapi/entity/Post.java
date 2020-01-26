package fr.fradigoy.postapi.entity;


import fr.fradigoy.postapi.bean.PostBean;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long idPost;
    private String title;
    private String body;

    public Post() {
    }

    public Post(PostBean postBean) {
        this.title = postBean.getTitle();
        this.body = postBean.getBody();
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setId(Long idPost) {
        this.idPost = idPost;
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
