package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.DatastoreInfo;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreInputStream;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class UploadFound extends HttpServlet {
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
        	
        	//TO DO: add check for CSV file
        	String blobString =
        			   new String(blobstoreService.fetchData(blobKey, 0, BlobstoreService.MAX_BLOB_FETCH_SIZE-1));
        	//parsing the string
        	//TO DO: split this into it's own function called ParseString or something
        	
        	//TO DO: refactor code so we have a create lost/create found entry
        	String[] blobStringArray = blobString.split("\n");
        	
        		System.out.println(blobString);
        	
            res.sendRedirect("/submit/data/?blob-key=" + blobKey.getKeyString());
        }
    }
}