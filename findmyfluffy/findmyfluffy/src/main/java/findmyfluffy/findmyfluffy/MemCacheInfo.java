package findmyfluffy.findmyfluffy;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class MemCacheInfo {
	public static MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
}
