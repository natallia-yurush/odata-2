package by.nyurush.odata.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static by.nyurush.odata.util.StringConstants.CONTAINER_NAME;
import static by.nyurush.odata.util.StringConstants.ES_DOGS_NAME;
import static by.nyurush.odata.util.StringConstants.ET_DOG_NAME;
import static by.nyurush.odata.util.StringConstants.NAMESPACE;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Dog")
@DiscriminatorValue("Dog")
@EdmEntitySet(name = ES_DOGS_NAME, container = CONTAINER_NAME)
@EdmEntityType(name = ET_DOG_NAME, namespace = NAMESPACE)
public class Dog extends Pet {

}

