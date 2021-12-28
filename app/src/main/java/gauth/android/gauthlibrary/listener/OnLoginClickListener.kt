package gauth.android.gauthlibrary.listener

import java.io.Serializable

interface OnLoginClickListener: Serializable {
    fun signIn(id: String, password: String)
}