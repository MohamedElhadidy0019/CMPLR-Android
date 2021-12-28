package com.cmp.cmplr.Controller

import com.cmp.cmplr.DataClasses.ListBooleanPair
import com.cmp.cmplr.Model.HashtagModel

class HashtagController {
    var hashtagModel:HashtagModel= HashtagModel()

    suspend fun GetPostsBackend(hashtag:String?):ListBooleanPair{

        return hashtagModel.listReturn()
    }
}