package by.nyurush.odata.entity.edm;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import java.util.List;

import static by.nyurush.odata.util.StringConstants.CONTAINER_NAME;
import static by.nyurush.odata.util.StringConstants.ES_PETS_NAME;
import static by.nyurush.odata.util.StringConstants.ES_USERS_NAME;
import static by.nyurush.odata.util.StringConstants.ET_USER_NAME;
import static by.nyurush.odata.util.StringConstants.NAMESPACE;
import static by.nyurush.odata.util.StringConstants.ROLE_PET;
import static by.nyurush.odata.util.StringConstants.USER_PET_ASSOCIATION;
import static org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity.MANY;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

@Data
@SuperBuilder
@NoArgsConstructor
@EdmEntitySet(name = ES_USERS_NAME, container = CONTAINER_NAME)
@EdmEntityType(name = ET_USER_NAME, namespace = NAMESPACE)
public class UserEdm implements EntityEdm {

    @EdmKey
    @EdmProperty(type = INT64)
    private Long id;

    @EdmProperty(type = STRING)
    private String name;

    @EdmProperty(type = STRING)
    private String email;

    @EdmNavigationProperty(toMultiplicity = MANY,
            toType = PetEdm.class,
            association = USER_PET_ASSOCIATION,
            toRole = ROLE_PET,
            name = ES_PETS_NAME)
    private List<PetEdm> petEdms;

}
