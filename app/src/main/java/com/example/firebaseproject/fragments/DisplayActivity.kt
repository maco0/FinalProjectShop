package com.example.firebaseproject.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject.R
import com.example.firebaseproject.Upload_Retrive.recyclerAdapter
import com.example.firebaseproject.data.productsData
import com.google.firebase.database.*


class displayActivity : Fragment(R.layout.activity_display) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lateinit var recycleadapter: recyclerAdapter
        lateinit var recycleview: RecyclerView
        lateinit var listproducts: MutableList<productsData>
        lateinit var listproductsTest: MutableList<productsData>
        lateinit var all: ImageButton
        lateinit var music: ImageButton
        lateinit var parts: ImageButton
        lateinit var clothes: ImageButton
        lateinit var carParts: ImageButton
        lateinit var other: ImageButton
        var tag: String
        super.onViewCreated(view, savedInstanceState)
        all = view.findViewById(R.id.all)
        music = view.findViewById(R.id.music)
        parts = view.findViewById(R.id.parts)
        clothes = view.findViewById(R.id.clothes)
        other = view.findViewById(R.id.other)
        recycleview = view.findViewById(R.id.recycleView)
        carParts = view.findViewById(R.id.car_parts)
        val productlayoutManager = LinearLayoutManager(getActivity())
        productlayoutManager.reverseLayout = true
        productlayoutManager.stackFromEnd = true
        recycleview.layoutManager = productlayoutManager
        listproducts = ArrayList()
        listproductsTest = ArrayList()
        recycleadapter =
            requireActivity().let { recyclerAdapter(it, listproducts as ArrayList<productsData>) }
        recycleview.adapter = recycleadapter
        val posts = FirebaseDatabase.getInstance().reference.child("user")
        posts.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (p0 in snapshot.children) {
                    val post = p0.getValue(productsData::class.java)
                    if (post != null) {

                        listproducts.add(post!!)
                        listproductsTest.add(post!!)
                        recycleadapter!!.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        music.setOnClickListener {
            tag = "music"
            listproducts.clear()
            recycleadapter!!.notifyDataSetChanged()
            for (i in 0..listproductsTest.size - 1) {
                if (listproductsTest[i].GetitemType() == tag) {
                    listproducts.add(listproductsTest[i])
                    recycleadapter!!.notifyDataSetChanged()
                }
            }

        }
        all.setOnClickListener {
            tag = "all"
            listproducts.clear()
            recycleadapter!!.notifyDataSetChanged()
            for (i in 0..listproductsTest.size - 1) {
                listproducts.add(listproductsTest[i])
                recycleadapter!!.notifyDataSetChanged()
            }
        }
        other.setOnClickListener {
            tag = "other"
            listproducts.clear()
            recycleadapter!!.notifyDataSetChanged()
            for (i in 0..listproductsTest.size - 1) {
                if (listproductsTest[i].GetitemType() == tag) {
                    listproducts.add(listproductsTest[i])
                    recycleadapter!!.notifyDataSetChanged()
                }
            }
        }
        parts.setOnClickListener {
            tag = "computer parts"
            listproducts.clear()
            recycleadapter!!.notifyDataSetChanged()
            for (i in 0..listproductsTest.size - 1) {
                if (listproductsTest[i].GetitemType() == tag) {
                    listproducts.add(listproductsTest[i])
                    recycleadapter!!.notifyDataSetChanged()
                }
            }
        }
        carParts.setOnClickListener {
            tag = "car parts"
            listproducts.clear()
            recycleadapter!!.notifyDataSetChanged()
            for (i in 0..listproductsTest.size - 1) {
                if (listproductsTest[i].GetitemType() == tag) {
                    listproducts.add(listproductsTest[i])
                    recycleadapter!!.notifyDataSetChanged()
                }
            }
        }
        clothes.setOnClickListener {
            tag = "clothes"
            listproducts.clear()
            recycleadapter!!.notifyDataSetChanged()
            for (i in 0..listproductsTest.size - 1) {
                if (listproductsTest[i].GetitemType() == tag) {
                    listproducts.add(listproductsTest[i])
                    recycleadapter!!.notifyDataSetChanged()
                }
            }
        }
    }
}


