package com.bucketlist.nadaum.network

import android.util.Log
import com.bucketlist.nadaum.data.BaseBucketList
import com.google.firebase.firestore.FirebaseFirestore

class FireStore {
    private val TAG = this::class.java.simpleName
    private val firestore = FirebaseFirestore.getInstance()

    fun setUid(uid: String, callback: (Boolean) -> Unit) {
        Log.d(TAG, "uid 추가, 추가 uid : $uid")
        val userRef = firestore.collection("user").document(uid)

        userRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    Log.d(TAG, "회원가입 스킵")
                    callback(true)
                } else {
                    Log.d(TAG, "회원가입")
                    userRef.set(mapOf("bucketList" to emptyList<Map<String, Map<String, Boolean>>>()))
                        .addOnSuccessListener {
                            Log.d(TAG, "$uid 회원가입 성공")
                            callback(true)
                        }
                        .addOnFailureListener { e ->
                            Log.e(TAG, "$uid 회원가입 실패", e)
                            callback(false)
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "firebase 오류", e)
                callback(false)
            }
    }

    fun saveBucketList(uid: String, date: String, bucketList: Map<String, Boolean>, callback: (Boolean) -> Unit) {
        val userRef = firestore.collection("user").document(uid)

        val newEntry = mapOf(date to bucketList)

        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(userRef)
            val currentList = snapshot.get("bucketList") as? List<Map<String, Map<String, Boolean>>> ?: emptyList()

            val updatedList = currentList + newEntry
            transaction.update(userRef, "bucketList", updatedList)
        }.addOnSuccessListener {
            Log.d(TAG, "버킷리스트 저장 성공")
            callback(true)
        }.addOnFailureListener { e ->
            Log.e(TAG, "버킷리스트 저장 실패", e)
            callback(false)
        }
    }

    fun getBucketList(uid: String, callback: (List<BaseBucketList>?) -> Unit) {
        val userRef = firestore.collection("user").document(uid)

        userRef.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val bucketListData = documentSnapshot.get("bucketList") as? List<Map<String, Map<String, Boolean>>>

                val baseBucketList = bucketListData?.mapNotNull { entry ->
                    val date = entry.keys.firstOrNull()
                    val todos = entry.values.firstOrNull()
                    if (date != null && todos != null) {
                        BaseBucketList(date, todos)
                    } else {
                        null
                    }
                }

                callback(baseBucketList)
            } else {
                Log.d(TAG, "문서가 존재하지 않습니다.")
                callback(null)
            }
        }.addOnFailureListener { e ->
            Log.e(TAG, "버킷리스트 조회 실패", e)
            callback(null)
        }
    }
}