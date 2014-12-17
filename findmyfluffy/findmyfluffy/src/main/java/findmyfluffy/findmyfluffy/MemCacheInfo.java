package findmyfluffy.findmyfluffy;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 * MemCacheInfo class
 * 
 * In this class I make one memcache service, to be accessed by other classes.
 * 
 * @author emilie (Emilie Menard Barnard) - <a href="mailto:emilie@cs.ucsb.edu">emilie@cs.ucsb.edu</a>
 * @version 1.0
 *
 */
public class MemCacheInfo {
	public static MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
}
