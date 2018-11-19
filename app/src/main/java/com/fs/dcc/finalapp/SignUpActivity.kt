package com.fs.dcc.finalapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fs.dcc.finalapp.utils.goToActivity
import com.fs.dcc.finalapp.utils.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    //Declarar una intancia de Firebase Auth de inicializarla
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        buttonGoLogin.setOnClickListener {
            goToActivity<LoginActivity> {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }

        }

        buttonCreateAccount.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (isValidEmailAndPassword(email, password)) {
                signUpByEmail(email, password)
            } else {
                toast(R.string.message_data_incomplete)
            }

        }

    }

    private fun signUpByEmail(email: String, password: String) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                toast(R.string.message_send_email_confirm)
                goToActivity<LoginActivity> {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            } else {
                toast(R.string.message_general_error)
            }

        }

    }

    private fun isValidEmailAndPassword(email: String, password: String): Boolean {
        return !email.isNullOrEmpty() &&
                !password.isNullOrEmpty() &&
                password === editTextConfirmPassword.text.toString()
    }

}
