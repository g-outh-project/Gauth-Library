package gauth.android.gauthlibrary.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import gauth.android.gauthlibrary.R
import gauth.android.gauthlibrary.databinding.ActivitySignInBinding
import gauth.android.gauthlibrary.listener.OnLoginClickListener

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding
    private lateinit var onLoginClickListener: OnLoginClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        onLoginClickListener = intent.getSerializableExtra("login") as OnLoginClickListener

        binding.loginBtn.setOnClickListener {
            val id = binding.editId.text.toString()
            val password = binding.editPassword.toString()

            if(id.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "id 혹은 password를 입력하세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            onLoginClickListener.signIn(id, password)
        }
    }
}