package project.laundry.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.laundry.dto.post_dto.postDto;
import project.laundry.entity.post;
import project.laundry.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository repository;

    public void init() {
        postDto dto = postDto.builder()
                .name("홍길동")
                .phone("010-1234-5678")
                .clothCount(4)
                .price(12324)
                .build();

        postDto dto2 = postDto.builder()
                .name("아무개")
                .phone("010-1234-3212")
                .clothCount(3)
                .price(13000)
                .build();

        register(dto);
        register(dto2);
    }


    public Long register(postDto dto) {
        post entity = dtoToEntity(dto);
        repository.save(entity);

        return entity.getId();
    }

    public postDto findOne(Long postId) {
        Optional<post> findPost = repository.findById(postId);
        return findPost.map(post -> entityToDto(post)).orElse(null);
    }


    public List<postDto> findAll() {
        List<post> posts = repository.findAll();

        List<postDto> postDtos = posts.stream().map(post -> {
            return entityToDto(post);
        }).toList();

        return postDtos;
    }



    public post dtoToEntity(postDto dto) {
        post entity = post.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .clothCount(dto.getClothCount())
                .price(dto.getPrice())
                .content(dto.getContent())
                .build();

        return entity;
    }

    public postDto entityToDto(post entity) {
        postDto dto = postDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .clothCount(entity.getClothCount())
                .price(entity.getPrice())
                .content(entity.getContent())
                .createTime(entity.getCreateTime())
                .build();

        return dto;
    }
}
