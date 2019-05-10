package com.bihe0832.test

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.Toast
import com.bihe0832.test.LOG_TAG
import com.bihe0832.test.SDKTest


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SDKTest.onCreate()

    }
}
