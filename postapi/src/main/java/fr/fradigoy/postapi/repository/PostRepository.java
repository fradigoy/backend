package fr.fradigoy.postapi.repository;

import fr.fradigoy.postapi.entity.Post;
import org.springframework.data.repository.CrudRepository;



public interface PostRepository extends CrudRepository<Post, Long> {

}
