package com.fs.dcc.finalapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fs.dcc.finalapp.utils.*
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val mGoogleApiClient: GoogleApiClient by lazy { getGoogleApiClient() }

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

    private fun getGoogleApiClient(): GoogleApiClient {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        return  GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        toast(getString(R.string.message_connection_failed))
    }

}
