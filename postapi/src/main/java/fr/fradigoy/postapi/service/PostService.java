package fr.fradigoy.postapi.service;

import fr.fradigoy.postapi.Tools.PostNotFoundException;
import fr.fradigoy.postapi.entity.Post;
import fr.fradigoy.postapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PostService {

    @Autowired
    private  PostRepository postRepository;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<?> getPosts(){

        List<Post> posts = new ArrayList<Post>();
        postRepository.findAll().forEach(posts::add);

        if(posts.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPostById(@PathVariable Long id){

        Optional<Post> post = postRepository.findById(id);

        if(post == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Optional<Post>>(post, HttpStatus.OK);
    }


    
}
