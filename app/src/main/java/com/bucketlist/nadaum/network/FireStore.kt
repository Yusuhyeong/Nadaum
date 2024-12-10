package com.bucketlist.nadaum.network

import android.util.Log
import com.bucketlist.nadaum.data.BaseBucketList
import com.bucketlist.nadaum.data.BucketList
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FireStore {
    private val TAG = this::class.java.simpleName
    private val firestore = FirebaseFirestore.getInstance()

    fun setUid(uid: String, callback: (Boolean) -> Unit) {
        Log.d(TAG, "uid 추가, 추가 uid : $uid")

        val userRef = firestore.collection("user").document(uid)

        val emptyData = mapOf<String, Any>() // 빈 Map

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

    fun saveBucketList(uid: String, date: String, bucketList: List<BucketList>, callback: (Boolean) -> Unit) {
        val userDoc = firestore.collection("user").document(uid)

        val bucketListData = bucketList.map { bucket ->
            mapOf(bucket.todo to bucket.isTodo)
        }

        val data = mapOf("bucketList.$date" to bucketListData)

        userDoc.update(data)
            .addOnSuccessListener {
                Log.d(TAG, "Success, save bucketList")
                callback(true)
            }
            .addOnFailureListener { e ->
                Log.d(TAG, "Fail, save bucketList")
                callback(false)
            }
    }


    fun getBucketList(uid: String, callback: (List<BaseBucketList>?) -> Unit) {
        val userDoc = firestore.collection("user").document(uid)

        userDoc.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val gson = Gson()

                val bucketListJson = gson.toJson(document.get("bucketList"))

                val bucketListMap: Map<String, List<Map<String, Boolean>>>? = gson.fromJson(
                    bucketListJson,
                    object : TypeToken<Map<String, List<Map<String, Boolean>>>>() {}.type
                )

                if (bucketListMap != null) {
                    val baseBucketList = bucketListMap.map { entry ->
                        val date = entry.key
                        val bucketItems = entry.value.map { task ->
                            val todo = task.keys.firstOrNull().orEmpty()
                            val isTodo = task.values.firstOrNull() ?: false
                            BucketList(todo, isTodo)
                        }
                        BaseBucketList(date, bucketItems)
                    }
                    Log.d(TAG, "Success, get bucketList -> not empty")
                    callback(baseBucketList)
                } else {
                    Log.d(TAG, "Success, bucketList -> empty")
                    callback(emptyList())
                }
            } else {
                Log.d(TAG, "Fail, bucketList document -> null")
                callback(null)
            }
        }.addOnFailureListener { e ->
            Log.d(TAG, "Fail, $e")
            callback(null)
        }
    }

}