package gauth.android.gauthlibrary.data

import java.io.Serializable

data class SignUp(
    val id : String,
    val password : String,
    val email : String,
    val school : String,
    val birth : String,
    val nickname: String,
    val name : String
) : Serializable