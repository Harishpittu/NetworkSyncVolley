# NetworkSyncVolley
VolleyNetworkCalls Made Easy


The library module networksyncvolley made Volley network calls is with single line of code.


Methods and Usages:

Basic NetWork Methods:

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
   
 public void GetPagination() 
 
     Use this method to get next page result.Make sure you set pagination is true in Get method.
     

    public void Delete(String Url, String EndPoint, String TAG) 

    public void Put(Object object, String Url, String EndPoint, String TAG)

    public void Patch(Object object, String Url, String EndPoint, String TAG) 

    public void Post(Object object, String Url, String EndPoint, String TAG) 
    
    
 Binding Methods:
    
    ModelViewBinding.Bind(Context context, int layoutid, Object object);
    ModelViewBinding.Bind(Activity activity, int layoutid, Object object);

        By Using this static method you can directly bind your model to the views.
        
         Views id's and  field names in the model should be the same.
        
        you need to pass context for listview OR acitivity for activity & layout resourceid & Model Object.
    
    
    ViewModelBinding.Bind(Activity activity, int layoutid, Object object);

        By Using this static method you can directly bind your Views to the Model.
           
           Views id's and  field names in the model should be the same.
        
        you need to pass context for listview OR acitivity for activity & layout resourceid & Model Object.
    
    
NetworkBind Methods:    
    
    There are other methods where  you can directly pass layout resource id and it will map that data to the model and create  
    Jsonobject and will post to the server with single of code.
    
        * No initializations required

    public void PostBind(Activity activity, int layoutid, Object object, String Url, String EndPoint, String TAG) 

    public void PutBind(Activity activity, int layoutid, Object object, String Url, String EndPoint, String TAG)
    
    
Network ImageLoader :

     VolleyWrapper.load(image url).placeholder(resourceid).errorplaceholder(resourceid).into(imageView);
 
you can use this static method for loading images from server.Usage is same like Picasso.

 
 *** You can find Sample Example Application in app folder.Just Import and run the project in AndroidStudio.
 
     

