package com.example.firebaseproject.Upload_Retrive
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firebaseproject.R
import com.example.firebaseproject.data.productsData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class recyclerAdapter(private val mContext: Context, private val products: List<productsData>): RecyclerView.Adapter<recyclerAdapter.ViewHolder>() {
    inner class ViewHolder(@NonNull itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var itemTag: TextView
        lateinit var itemName: TextView
        lateinit var itemDescripion: TextView
        lateinit var contactInfo: TextView
        lateinit var imgUrl: ImageView
        lateinit var publisherId:String
        init {
            itemName = itemView.findViewById(R.id.productNameDisplay)
            itemDescripion = itemView.findViewById(R.id.productDescDispay)
            contactInfo = itemView.findViewById(R.id.ContactinformationDisplay)
            imgUrl = itemView.findViewById(R.id.productImageDisplay)
            itemTag = itemView.findViewById(R.id.producttag)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.product_data,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var productpost = products[position]
        Glide.with(mContext).load(productpost!!.GetimgUrl()).placeholder(R.drawable.ic_launcher_foreground).into(holder.imgUrl)
        publisher(holder.itemTag,holder.contactInfo,holder.itemName,holder.itemDescripion,productpost.Getproductpublisher())
    }

    private fun publisher(itemTag:TextView,contactinfo:TextView,itemName:TextView,itemDescription: TextView,publisherId:String){
        val ref = FirebaseDatabase.getInstance().reference.child("user").child(publisherId)
        ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val post = snapshot.getValue<productsData>(productsData::class.java)
                    contactinfo.text = post!!.GetcontactInfo()
                    itemName.text = post!!.GetitemName()
                    itemDescription.text = post!!.GetitemDescription()
                    itemTag.text = post!!.GetitemType()

                }
            }

        })
    }

}