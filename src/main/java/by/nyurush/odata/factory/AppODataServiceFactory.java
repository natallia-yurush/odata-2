package by.nyurush.odata.factory;

import by.nyurush.odata.datasource.AppDataSource;
import org.apache.olingo.odata2.annotation.processor.core.ListsProcessor;
import org.apache.olingo.odata2.annotation.processor.core.datasource.AnnotationValueAccess;
import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.annotation.processor.core.datasource.ValueAccess;
import org.apache.olingo.odata2.annotation.processor.core.edm.AnnotationEdmProvider;
import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;

import static by.nyurush.odata.util.StringConstants.ENTITY_PACKAGE;

public class AppODataServiceFactory extends ODataServiceFactory {

    @Override
    public ODataService createService(ODataContext ctx) throws ODataException {
        EdmProvider edmProvider = new AnnotationEdmProvider(ENTITY_PACKAGE);
        ValueAccess valueAccess = new AnnotationValueAccess();
        DataSource dataSource = new AppDataSource();
        return createODataSingleProcessorService(edmProvider, new ListsProcessor(dataSource, valueAccess));
    }

}