package com.fs.dcc.finalapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fs.dcc.finalapp.utils.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if (isValidEmail(email) && isValidPassword(password)) {
                loginByEmail(email, password)
            } else {
                toast(R.string.message_data_incomplete)
            }

        }

        textViewForgotPassword.setOnClickListener {
            goToActivity<ForgotActivity>()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
        buttonCreateAccount.setOnClickListener {
            goToActivity<SignUpActivity>()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

        editTextEmail.validate {
            editTextEmail.error = if (isValidEmail(it)) null else getString(R.string.error_message_invalid_email)
        }

        editTextPassword.validate {
            editTextPassword.error = if (isValidPassword(it)) null else getString(R.string.error_message_invalid_password)
        }

    }

    private fun loginByEmail(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {task ->
            if (task.isSuccessful) {

                if (mAuth.currentUser!!.isEmailVerified) {
                    toast("User is now logged ni")
                } else {
                    toast(getString(R.string.message_email_not_confirm))
                }

            }else {
                toast(R.string.message_general_error)
            }
        }
    }

}
