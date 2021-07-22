package by.nyurush.odata.repository;

import by.nyurush.odata.entity.Cat;
import by.nyurush.odata.entity.Dog;
import by.nyurush.odata.entity.Pet;
import by.nyurush.odata.entity.User;

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
