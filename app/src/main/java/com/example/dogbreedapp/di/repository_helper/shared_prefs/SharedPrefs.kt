package com.example.dogbreedapp.di.repository_helper.shared_prefs

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefs @Inject constructor(
    private val mSharedPreferences: SharedPreferences
): SharedPrefsHelper {

    companion object {
        const val IS_USER_LOGGED_IN = "is_user_logged_in"
    }

    /**
     * extension function to make call more generic
     * @sample  mSharedPreferences.get("token", "") \\ get
     * @sample  mSharedPreferences.edit{ set(Pair(ID, it?.data?.id))
     * */

    inline fun SharedPreferences.edit(operation: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        editor.operation()
        editor.apply()
    }

    fun SharedPreferences.Editor.set(pair: Pair<String, Any?>) {
        when (pair.second) {
            is String? -> putString(pair.first, pair.second as String?)
            is Int -> putInt(pair.first, pair.second as Int)
            is Boolean -> putBoolean(pair.first, pair.second as Boolean)
            is Float -> putFloat(pair.first, pair.second as Float)
            is Long -> putLong(pair.first, pair.second as Long)
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    inline fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    override fun saveUserLoggedIn(loggedIn: Boolean) {
        mSharedPreferences.edit {
            set(Pair(IS_USER_LOGGED_IN, loggedIn))
        }
    }

    override fun getUserLoggedIn(): Boolean? {
        return mSharedPreferences.get(IS_USER_LOGGED_IN, false) ?: false
    }
}