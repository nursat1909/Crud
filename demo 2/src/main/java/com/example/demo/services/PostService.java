package com.example.demo.services;


import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.models.PostRequest;
import com.example.demo.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post create(PostRequest request){
        return postRepository.save(new Post(request));
    }

    public Post getPost (Integer id) {
        return postRepository.findById(id.longValue()).orElse(null);
    }

    public void updatePost(Post post) {
        Post post1 = postRepository.getById(post.getId());
        post1.update(post.getTitle(), post.getBody());
        postRepository.save(post1);
    }

    public void removePost(Integer id) {
        boolean exists = postRepository.existsById(id.longValue());
        if(!exists) {
            throw new IllegalStateException("Post with id " + id + "doesn't exist");
        }
        postRepository.deleteById(id.longValue());
    }
}
