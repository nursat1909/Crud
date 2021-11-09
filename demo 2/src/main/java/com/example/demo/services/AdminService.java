package com.example.demo.services;


import com.example.demo.entity.Admin;
import com.example.demo.entity.Post;
import com.example.demo.models.AdminRequest;
import com.example.demo.respository.AdminRepository;
import com.example.demo.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PostRepository postRepository;

    public boolean saveAdmin(AdminRequest adminRequest) {
        Admin admin = new Admin(adminRequest.getUsername());
        adminRepository.save(admin);
        return true;
    }

    public boolean addPostToAdmin(Integer adminId, List<Integer>postIds) {
        Optional<Admin> adminOptional = adminRepository.findById(adminId.longValue());
        Admin admin = adminOptional.orElse(null);
        if(admin == null) {
            return false;
        }
        List<Post> posts = new ArrayList<>();
        postIds.forEach(id -> postRepository.findById(id.longValue()).ifPresent(p -> posts.add(p)));
        admin.setPosts(posts);
        adminRepository.save(admin);
        return true;
    }

    public Admin getAdmin(Integer id) {
        return adminRepository.findById(id.longValue()).orElse(null);
    }

    public void removeProduct(Integer id) {
        boolean exists = adminRepository.existsById(id.longValue());
        if(!exists) {
            throw new IllegalStateException("Product with id " + id + "doesn't exist");
        }
        adminRepository.deleteById(id.longValue());
    }
}
