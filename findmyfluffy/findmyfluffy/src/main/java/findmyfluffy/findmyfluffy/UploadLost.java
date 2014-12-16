package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.DatastoreInfo;
import findmyfluffy.findmyfluffy.AddCat;

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

public class UploadLost extends HttpServlet{
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
        	
        	//split string by newlines
        	String[] blobStringArray = blobString.split("\n");
        	
        	for(int i = 1; i < blobStringArray.length-1; i++ ){
        		
        		//System.out.println(blobStringArray[i]);
        		//split each line by ","
        		String[] blobStringLineArray = blobStringArray[i].split(",");
        		
        		AddCat add = new AddCat();
        		
        		//TO-DO: add checks around these
        		//Redo the way we get breeds/colors
        		//maybe add a function for this
        		add.addLostCatEntry(blobStringLineArray[6], add.isChipped(blobStringLineArray[3]), blobStringLineArray[5], blobStringLineArray[4], add.findBreed(blobStringLineArray[1]), add.findColor(blobStringLineArray[2]), blobStringLineArray[0], blobStringLineArray[7], blobStringLineArray[8]);
        		//System.out.println("name: " + blobStringLineArray[1] + " age:" + blobStringLineArray[6] + ", sex: " + blobStringLineArray[5]);
        	}
        	 	
            res.sendRedirect("/submit/data/?blob-key=" + blobKey.getKeyString());
        }
    }
}