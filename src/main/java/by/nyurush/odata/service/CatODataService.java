package by.nyurush.odata.service;

import by.nyurush.odata.entity.EdmMapper;
import by.nyurush.odata.entity.edm.CatEdm;
import by.nyurush.odata.entity.jpa.Cat;
import by.nyurush.odata.repository.impl.PetRepositoryImpl;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import javax.transaction.Transactional;
import java.util.List;

import static by.nyurush.odata.util.StringConstants.ES_USERS_NAME;
import static java.util.stream.Collectors.toList;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class CatODataService implements ODataService<CatEdm> {

    @Override
    public Object getRelatedData(Object sourceData, String targetEntityName) throws ODataNotFoundException {
        CatEdm catEdm = (CatEdm) sourceData;
        if (ES_USERS_NAME.equals(targetEntityName)) {
            return catEdm.getUserEdm();
        }
        throw new ODataNotFoundException(ENTITY);
    }

    @Override
    public CatEdm getDataObject() {
        return new CatEdm();
    }

    @Override
    public List<CatEdm> findAll() {
        return PetRepositoryImpl.getInstance().findAllCats().stream()
                .map(EdmMapper::toCatEdm)
                .collect(toList());
    }

    @Override
    public CatEdm findById(long entityId) {
        return EdmMapper.toCatEdm((Cat) PetRepositoryImpl.getInstance().findById(entityId));
    }

    @Override
    public CatEdm save(CatEdm sourceData) throws ODataNotImplementedException {
        Cat cat = EdmMapper.fromCatEdm(sourceData);
        PetRepositoryImpl.getInstance().save(cat);
        return EdmMapper.toCatEdm(cat);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Cat cat = (Cat) PetRepositoryImpl.getInstance().findById(id);
        PetRepositoryImpl.getInstance().delete(cat);
    }
}
