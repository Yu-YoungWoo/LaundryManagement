package project.laundry.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.laundry.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;




}
