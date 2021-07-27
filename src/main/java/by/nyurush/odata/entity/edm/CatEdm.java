package by.nyurush.odata.entity.edm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;

import static by.nyurush.odata.util.StringConstants.CONTAINER_NAME;
import static by.nyurush.odata.util.StringConstants.ES_CATS_NAME;
import static by.nyurush.odata.util.StringConstants.ET_CAT_NAME;
import static by.nyurush.odata.util.StringConstants.NAMESPACE;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EdmEntitySet(name = ES_CATS_NAME, container = CONTAINER_NAME)
@EdmEntityType(name = ET_CAT_NAME, namespace = NAMESPACE)
public class CatEdm extends PetEdm {

}
