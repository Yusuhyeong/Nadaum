package com.bucketlist.nadaum.network

import android.util.Log
import com.bucketlist.nadaum.data.BaseBucketList
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FireStore {
    private val TAG = this::class.java.simpleName
    private val firestore = FirebaseFirestore.getInstance()

    fun setUid(uid: String, callback: (Boolean) -> Unit) {
        Log.d(TAG, "uid 추가, 추가 uid : $uid")
        val userRef = firestore.collection("user").document(uid)
        val emptyData = listOf<Any>()

        userRef.set(mapOf("bucketList" to emptyData))
            .addOnSuccessListener {
                Log.d(TAG, "Success, setUid : $uid")
                callback(true)
            }
            .addOnFailureListener { e ->
                Log.d(TAG, "Fail, setUid : $e")
                callback(false)
            }
    }

    fun saveBucketList(uid: String, date: String, bucketList: Map<String, Boolean>, callback: (Boolean) -> Unit) {

    }

    fun getBucketList(uid: String, callback: (List<BaseBucketList>?) -> Unit) {

    }
}