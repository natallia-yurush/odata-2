package by.nyurush.odata.entity.edm;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import static by.nyurush.odata.util.StringConstants.CONTAINER_NAME;
import static by.nyurush.odata.util.StringConstants.ES_PETS_NAME;
import static by.nyurush.odata.util.StringConstants.ET_PET_NAME;
import static by.nyurush.odata.util.StringConstants.ET_USER_NAME;
import static by.nyurush.odata.util.StringConstants.NAMESPACE;
import static by.nyurush.odata.util.StringConstants.ROLE_USER;
import static by.nyurush.odata.util.StringConstants.USER_PET_ASSOCIATION;
import static org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity.ONE;

@Data
@SuperBuilder
@NoArgsConstructor
@EdmEntitySet(name = ES_PETS_NAME, container = CONTAINER_NAME)
@EdmEntityType(name = ET_PET_NAME, namespace = NAMESPACE)
public class PetEdm implements EntityEdm {

    @EdmKey
    @EdmProperty
    protected Long id;

    @EdmProperty
    protected String name;

    @EdmProperty
    protected Integer age;

    @EdmNavigationProperty(toMultiplicity = ONE,
            toType = UserEdm.class,
            association = USER_PET_ASSOCIATION,
            toRole = ROLE_USER,
            name = ET_USER_NAME
    )
    private UserEdm userEdm;
}
