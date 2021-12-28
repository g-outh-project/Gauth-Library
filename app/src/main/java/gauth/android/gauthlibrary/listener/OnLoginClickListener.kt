package gauth.android.gauthlibrary.listener

import java.io.Serializable

interface OnLoginClickListener {
    fun signIn(id: String, password: String)
}