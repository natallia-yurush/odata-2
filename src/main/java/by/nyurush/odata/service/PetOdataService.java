package by.nyurush.odata.service;

import by.nyurush.odata.entity.EdmMapper;
import by.nyurush.odata.entity.edm.PetEdm;
import by.nyurush.odata.entity.edm.UserEdm;
import by.nyurush.odata.entity.jpa.Pet;
import by.nyurush.odata.repository.impl.PetRepositoryImpl;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

import static by.nyurush.odata.util.StringConstants.ES_USERS_NAME;
import static java.util.stream.Collectors.toList;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class PetOdataService implements ODataService<PetEdm> {

    @Override
    public UserEdm getRelatedData(Object sourceData, String targetEntityName) throws ODataNotFoundException {
        PetEdm petEdm = (PetEdm) sourceData;
        if (ES_USERS_NAME.equals(targetEntityName)) {
            return petEdm.getUserEdm();
        }
        throw new ODataNotFoundException(ENTITY);
    }

    @Override
    public PetEdm getDataObject() {
        return new PetEdm();
    }

    @Override
    public List<PetEdm> findAll() {
        return PetRepositoryImpl.getInstance().findAll().stream()
                .map(EdmMapper::toPetEdm)
                .collect(toList());
    }

    @Override
    public PetEdm findById(long entityId) {
        return EdmMapper.toPetEdm(PetRepositoryImpl.getInstance().findById(entityId));
    }

    @Override
    @Transactional
    public PetEdm save(PetEdm sourceData) {
        Pet pet = EdmMapper.fromPetEdm(sourceData);
        PetRepositoryImpl.getInstance().save(pet);
        return EdmMapper.toPetEdm(pet);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Pet pet = PetRepositoryImpl.getInstance().findById(id);
        PetRepositoryImpl.getInstance().delete(pet);
    }

}