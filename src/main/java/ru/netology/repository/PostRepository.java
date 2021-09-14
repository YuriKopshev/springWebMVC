package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private final ConcurrentHashMap<Long, Post> postMap = new ConcurrentHashMap<Long, Post>();
    private final AtomicLong idCount = new AtomicLong();


    public Collection<Post> all() {
        return postMap.values().stream().filter(post -> !post.isRemoved()).collect(Collectors.toList());
    }


    public Optional<Post> getById(long id) {
        final var post = postMap.get(id);
        if (post != null && post.isRemoved()) {
            return Optional.empty();
        }
        return Optional.ofNullable(post);
    }


    public Optional<Post> save(Post post) {
        long id = post.getId();
        if (id == 0) {
            id = idCount.incrementAndGet();
            post.setId(id);
        } else {
            if (!postMap.containsKey(id) || postMap.get(id).isRemoved()) {
                return Optional.empty();
            }
        }
        postMap.put(id, post);
        return Optional.of(post);
    }

    public void removeById(long id) {
        postMap.get(id).setRemoved(true);
    }
}
