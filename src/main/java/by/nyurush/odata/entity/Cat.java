package by.nyurush.odata.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static by.nyurush.odata.util.StringConstants.CONTAINER_NAME;
import static by.nyurush.odata.util.StringConstants.ES_CATS_NAME;
import static by.nyurush.odata.util.StringConstants.ET_CAT_NAME;
import static by.nyurush.odata.util.StringConstants.NAMESPACE;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Cat")
@DiscriminatorValue("Cat")
@EdmEntitySet(name = ES_CATS_NAME, container = CONTAINER_NAME)
@EdmEntityType(name = ET_CAT_NAME, namespace = NAMESPACE)
public class Cat extends Pet {

}
