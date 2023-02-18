package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.laundry.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
