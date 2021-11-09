package com.example.demo.controllers;


import com.example.demo.entity.Admin;
import com.example.demo.models.AddPostsRequest;
import com.example.demo.models.AdminRequest;
import com.example.demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/create") public ResponseEntity create(@RequestBody AdminRequest adminRequest) {
        boolean result = adminService.saveAdmin(adminRequest);
        if (result) {
            return new ResponseEntity("Admin created", HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().body("bad request");
    }

    @PostMapping("/addPosts") public ResponseEntity addPosts(@RequestBody AddPostsRequest request) {
        adminService.addPostToAdmin(request.getAdminId(), request.getPostIds());
        return ResponseEntity.ok().body("");
    }

    @GetMapping("/get") public ResponseEntity getAdminById(@RequestParam Integer id) {
        Admin adminRequest = adminService.getAdmin(id);
        if (adminRequest == null) {
            return new ResponseEntity("Admin not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(adminRequest);
    }
}
