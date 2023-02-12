package project.laundry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.laundry.entity.post;

public interface PostRepository extends JpaRepository<post, Long> {

}
