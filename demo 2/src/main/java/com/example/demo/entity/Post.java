package com.example.demo.entity;


import com.example.demo.models.PostRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor

public class Post{
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String title;
    private String body;

    public Post(PostRequest postRequest) {
        this.title = postRequest.getTitle();
        this.body = postRequest.getBody();
    }

    public boolean update(String title, String body){
        try {
            this.title = title;
            this.body = body;
            return true;
        } catch (Error e) {
            System.out.println(e);
        }
        return false;
    }

}
