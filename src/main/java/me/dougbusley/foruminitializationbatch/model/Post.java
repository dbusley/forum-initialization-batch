package me.dougbusley.foruminitializationbatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Post {
  @Id
  @GeneratedValue
  private long id;

  @Column(columnDefinition = "text")
  private String message;

  @ManyToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private Person person;

  @ManyToOne @JoinColumn private Forum forum;
}
