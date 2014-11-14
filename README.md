cs263project: Find My Fluffy
============

Repo for my course project for CS 263: Programming Language Runtime Systems at UC Santa Barbara.
Find My Fluffy is a webservice intended to allow users to easily report lost and found cats, and will generate potential lost/found matched based on database entries.

The database is intended to be used by shelter staff. Because of this, those who report found cats (i.e. shelter staff) do not need to input any personal data. When a user inputs a found cat, it will generate possible matches of lost cats. Similarly, when a user inputs a lost cat, it will generate possible matches of found cats. At least one way to contact the owner is required when entering data regarding a lost cat, so that if and when a user inputs a matching found cat, that user can contact the owner.

This is different from other lost pet databases. Current databases require users to register, and focus on general pets. This will focus specifically on cats, which will enable the database to be more precise and thus more accurate. Registration is also not required to use find my fluffy.

This project will act as a stepping stone for the shelter I volunteer at before we integrate into a larger software. More info about the larger software can be found here: http://www.chameleonbeach.com/ProductsandServices/ChameleonCMS/tabid/90/Default.aspx

------------

NOTES:

-----------


From Google Developer:

Congratulations! Your project is ready. Your unique project ID is gold-box-728.

Deploy your app from your local dev environment using this command (make sure you're in the app engine folder first):

appcfg.sh -A gold-box-728 update target/appengine-try-java-1.0
After deploying your app, you can visit it with your browser at this URL:

gold-box-728.appspot.com

That's it! You're running on Google App Engine. Go to your project dashboard to see how your app is performing.

------------
This link is extremely helpful for getting the Google app engine set up in Eclipse: http://www.mkyong.com/google-app-engine/google-app-engine-hello-world-example-using-eclipse/
