package project.laundry.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.dto.post_dto.postDto;
import project.laundry.entity.Post;
import project.laundry.entity.Visit;
import project.laundry.repository.PostRepository;
import project.laundry.repository.VisitRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository repository;
    private final VisitRepository visitRepository;

//    @Transactional
//    public void init() {
//        postDto dto = postDto.builder()
//                .name("홍길동")
//                .phone("010-1234-5678")
//                .clothCount(4)
//                .price(12324)
//                .build();
//
//        postDto dto2 = postDto.builder()
//                .name("아무개")
//                .phone("010-1234-3212")
//                .clothCount(3)
//                .price(13000)
//                .build();
//
//        register(dto);
//        register(dto2);
//    }


    @Transactional
    public Long register(postDto dto) {
        Post entity = dtoToEntity(dto);

        Visit visit = Visit.of(entity, entity.getPrice());
        log.info("visit.getPrice : {}", visit.getPrice());
        entity.addVisit(visit);
        repository.save(entity);

        return entity.getId();
    }


    public postDto findOne(Long postId) {
        Optional<Post> findPost = repository.findById(postId);
        return findPost.map(Post -> entityToDto(Post)).orElse(null);
    }


    public List<postDto> findAll() {
        List<Post> Posts = repository.findAll();

        return Posts.stream().map(Post -> {
            return entityToDto(Post);
        }).toList();
    }

    @Transactional
    public Long update(Long postId, postDto dto) {

        Post findPost = repository.findById(postId).orElseThrow(() -> new RuntimeException("존재하지 않는 손님입니다."));

        List<Visit> visits = findPost.getVisits();

        visits.forEach(v -> {
            v.setPrice(dto.getPrice());
            v.setVisitDateTime(LocalDate.now());
            v.setPost(findPost);
        });


        return findPost.updatePost(dto);
    }

    @Transactional
    public void delete(Long postId) {

        Post findPost = repository.findById(postId).orElseThrow(() -> new RuntimeException("존재하지 않는 손님입니다."));
        repository.delete(findPost);

    }


    public Post dtoToEntity(postDto dto) {
        return Post.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .clothCount(dto.getClothCount())
                .clothStatus(dto.getClothStatus())
                .price(dto.getPrice())
                .content(dto.getContent())
                .build();
    }

    public postDto entityToDto(Post entity) {
        return postDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .clothCount(entity.getClothCount())
                .clothStatus(entity.getClothStatus())
                .price(entity.getPrice())
                .content(entity.getContent())
                .createTime(entity.getCreateTime())
                .build();
    }
}
