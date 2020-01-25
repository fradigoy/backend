package fr.fradigoy.postapi.service;


import fr.fradigoy.postapi.Tools.PostNotFoundException;
import fr.fradigoy.postapi.entity.Post;
import fr.fradigoy.postapi.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PostService {

    private  PostRepository postRepository;



    @GetMapping("/posts")
    public ResponseEntity<?> getPosts(){

        List<Post> posts = (List<Post>) postRepository.findAll();

        if(posts.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id){

        Optional<Post> post = postRepository.findById(id);

        if(post == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Optional<Post>>(post, HttpStatus.OK);
    }
}
