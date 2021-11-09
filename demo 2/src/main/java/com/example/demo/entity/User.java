package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "usr")
@Data
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;
  @Column(columnDefinition = "TEXT")
  private String password;
  private String firstName;
  private String secondName;
  private String email;
  @OneToMany

  public boolean update(String password, String firstName, String secondName){
    try {
      this.password = password;
      this.firstName = firstName;
      this.secondName = secondName;
      return true;
    } catch (Error e) {
      System.out.println(e);
    }
    return false;
  }

  public User(final String password, final String firstName, final String secondName, final String email) {
    this.password = password;
    this.firstName = firstName;
    this.secondName = secondName;
    this.email = email;
  }
}
