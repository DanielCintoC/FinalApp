package com.fs.dcc.finalapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fs.dcc.finalapp.utils.goToActivity
import kotlinx.android.synthetic.main.activity_forgot.*

class ForgotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        buttonGoLogin.setOnClickListener {
            goToActivity<LoginActivity>()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

    }
}
