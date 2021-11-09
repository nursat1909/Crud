package com.example.demo.models;


import lombok.Data;

import java.util.List;

@Data
public class AddPostsRequest {
    private Integer adminId;
    private List<Integer> postIds;
}
