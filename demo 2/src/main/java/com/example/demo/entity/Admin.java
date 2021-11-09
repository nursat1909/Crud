package com.example.demo.entity;


import com.example.demo.models.AdminRequest;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String username;
    @OneToMany
    private List<Post> posts;

    public Admin(final String username) {
        this.username = username;
    }
}
