package me.dougbusley.foruminitializationbatch.repository;

import me.dougbusley.foruminitializationbatch.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}
