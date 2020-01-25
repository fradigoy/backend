package fr.fradigoy.backspringbash.repository;

import fr.fradigoy.backspringbash.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
