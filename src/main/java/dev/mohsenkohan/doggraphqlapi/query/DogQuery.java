package dev.mohsenkohan.doggraphqlapi.query;

import dev.mohsenkohan.doggraphqlapi.entity.Dog;
import dev.mohsenkohan.doggraphqlapi.exception.NotFoundException;
import dev.mohsenkohan.doggraphqlapi.repository.DogRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class DogQuery implements GraphQLQueryResolver {

    private final DogRepository dogRepository;

    public DogQuery(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    public Dog findDogById(Long id) {
        return dogRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Dog Not Found", "invalidDogId", id));
    }
}
