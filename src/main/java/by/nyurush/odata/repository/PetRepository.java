package by.nyurush.odata.repository;

import by.nyurush.odata.entity.jpa.Cat;
import by.nyurush.odata.entity.jpa.Dog;
import by.nyurush.odata.entity.jpa.Pet;
import by.nyurush.odata.entity.jpa.User;

import java.util.List;

public interface PetRepository {

    Pet save(Pet pet);

    void delete(Pet pet);

    List<Pet> findAll();

    Pet findById(Long id);

    List<Cat> findAllCats();

    List<Dog> findAllDogs();

    List<Pet> findAllByUser(User user);
}
