package findmyfluffy.findmyfluffy;

import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class MemCacheInfo {
	public static MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
}
