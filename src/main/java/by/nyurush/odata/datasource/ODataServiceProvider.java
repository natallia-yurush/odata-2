package by.nyurush.odata.datasource;

import by.nyurush.odata.entity.edm.CatEdm;
import by.nyurush.odata.entity.edm.DogEdm;
import by.nyurush.odata.entity.edm.PetEdm;
import by.nyurush.odata.entity.edm.UserEdm;
import by.nyurush.odata.service.CatODataService;
import by.nyurush.odata.service.DogODataService;
import by.nyurush.odata.service.ODataService;
import by.nyurush.odata.service.PetOdataService;
import by.nyurush.odata.service.UserODataService;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static by.nyurush.odata.util.StringConstants.ES_CATS_NAME;
import static by.nyurush.odata.util.StringConstants.ES_DOGS_NAME;
import static by.nyurush.odata.util.StringConstants.ES_PETS_NAME;
import static by.nyurush.odata.util.StringConstants.ES_USERS_NAME;
import static java.util.Optional.ofNullable;
import static org.apache.olingo.odata2.api.exception.ODataNotFoundException.ENTITY;

public class ODataServiceProvider {

    private final Map<String, ODataService> oDataServices = new HashMap<>();

    public ODataServiceProvider() {
        ODataService<UserEdm> userODataService = new UserODataService();
        ODataService<PetEdm> petODataService = new PetOdataService();
        ODataService<CatEdm> catODataService = new CatODataService();
        ODataService<DogEdm> dogODataService = new DogODataService();

        oDataServices.put(ES_USERS_NAME, userODataService);
        oDataServices.put(ES_PETS_NAME, petODataService);
        oDataServices.put(ES_CATS_NAME, catODataService);
        oDataServices.put(ES_DOGS_NAME, dogODataService);
    }

    public ODataService getODataEntityService(String entitySetName) throws ODataNotFoundException {
        return ofNullable(oDataServices.get(entitySetName))
                .orElseThrow(() -> new ODataNotFoundException(ENTITY));
    }
}
