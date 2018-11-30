package com.fs.dcc.mylibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.fs.dcc.mylibrary.interfaces.IToolbar

/**
 * Created by danielcintoconde on 29/10/18.
 */

open class ToolbarActivity : AppCompatActivity(), IToolbar {

    protected var _toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun toolbarToLoad(toolbar: Toolbar?) {

        _toolbar = toolbar
        _toolbar?.let { setSupportActionBar(_toolbar) }

    }

    override fun enableHomeDisplay(value: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }

    //Cometario de prueba

}