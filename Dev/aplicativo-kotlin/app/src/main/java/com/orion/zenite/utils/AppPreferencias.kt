package com.orion.zenite.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferencias {
    // https://acomputerengineer.com/2020/02/11/sharedpreferences-in-android-kotlin-with-how-to-remember-user-login-session-example/
    // https://github.com/lomza/sharedpreferences-in-kotlin
    private const val NAME = "ZENITE"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    //SharedPreferences variables
    private val NIVEL = Pair("nivel", 0)
    private val TOKEN = Pair("token", "")
    private val ID = Pair("id", 0)
    private val IDLINHA = Pair("idLinha", 0)

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    fun clean() {
      preferences.edit {
          it.clear()
      }
    }

    //an inline function to put variable and save it
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    //SharedPreferences variables getters/setters
    var nivel: Int
        get() = preferences.getInt(NIVEL.first, NIVEL.second)
        set(value) = preferences.edit {
            it.putInt(NIVEL.first, value)
        }

    var token: String
        get() = preferences.getString(TOKEN.first, TOKEN.second) ?: ""
        set(value) = preferences.edit {
            it.putString(TOKEN.first, value)
        }

    var id: Int
        get() = preferences.getInt(ID.first, ID.second)
        set(value) = preferences.edit {
            it.putInt(ID.first, value)
        }

    var idLinha: Int
        get() = preferences.getInt(IDLINHA.first, IDLINHA.second)
        set(value) = preferences.edit{
            it.putInt(IDLINHA.first, value)
        }
}