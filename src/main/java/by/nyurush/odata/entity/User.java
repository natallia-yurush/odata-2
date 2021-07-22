package by.nyurush.odata.entity;

import lombok.Data;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

import static by.nyurush.odata.util.StringConstants.CONTAINER_NAME;
import static by.nyurush.odata.util.StringConstants.ES_USERS_NAME;
import static by.nyurush.odata.util.StringConstants.ET_USER_NAME;
import static by.nyurush.odata.util.StringConstants.NAMESPACE;
import static by.nyurush.odata.util.StringConstants.ROLE_PET;
import static by.nyurush.odata.util.StringConstants.USER_PET_ASSOCIATION;
import static org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity.MANY;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.INT64;
import static org.apache.olingo.odata2.api.annotation.edm.EdmType.STRING;

@Data
@Entity
@EdmEntitySet(name = ES_USERS_NAME, container = CONTAINER_NAME)
@EdmEntityType(name = ET_USER_NAME, namespace = NAMESPACE)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @EdmKey
    @EdmProperty(type = INT64)
    private Long id;

    @EdmProperty(type = STRING)
    @Column(name = "name")
    private String name;

    @EdmProperty(type = STRING)
    @Column(name = "email")
    private String email;

    @EdmNavigationProperty(toMultiplicity = MANY,
            toType = Pet.class,
            association = USER_PET_ASSOCIATION,
            toRole = ROLE_PET)
    @OneToMany
    private Collection<Pet> pets;

}
