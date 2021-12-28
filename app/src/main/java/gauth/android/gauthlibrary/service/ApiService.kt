package gauth.android.gauthlibrary.service

import gauth.android.gauthlibrary.data.SignIn
import gauth.android.gauthlibrary.data.SignUp
import gauth.android.gauthlibrary.data.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/v1/auth/signup")
    fun signUp(@Body signUpRequest: SignUp) : Call<Void>

    @POST("/api/v1/auth/login")
    fun signIn(@Body signIn: SignIn) : Call<Token>
}