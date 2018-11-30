package com.fs.dcc.mylibrary.interfaces

import android.support.v7.widget.Toolbar

/**
 * Created by danielcintoconde on 29/10/18.
 */
interface IToolbar {

    fun toolbarToLoad(toolbar: Toolbar?)
    fun enableHomeDisplay(value: Boolean)

}