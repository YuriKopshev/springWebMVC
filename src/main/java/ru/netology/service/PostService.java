package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class PostService {
  // сервис завязан на интерфейс, а не на конкретную реализацию
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public Collection<Post> all() {
    return repository.all();
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(repository.getById(id).orElseThrow(NotFoundException::new));
  }

  public Optional<Post> save(Post post) {
    return Optional.ofNullable(repository.save(post).orElseThrow(NotFoundException::new));
  }

  public void removeById(long id) {
    repository.removeById(id);
  }
}

