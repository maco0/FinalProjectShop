package com.example.firebaseproject.Upload_Retrive

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseproject.R
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask


class UploadItem : AppCompatActivity() {
    lateinit var spinner: Spinner
    lateinit var arrayAdapter: ArrayAdapter<CharSequence>
    lateinit var tag: String
    lateinit var browseButton: Button
    lateinit var uploadButton: Button
    lateinit var productName: EditText
    lateinit var productDescription: EditText
    lateinit var contactDetails: EditText
    lateinit var image: ImageView
    lateinit var backButton:Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var user_db: StorageReference
    private var image_uri: Uri? = null
    private val reqCode: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        user_db = FirebaseStorage.getInstance().reference.child("uploads")

        setContentView(R.layout.activity_upload_item)
        this.initSpinner()
        this.initElements()
        this.initializeBrowse()
        this.initializeUpload()
        backButton.setOnClickListener{
            onBackPressed()
        }
    }

    private fun initSpinner() {
        spinner = findViewById(R.id.productTag)
        val tags = resources.getStringArray(R.array.tags)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tags)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                tag = tags[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun initElements() {
        browseButton = findViewById(R.id.BrowseButton)
        uploadButton = findViewById(R.id.UploadButton)
        productName = findViewById(R.id.productname)
        productDescription = findViewById(R.id.productDescription)
        contactDetails = findViewById(R.id.contactInformation)
        image = findViewById(R.id.image_view)
        backButton = findViewById(R.id.backbutton1)
    }

    private fun initializeUpload() {
        uploadButton.setOnClickListener {
            val productnameText = productName.text.toString()
            val productDescription = productDescription.text.toString()
            val contactInfo = contactDetails.text.toString()
            val tagstring = tag
            if (productnameText.isEmpty() || productDescription.isEmpty() || contactInfo.isEmpty()) {
                Toast.makeText(this, "please fill all values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (tagstring == "no Tag") {
                Toast.makeText(this, "make sure u assign tag to product", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (image_uri == null) {
                Toast.makeText(this, "no images loaded", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            addimage(productnameText,tagstring, productDescription, contactInfo)
        }
    }

    private fun initializeBrowse() {
        browseButton.setOnClickListener {
            this.openFile()
        }
    }

    private fun openFile() {
        val intent: Intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, reqCode)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == reqCode && resultCode == RESULT_OK && data != null && data.data != null) {
            image_uri = data.data;
            image.setImageURI(image_uri)
        }
    }

    private fun addimage(name: String, tag: String, desc: String, info: String) {


        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Upload")
        progressDialog.setMessage("Uploading")
        progressDialog.show()
        val fileRef =
                user_db!!.child(System.currentTimeMillis().toString() + ".jpg")

        var uploadTask: StorageTask<*>
        uploadTask = fileRef.putFile(image_uri!!)

        uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception.let {
                    throw it!!
                    progressDialog.dismiss()
                }
            }
            return@Continuation fileRef.downloadUrl
        })
                .addOnCompleteListener { task2 ->
                    if (task2.isSuccessful) {
                        Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()
                        val downloadUri = task2.result
                        var myUrl = downloadUri.toString()

                        val Firebaseref = FirebaseDatabase.getInstance().reference.child("user")
                        val id = Firebaseref.push().key
                        val productMap = HashMap<String, Any>()
                        productMap["itemName"] = name
                        productMap["itemType"] = tag
                        productMap["imgUrl"] = myUrl
                        productMap["productpublisher"] =id!!
                        productMap["itemDescription"] = desc
                        productMap["contactInfo"] = info
                        Firebaseref.child(id).updateChildren(productMap)


                        progressDialog.dismiss()

                    } else {

                        progressDialog.dismiss()
                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                    }
                }


    }

}
