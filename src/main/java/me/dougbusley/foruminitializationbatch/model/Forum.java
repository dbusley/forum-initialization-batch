package me.dougbusley.foruminitializationbatch.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Forum {
  @Id
  @GeneratedValue
  private long id;

  private String topic;

  @OneToMany(mappedBy = "forum")
  private List<Post> posts;
}
