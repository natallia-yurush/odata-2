package by.nyurush.odata.datasource;

import by.nyurush.odata.entity.Cat;
import by.nyurush.odata.entity.Dog;
import by.nyurush.odata.entity.Pet;
import by.nyurush.odata.entity.User;
import by.nyurush.odata.repository.impl.PetRepositoryImpl;
import by.nyurush.odata.repository.impl.UserRepositoryImpl;
import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.edm.EdmFunctionImport;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import java.util.List;
import java.util.Map;

import static by.nyurush.odata.util.StringConstants.ES_CATS_NAME;
import static by.nyurush.odata.util.StringConstants.ES_DOGS_NAME;
import static by.nyurush.odata.util.StringConstants.ES_PETS_NAME;
import static by.nyurush.odata.util.StringConstants.ES_USERS_NAME;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class AppDataSource implements DataSource {

    @Override
    public List<?> readData(EdmEntitySet entitySet) throws ODataNotFoundException, EdmException {
        String entitySetName = entitySet.getName();
        return switch (entitySetName) {
            case ES_PETS_NAME -> PetRepositoryImpl.getInstance().findAll();
            case ES_USERS_NAME -> UserRepositoryImpl.getInstance().findAll();
            case ES_CATS_NAME -> PetRepositoryImpl.getInstance().findAllCats();
            case ES_DOGS_NAME -> PetRepositoryImpl.getInstance().findAllDogs();
            default -> throw new ODataNotFoundException(ENTITY);
        };

    }

    @Override
    public Object readData(EdmEntitySet entitySet, Map<String, Object> keys) throws ODataNotFoundException, EdmException {
        String entitySetName = entitySet.getName();
        Long firstLayerEntityId = (Long) keys.get("Id");
        return switch (entitySetName) {
            case ES_PETS_NAME -> PetRepositoryImpl.getInstance().findById(firstLayerEntityId);
            case ES_USERS_NAME -> UserRepositoryImpl.getInstance().findById(firstLayerEntityId);
            default -> throw new ODataNotFoundException(ENTITY);
        };
    }

    @Override
    public Object readData(EdmFunctionImport function, Map<String, Object> parameters, Map<String, Object> keys) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public Object readRelatedData(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet, Map<String, Object> targetKeys) throws EdmException, ODataNotImplementedException {
        String sourceEntityName = sourceEntitySet.getName();
        String targetEntityName = targetEntitySet.getName();
        switch (sourceEntityName) {
            case ES_USERS_NAME -> {
                User user = (User) sourceData;
                return PetRepositoryImpl.getInstance().findAllByUser(user);
            }
            case ES_PETS_NAME -> {
                Pet pet = (Pet) sourceData;
                if (ES_USERS_NAME.equals(targetEntityName)) {
                    return pet.getUser();
                }
            }
        }
        throw new ODataNotImplementedException();
    }

    @Override
    public BinaryData readBinaryData(EdmEntitySet entitySet, Object mediaLinkEntryData) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public Object newDataObject(EdmEntitySet entitySet) throws ODataNotImplementedException, EdmException {

        String entitySetName = entitySet.getName();
        return switch (entitySetName) {
            case ES_CATS_NAME -> new Cat();
            case ES_USERS_NAME -> new User();
            case ES_DOGS_NAME -> new Dog();
            default -> throw new ODataNotImplementedException();
        };
    }

    @Override
    public void writeBinaryData(EdmEntitySet entitySet, Object mediaLinkEntryData, BinaryData binaryData) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public void deleteData(EdmEntitySet entitySet, Map<String, Object> keys) throws ODataNotImplementedException, EdmException {
        String entitySetName = entitySet.getName();
        Long id = (Long) keys.get("Id");
        switch (entitySetName) {
            case ES_PETS_NAME -> {
                Pet pet = PetRepositoryImpl.getInstance().findById(id);
                PetRepositoryImpl.getInstance().delete(pet);
            }
            case ES_USERS_NAME -> {
                User user = UserRepositoryImpl.getInstance().findById(id);
                UserRepositoryImpl.getInstance().delete(user);
            }
            default -> throw new ODataNotImplementedException();
        }
    }

    @Override
    public void createData(EdmEntitySet entitySet, Object data) throws ODataNotImplementedException, EdmException {
        String entitySetName = entitySet.getName();
        switch (entitySetName) {
            case ES_CATS_NAME -> {
                Pet pet = new Cat();
                PetRepositoryImpl.getInstance().save(pet);
            }
            case ES_USERS_NAME -> {
                User user = (User) data;
                UserRepositoryImpl.getInstance().save(user);
            }
            case ES_DOGS_NAME -> {
                Pet pet = new Dog();
                PetRepositoryImpl.getInstance().save(pet);
            }
            default -> throw new ODataNotImplementedException();
        }
    }

    @Override
    public void deleteRelation(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet, Map<String, Object> targetKeys) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }

    @Override
    public void writeRelation(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet, Map<String, Object> targetKeys) throws ODataNotImplementedException {
        throw new ODataNotImplementedException();
    }
}
