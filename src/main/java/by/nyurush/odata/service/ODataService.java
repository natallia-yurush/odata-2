package by.nyurush.odata.service;

import by.nyurush.odata.entity.edm.EntityEdm;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

import java.util.List;

public interface ODataService<T extends EntityEdm> {

    <T extends EntityEdm> Object getRelatedData(Object sourceData, String targetEntityName) throws ODataNotFoundException, ODataNotImplementedException;

    T getDataObject();

    List<T> findAll();

    T findById(long entityId);

    T save(T sourceData) throws ODataNotImplementedException;

    void delete(long id);
}
