package com.example.demo.controllers;


import com.example.demo.entity.Post;
import com.example.demo.models.PostRequest;
import com.example.demo.respository.PostRepository;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/create")
    public Post create(@RequestBody PostRequest post){
        return postService.create(post);
    }

    @GetMapping("/get") public ResponseEntity getPostById(@RequestParam Integer id) {
        Post postRequest = postService.getPost(id);
        if (postRequest == null) {
            return new ResponseEntity("Post not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(postRequest);
    }

    @PostMapping("/update") public ResponseEntity updatePost(@RequestBody Post post) {
        postService.updatePost(post);
        return ResponseEntity.ok().body("Post updated");
    }

    @DeleteMapping("/delete") public ResponseEntity deletePost(@RequestParam Integer id) {
        postService.removePost(id);
        return ResponseEntity.ok().body("Post deleted!");
    }
}
