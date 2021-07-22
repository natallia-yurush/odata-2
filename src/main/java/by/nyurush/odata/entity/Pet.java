package by.nyurush.odata.entity;

import lombok.Data;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static by.nyurush.odata.util.StringConstants.CONTAINER_NAME;
import static by.nyurush.odata.util.StringConstants.ES_PETS_NAME;
import static by.nyurush.odata.util.StringConstants.ET_PET_NAME;
import static by.nyurush.odata.util.StringConstants.NAMESPACE;
import static by.nyurush.odata.util.StringConstants.ROLE_USER;
import static by.nyurush.odata.util.StringConstants.USER_PET_ASSOCIATION;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity.ONE;

@Data
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "Pet_Type")
@EdmEntitySet(name = ES_PETS_NAME, container = CONTAINER_NAME)
@EdmEntityType(name = ET_PET_NAME, namespace = NAMESPACE)
public abstract class Pet {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    @EdmKey
    @EdmProperty
    protected Long id;

    @EdmProperty
    @Column(name = "name")
    protected String name;

    @EdmProperty
    @Column(name = "age")
    protected Integer age;

    @EdmNavigationProperty(toMultiplicity = ONE,
            toType = User.class,
            association = USER_PET_ASSOCIATION,
            toRole = ROLE_USER
    )
    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;
}
