package dev.mohsenkohan.doggraphqlapi.repository;

import dev.mohsenkohan.doggraphqlapi.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {

    Iterable<Dog> findAllByBreed(String breed);
}
