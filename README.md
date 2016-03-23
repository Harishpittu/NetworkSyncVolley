# NetworkSyncVolley
VolleyNetworkCalls Made Easy


The library module networksyncvolley made Volley network calls is with single line of code.


Methods and Usages:

    public void Get(Object object, String Url, String EndPoint, String type, boolean Pagination, boolean store, String TAG) 
    
    
    object : you can pass this as null if you dont want to store that in realm.
    
          * The Object shout extends Realm Object to store the data.
          
   Url  : You need to pass your API URL.
   
      Example : http://jsonplaceholder.typicode.com/
   
   EndPoint  : You can pass endpoint .
   
      Example : posts/1/comments
    
   Type : specify response type.
   
      Example : Jsonobject    or   JsonArray   (you can use VolleyWrapper.JSONOBJECT  or VolleyWrapper.JSONARRAY   )
      
   Pagination :  you can enable pagination  by setting it as true.
   
   store :  you can store response data in Realm by setting it as true. 
   
   TAG : you can specify TAG  for Requestque .You can cancel the Requestque by using this TAG.
   
      Example :  volleyWrapper.CancelRequest(String TAG);
   

