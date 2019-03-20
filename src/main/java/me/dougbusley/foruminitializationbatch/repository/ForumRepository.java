package me.dougbusley.foruminitializationbatch.repository;

import me.dougbusley.foruminitializationbatch.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Long> {}
