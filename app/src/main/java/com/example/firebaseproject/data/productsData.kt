package com.example.firebaseproject.data

import android.icu.text.StringPrepParseException

class productsData {
    var itemName: String = ""
    var itemType: String = ""
    var imgUrl: String = ""
    var itemDescription: String = ""
    var contactInfo: String = ""
    var productpublisher: String = ""
    constructor()
    constructor(itemName:String,itemType:String,imgUrl:String,itemDescription:String,contactInfo:String,productpublisher:String){
        this.itemName=itemName
        this.itemType=itemType
        this.imgUrl=imgUrl
        this.itemDescription=itemDescription
        this.contactInfo=contactInfo
        this.productpublisher = productpublisher
    }
    public fun GetitemName():String{
        return itemName
    }
    public fun GetitemType():String{
        return itemType
    }
    public fun GetimgUrl():String{
        return imgUrl
    }
    public fun GetitemDescription():String{
        return itemDescription
    }
    public fun Getproductpublisher():String{
        return productpublisher
    }
    public fun GetcontactInfo():String{
        return contactInfo
    }
    public fun SetitemName(itemName:String){
        this.itemName=itemName
    }
    public fun SetitemType(itemType:String){
        this.itemType = itemType
    }
    public fun SetimgUrl(imgUrl: String){
        this.imgUrl = imgUrl
    }
    public fun SetitemDescription(itemDescription: String){
        this.itemDescription = itemDescription
    }
    public fun SetcontactInfo(contactInfo: String){
        this.contactInfo = contactInfo
    }
    public fun Setproductpublisher(productpublisher: String){
        this.productpublisher = productpublisher
    }
}