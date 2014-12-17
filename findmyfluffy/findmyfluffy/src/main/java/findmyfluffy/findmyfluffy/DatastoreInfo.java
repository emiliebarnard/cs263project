package findmyfluffy.findmyfluffy;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

/**
 * DatastoreInfo class
 * 
 * In this class I make one datastore service, to be accessed by other classes.
 * 
 * @author emilie (Emilie Menard Barnard) - <a href="mailto:emilie@cs.ucsb.edu">emilie@cs.ucsb.edu</a>
 * @version 1.0
 *
 */
public class DatastoreInfo {
	public static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
}
