package findmyfluffy.findmyfluffy;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class DatastoreInfo {
	public static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
}
