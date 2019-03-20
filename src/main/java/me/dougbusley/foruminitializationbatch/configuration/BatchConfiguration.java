package me.dougbusley.foruminitializationbatch.configuration;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dougbusley.foruminitializationbatch.model.Forum;
import me.dougbusley.foruminitializationbatch.model.Person;
import me.dougbusley.foruminitializationbatch.model.Post;
import me.dougbusley.foruminitializationbatch.repository.ForumRepository;
import me.dougbusley.foruminitializationbatch.repository.PersonRepository;
import me.dougbusley.foruminitializationbatch.repository.PostRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
@Slf4j
public class BatchConfiguration {

  private JobBuilderFactory jobBuilderFactory;

  private StepBuilderFactory stepBuilderFactory;

  private PersonRepository personRepository;

  private ForumRepository forumRepository;

  private PostRepository postRepository;

  private static final long NUMBER_OF_RECORDS_TO_LOAD = 100000;

  private static final Faker FAKER = Faker.instance();

  @Bean
  public Job loadTestDataJob(Step loadStep) {
    return jobBuilderFactory.get("loadTestDataJob").start(loadStep).build();
  }

  @Bean
  @JobScope
  public Step loadStep() {
    return stepBuilderFactory
        .get("loadStep")
        .tasklet(
            (stepContribution, chunkContext) -> {
              stepContribution.incrementWriteCount(1);
              Person person = new Person();
              person.setFirstName(FAKER.name().firstName());
              person.setLastName(FAKER.name().lastName());
              person.setUsername(FAKER.name().username());
              person.setPassword(FAKER.pokemon().name());
              person = personRepository.save(person);

              Forum forum = new Forum();
              forum.setTopic(FAKER.funnyName().name());
              forum = forumRepository.save(forum);

              Random r = new Random();
              int low = 0;
              int high = 100;
              int result = r.nextInt(high - low) + low;
              List<Post> posts = new ArrayList<>();

              for (int i = 0; i < result; i++) {
                Post post = new Post();
                post.setPerson(person);
                post.setMessage(FAKER.pokemon().location());
                post.setPerson(person);
                post.setForum(forum);
                posts.add(post);
              }
              postRepository.saveAll(posts);

              return RepeatStatus.continueIf(
                  chunkContext.getStepContext().getStepExecution().getWriteCount()
                      <= NUMBER_OF_RECORDS_TO_LOAD);
            })
        .build();
  }
}
