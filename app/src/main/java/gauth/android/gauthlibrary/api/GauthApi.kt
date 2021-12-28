package gauth.android.gauthlibrary.api

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import gauth.android.gauthlibrary.activities.SignInActivity
import gauth.android.gauthlibrary.activities.SignUpActivity
import gauth.android.gauthlibrary.data.SignIn
import gauth.android.gauthlibrary.data.SignUp
import gauth.android.gauthlibrary.data.Token
import gauth.android.gauthlibrary.listener.OnLoginClickListener
import gauth.android.gauthlibrary.listener.OnSignUpClickListener
import gauth.android.gauthlibrary.listener.SignInListener
import gauth.android.gauthlibrary.listener.SignUpListener
import gauth.android.gauthlibrary.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable
import kotlin.math.sign

class GauthApi : Serializable {

    private var apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://neon-dev.kro.kr:5083")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun signIn(activity: Activity, signInListener: SignInListener) {
        val intent = Intent(activity, SignInActivity::class.java)
        intent.putExtra("login", object : OnLoginClickListener {
            override fun signIn(id: String, password: String) {
                signIn(SignIn(id, password), signInListener)
            }

        })
        activity.startActivity(intent)
    }

    fun signUp(activity: Activity, signUpListener: SignUpListener) {
        val signUpActivity = SignUpActivity()
        val intent = Intent(activity, signUpActivity::class.java)
        intent.putExtra("signUp", object : OnSignUpClickListener {
            override fun signUp(
                id: String,
                password: String,
                email: String,
                school: String,
                birth: String,
                nickname: String,
                name: String
            ) {
                signUp(SignUp(id, password, email, school, birth, nickname, name), signUpListener)
            }
        })
        activity.startActivity(intent)
    }

    private fun signUp(signUp: SignUp, signUpListener: SignUpListener) {
        val call = apiService.signUp(signUp)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful)
                    signUpListener.onSuccess()
                else
                    signUpListener.onFail()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("sign up fail", "fail : ${t.message}")
            }
        })
    }

    private fun signIn(signIn: SignIn, signInListener: SignInListener) {
        val call = apiService.signIn(signIn)
        call.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if(response.isSuccessful)
                    response.body()?.let { signInListener.onSuccess(it) }
                else
                    signInListener.onFail()
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.d("sign in fail", "fail : ${t.message}")
            }

        })
    }
}