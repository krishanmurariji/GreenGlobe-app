package com.SShayar.greenglobe.Models

class Post {
    var postUrl:String=""
    var caption:String=""
    var uid:String=""
    var time:String=""
    constructor()

    constructor(postUrl: String, caption: String) {
        this.postUrl = postUrl
        this.caption = caption
    }

    constructor(postUrl: String, caption: String, name: String, time: String) {
        this.postUrl = postUrl
        this.caption = caption
        this.uid = name
        this.time = time
    }


}