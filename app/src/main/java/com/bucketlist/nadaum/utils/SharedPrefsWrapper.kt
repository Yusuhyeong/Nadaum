package com.bucketlist.nadaum.utils

import android.content.SharedPreferences
import android.util.Log

class SharedPrefsWrapper(
    val appCache: SharedPreferences
) : PersistentKVStore {
    private val TAG = this::class.java.simpleName

    override fun remove(key: String): PersistentKVStore {
        editor.remove(key)
        return this
    }

    private val editor: SharedPreferences.Editor = appCache.edit()

    override fun getString(key: String, fallbackValue: String?): String? =
        appCache.getString(key, fallbackValue)

    override fun getLong(key: String, fallbackValue: Long): Long =
        appCache.getLong(key, fallbackValue)

    override fun putString(key: String, value: String): PersistentKVStore {
        editor.putString(key, value)
        return this
    }

    override fun putLong(key: String, value: Long): PersistentKVStore {
        editor.putLong(key, value)
        return this
    }

    override fun commit(): PersistentKVStore {
        editor.commit()
        return this
    }

    override fun apply(): PersistentKVStore {
        editor.apply()
        return this
    }
}