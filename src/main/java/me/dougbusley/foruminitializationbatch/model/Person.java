package me.dougbusley.foruminitializationbatch.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Person {
  @Id @GeneratedValue private long id;

  private String firstName;

  private String lastName;

  private String username;

  private String password;

  @OneToMany(mappedBy = "person")
  private List<Post> posts;
}
