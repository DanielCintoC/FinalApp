  package com.fs.dcc.finalapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    //Declarar una intancia de Firebase Auth de inicializarla
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val currentUser = mAuth.currentUser
        if(currentUser == null) {
            Toast.makeText(this, "User is NOT logged in", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "User IS logged in", Toast.LENGTH_SHORT).show()
        }

    }

    private fun createAccount(email: String, password: String) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = mAuth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this@SignUpActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                    }

                }

    }

}
