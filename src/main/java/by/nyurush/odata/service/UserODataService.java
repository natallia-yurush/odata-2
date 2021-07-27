package by.nyurush.odata.service;

import by.nyurush.odata.entity.EdmMapper;
import by.nyurush.odata.entity.edm.UserEdm;
import by.nyurush.odata.entity.jpa.User;
import by.nyurush.odata.repository.impl.PetRepositoryImpl;
import by.nyurush.odata.repository.impl.UserRepositoryImpl;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

import static by.nyurush.odata.util.StringConstants.ES_PETS_NAME;
import static java.util.stream.Collectors.toList;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class UserODataService implements ODataService<UserEdm> {

    @Override
    public Object getRelatedData(Object sourceData, String targetEntityName) throws ODataNotFoundException {
        User user = EdmMapper.fromUserEdm((UserEdm) sourceData);
        if (ES_PETS_NAME.equals(targetEntityName)) {
            return PetRepositoryImpl.getInstance().findAllByUser(user)
                    .stream()
                    .map(EdmMapper::toPetEdm)
                    .toList();
        }
        throw new ODataNotFoundException(ENTITY);
    }

    @Override
    public UserEdm getDataObject() {
        return new UserEdm();
    }

    @Override
    public List<UserEdm> findAll() {
        return UserRepositoryImpl.getInstance().findAll().stream()
                .map(EdmMapper::toUserEdm)
                .collect(toList());
    }

    @Override
    public UserEdm findById(long entityId) {
        return EdmMapper.toUserEdm(UserRepositoryImpl.getInstance().findById(entityId));
    }

    @Override
    @Transactional
    public UserEdm save(UserEdm sourceData) {
        User user = EdmMapper.fromUserEdm(sourceData);
        UserRepositoryImpl.getInstance().save(user);
        return EdmMapper.toUserEdm(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        User user = UserRepositoryImpl.getInstance().findById(id);
        UserRepositoryImpl.getInstance().delete(user);
    }
}
