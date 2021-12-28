package gauth.android.gauthlibrary.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import gauth.android.gauthlibrary.R
import gauth.android.gauthlibrary.databinding.ActivitySignUpBinding
import gauth.android.gauthlibrary.listener.OnSignUpClickListener

class SignUpActivity(val onClickListener: OnSignUpClickListener) : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        binding.signUpBtn.setOnClickListener {
            val password = binding.editPassword.text.toString()
            val checkPassword = binding.editCheckPassword.text.toString()
            val id = binding.editId.text.toString()
            val name = binding.editName.text.toString()
            val nickname = binding.editNickname.text.toString()
            val email = binding.editEmail.text.toString()
            val school = binding.editSchool.text.toString()
            val birth = binding.editBirth.text.toString()

            if(id.isEmpty() || name.isEmpty() || nickname.isEmpty() || email.isEmpty() || school.isEmpty() || password.isEmpty() || checkPassword.isEmpty() || birth.isEmpty()) {
                Toast.makeText(this, "입력을 확인헤주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(!password.equals(checkPassword)) {
                Toast.makeText(this, "패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            onClickListener.signUp(id, password, email, school, birth, nickname, name)
        }
    }
}