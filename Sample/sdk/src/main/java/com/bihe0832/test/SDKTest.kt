package com.bihe0832.test

import android.util.Log

/**
 *
 * @author zixie code@bihe0832.com
 * Created on 2019/5/9.
 * Description: Description
 *
 */
val LOG_TAG = "bihe0832"
object SDKTest {

    private var VERSION_CODES = 1

    fun onCreate() {
        Log.d(LOG_TAG, "SDKTest version code:$VERSION_CODES")
    }


    fun getUpperString(source: String?): String? {
        return source?.toUpperCase()
    }

    fun getLowerString(source: String?): String? {
        return source?.toLowerCase()
    }
}
