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
import gauth.android.gauthlibrary.listener.SignInListener
import gauth.android.gauthlibrary.listener.SignUpListener
import gauth.android.gauthlibrary.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GauthApi {

    private var apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://neon-dev.kro.kr:5083")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun signIn(activity: Activity, signInListener: SignInListener) {
        val signInActivity = SignInActivity()
        signInActivity.setClickListener {
            val id = signInActivity.getBinding().editId.text.toString()
            val password = signInActivity.getBinding().editPassword.toString()

            if(id.isEmpty() || password.isEmpty()) {
                Toast.makeText(signInActivity, "id 혹은 password를 입력하세요", Toast.LENGTH_SHORT).show()
                return@setClickListener
            }

            signIn(SignIn(id, password), signInListener)
        }

        val intent = Intent(activity, signInActivity::class.java)
        activity.startActivity(intent)
    }

    fun signUp(activity: Activity, signUpListener: SignUpListener) {
        val signUpActivity = SignUpActivity()
        val binding = signUpActivity.getBinding()

        signUpActivity.setClickListener {
            val password = binding.editPassword.text.toString()
            val checkPassword = binding.editCheckPassword.text.toString()
            val id = binding.editId.text.toString()
            val name = binding.editName.text.toString()
            val nickname = binding.editNickname.text.toString()
            val email = binding.editEmail.text.toString()
            val school = binding.editSchool.text.toString()
            val birth = binding.editBirth.text.toString()

            if(id.isEmpty() || name.isEmpty() || nickname.isEmpty() || email.isEmpty() || school.isEmpty() || password.isEmpty() || checkPassword.isEmpty() || birth.isEmpty()) {
                Toast.makeText(signUpActivity, "입력을 확인헤주세요", Toast.LENGTH_SHORT).show()
                return@setClickListener
            }

            if(!password.equals(checkPassword)) {
                Toast.makeText(signUpActivity, "패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setClickListener
            }

            signUp(SignUp(id, password, email, school, birth, nickname, name), signUpListener)
        }
        val intent = Intent(activity, signUpActivity::class.java)
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