package com.fs.dcc.finalapp.utils

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.fs.dcc.finalapp.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern

/**
 * Created by danielcintoconde on 19/11/18.
 */
fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, duration).show()

fun Activity.toast(resourceId: Int, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, resourceId, duration).show()

/*fun Activity.snackBar(message: CharSequence, view: View? = findViewById(R.id.container), duration: Int = Snackbar.LENGTH_SHORT,
                      action: String? = null, actionEvent: (v: View) -> Unit = {}) {
    if (view != null) {
        val snackBar = Snackbar.make(view, message, duration)
        if (!action.isNullOrEmpty()) {
            snackBar.setAction(action, actionEvent)
        }
        snackBar.show()
    }
}*/
fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)!!
/*fun ImageView.loadByURL(url: String) = Picasso.get().load(url).into(this)*/

//noinline es para decirle que queremos el contexto de el intent, por eso cuando usamos la funcion
// podemos llamar a los metodos de intent com los puExtra()
inline fun <reified T: Activity>Activity.goToActivity(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}

/*
fun Activity.goToActivityResult(action: String, requestCode: Int, init: Intent.() -> Unit = {}) {
    val intent = Intent(action)
    intent.init()
    startActivityForResult(intent, requestCode)
}*/

fun EditText.validate(validation: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            validation(editable.toString())
        }

        override fun beforeTextChanged(sequence: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(sequence: CharSequence?, start: Int, before: Int, count: Int) {

        }

    })
}


fun Activity.isValidEmail(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

fun Activity.isValidPassword(password: String): Boolean {
    //Necesita contener --> 1 número, 1 minuscula, 1 mayuscula, 1 especial, minimo 4 caracteres
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
    val pattern = Pattern.compile(passwordPattern)
    return pattern.matcher(password).matches()
}

fun Activity.isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}