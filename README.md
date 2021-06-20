# SimpleShopapplication
this is the simple shop application program written in android studio using kotlin

# what it does?
---------------
this project allowes you to create user and login into application, where you can browse all products that have been displayed by other users and you can upload products yourself.
the product itself represents the item that you wish to sell/buy, it must have its own tag, name, description and contact info, so other users will be able to contact you and vice versa.
also you can sort these products by clicking buttons, that are displayed at the top of the products scene.
currently the type of products this app supports are: music, car parts, computer parts, clothes, other.
# How To Use It?
---------------
its basically very simple.
first you open the app and it displays registration page,where you can register, sign up or get new password if you have forgotten the old one. if you already have this application in your phone,you're registered and haven't clicked log out button, it automatically 
takes you to user profile page where you can upload image, change password or log out. you see three buttons, upload image button, change password button and log out button.
when clicked on upload image button, application takes you to upload image page, where you can browse image from your phone and upload it to database, keep in mind that you have to 
actually choose image, assign its type and write name,description and contact info in order to upload it to database, otherwise you wont be able to do so.
there is also back button which takes you to profile page. on the profile page you are able to see two icons at the bottom of the screen, that is bottom navigation menu.
these icons are shopping cart and user generic image. when you click on shopping cart, application takes you to new page where you can see all of the products that are displayed by all users.
there are several buttons at the top of the screen with different icons, each icon represents each product tag, you can filter the products by clicking these icons, for example:
if you want tosee only music related products, you click on music button and page only displays products that are identified by music tag. 
there is also button with text that says ALL , by clicking that button you get all types of products.
# How is it made?
---------------
this application is made in android studio with kotlin. 
it is connected to Firebase and has imported Navigation component.
the app itself combines several activities as well as fragments. in manifets, firtly displayed activity is LoginActivity, this activity has several attributes and is using Firebase Auth and 
Firebase DataBaseReference. we need these to create new user and store his/her name in database. this activity is connected to several other activities such as SignInActivity and forgotPasswordActivity
they all use Firebase Auth, to actually check if we already have that user in Authentication part of firebase(SignInActivity), and also we need to inform user email if he/she forgot password and is willing to
change it.
LogInActivity is also connected with this apps most important Activity, that is FragmentContainer.
FragmentContainer is an Activity that displays activity_fragment_container, that has attribute of type Fragment.Container, so with this activity we are able to display our fragments, that are
MainActivity and displayActivity.
in FragmentContainer we have reference to bottomNavigationMenu , and by using it with OnNavigationItemSelectedListener , we are able to change fragments, we also use supportFragmentManager
to replace framgents.
in MainActivity we see three BUttons and text that displays user name. these 3 buttons are:
1. upload picture -> takes you to UploadItem activity
2. Change Password -> takes you to ChangePassword activity // here you can  write new password in displayed EditText component and click update password button, also there is back button
that brings you back to MainActivity (Fragment Container)
3. log out -> logs user out of application and takes him/her to LogInActivity
----------------------
UploadItem Activity displays Three button, one dropDown Spinner, and 3 EditText component. Buttons are:
Browse-> lets user choose picture from phone, it uses Intent with type of image and requests content, we pass reqCode that must be positive integer and then we  start activity for result,
there is also onActivityResult callback that checks request and result codes as well as data, if neither of them are null, results are okay then it gets data of type Uri, that we put
into our image view. also there is addimage function where we place this image into storage and we upload product into database, in order to do so we have to have all required values filled,
so user has to actually browse image, set its tag, name, description and contactinfo.
we run two tasks, first task is required to place image into storage, secont task is used to display data in database, here we get firebase reference child of type "user" , so 
the data will be displayed on this branch, to display data we use HashMap with string keys and Any type data, keep in mind, that the key string must match defined file for data, otherwise
firebase wont be able to retrive data back.
----
 val id = Firebaseref.push().key here we use push to actually add new data and not overwrite others, and we store this key into id, so we can pass the key as data part to firebase,
 we will need this in order to retrive data, you can see usage in recyclerAdapter.
 ----
 recyclerAdapter is a special class that we use to generate data that will be displayed in recyclerview, so its just an adapter for recyclerview.
 recyclerAdapter(private val mContext: Context, private val products: List<productsData>) this class has two attributes, that are type of context and list type of productsData.
 productsData is a special class that only serves as a container for database, it must have empty constructor and constructor with all data, also all getters and setters for each data,
 so firebase can use them, keep in mind that data variables must be same as hashmap keys, otherwise it wont work.
onBindViewHolder - we assign data to each products , we index products by defined attribute of name position,
 Glide.with(mContext).load(productpost!!.GetimgUrl()).placeholder(R.drawable.ic_launcher_foreground).into(holder.imgUrl) - Glide helps us to easily display our uploaded image
 publisher function is used to retrive data from database and assign this data to the products list element on current index,
 val ref = FirebaseDatabase.getInstance().reference.child("user").child(publisherId) in UploadItem, we stored users id in publisher id , here we use this publisherid in order to retrive
 data, and after retriveing data we initialize "R.layout.product_data" that is an xml file that displays single product , so in recycler view we get a lot of "R.layout.product_data" with different
 datas from different users.
 --------
 displayActivity is a fragment class with R.layout.activity_display layout, in R.layout.activity_display layout we have recycler view, on view created we initialise buttons , get linearlayoutmanager
 and we create two lists, one list will store all database data as productList type and we will use another list to filter the first list and display filtered data.
 then we will pass context and list to recycleradapter and get Firebase reference with child type of "users"
 we will add valueevent listener to this firebase oject and for all its values we retrive data as productsData type, add it into list and notifyDataSetChanged(), that will notify observers 
 that data has been changed and update View. 
 so basically we get all data in "user" branch and add this data to list, then we notify adapter that data has been changed and adapter will redraw the view with passed list.
 and the buttons are used to filter this list
 




