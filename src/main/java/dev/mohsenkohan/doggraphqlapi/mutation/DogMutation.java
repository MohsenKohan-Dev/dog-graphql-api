package dev.mohsenkohan.doggraphqlapi.mutation;

import dev.mohsenkohan.doggraphqlapi.entity.Dog;
import dev.mohsenkohan.doggraphqlapi.exception.NotFoundException;
import dev.mohsenkohan.doggraphqlapi.repository.DogRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class DogMutation implements GraphQLMutationResolver {

    private final DogRepository dogRepository;

    public DogMutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogsByBreed(String breed) {
        Iterable<Dog> allByBreed = dogRepository.findAllByBreed(breed);

        if (!allByBreed.iterator().hasNext())
            throw new NotFoundException("Breed Not Found", "invalidBreed", breed);

        dogRepository.deleteAll(allByBreed);
        return true;
    }

    public Dog updateDogName(String newName, Long id) {
        Dog dog = dogRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Dog Not Found", "invalidDogId", id));
        dog.setName(newName);
        return dogRepository.save(dog);
    }
}
