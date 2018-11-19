package com.fs.dcc.finalapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fs.dcc.finalapp.utils.goToActivity
import com.fs.dcc.finalapp.utils.isValidEmail
import com.fs.dcc.finalapp.utils.toast
import com.fs.dcc.finalapp.utils.validate
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot.*

class ForgotActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        editTextEmail.validate {
            editTextEmail.error = if (isValidEmail(it)) null else getString(R.string.error_message_invalid_email)
        }

        buttonGoLogin.setOnClickListener {
            goToActivity<LoginActivity> {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        buttonForgot.setOnClickListener {

            val email = editTextEmail.text.toString()
            if (isValidEmail(email)) {

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(this) {
                    toast(getString(R.string.message_reset_password))
                    goToActivity<LoginActivity> {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }

            }else {
                toast(getString(R.string.message_mail_correct))
            }

        }

    }
}
