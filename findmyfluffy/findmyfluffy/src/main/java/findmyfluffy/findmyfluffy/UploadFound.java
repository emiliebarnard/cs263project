package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.MemCacheInfo;
import findmyfluffy.findmyfluffy.AddCat;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.memcache.ErrorHandlers;

public class UploadFound extends HttpServlet{
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        BlobKey blobKey = blobs.get("myFile");

        if (blobKey == null) {
            res.sendRedirect("/");
        } else {
        	//want to add info to our datastore
        	//get CSV file
        	String blobString =
        			   new String(blobstoreService.fetchData(blobKey, 0, BlobstoreService.MAX_BLOB_FETCH_SIZE-1));
        	//parsing the string
        	//split string by newlines
        	String[] blobStringArray = blobString.split("\n");
        	
        	String firstCatName = "meow";
    		AddCat add = new AddCat();
        	
        	for(int i = 1; i < blobStringArray.length-1; i++ ){
        		//split each line by ","
        		String[] blobStringLineArray = blobStringArray[i].split(",");
        		
        		if (i==1){
        			firstCatName = blobStringLineArray[0];
        		}
        		
        		//this adds the info
        		add.addFoundCatEntry(blobStringLineArray[0], blobStringLineArray[4], blobStringLineArray[2], blobStringLineArray[1], add.findBreed(blobStringLineArray[3]), add.findColor(blobStringLineArray[3]), blobStringLineArray[7], blobStringLineArray[5], blobStringLineArray[6]);
        	}
        	
        	//Memcache add:
        	MemCacheInfo.syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO)); 
        	MemCacheInfo.syncCache.put(blobKey.getKeyString(), firstCatName); // populate cache with name of the first cat
        	 	
            res.sendRedirect("/submit/data/?blob-key=" + blobKey.getKeyString());
        }
    }
}