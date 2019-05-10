package com.bihe0832.gradle.dependencies.check

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import com.bihe0832.test.LOG_TAG
import com.bihe0832.test.SDKTest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SDKTest.onCreate()

        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        }

        UpperBtn.setOnClickListener {
            showResult(SDKTest.getUpperString(userInput()))
        }
        LowerBtn.setOnClickListener {
            showResult(SDKTest.getLowerString(userInput()))
        }
    }


    private fun userInput(): String? {
        var input = md5String.text?.toString()
        return if (input?.isEmpty() == true) {
            Toast.makeText(this, "user input is bad!", Toast.LENGTH_LONG).show()
            ""
        } else {
            Log.d(LOG_TAG, "user input:$input")
            input
        }
    }

    private fun showResult(s: String?) {
        s?.let {
            Log.d(LOG_TAG, "showResult:$s")
            md5Result.text = "Result: $s"
        }
    }

}
