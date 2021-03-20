package com.example.androiddetectappfrombackground

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    var localAppStartBeginTime: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        Log.i("TAG", "onCreate")

        findViewById<Button>(R.id.button_open_second).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    // Detect in onResume() instead of onStart because
    // onMoveToForeground() in MyApplication will fired before onStart
    override fun onResume() {
        super.onResume()
        if (isOpenedFirstTimeOrFromBackground()) {
            Log.i("TAGss", "open first time or from background")

            // do something: eg, call API
        } else {
            Log.i("TAGss", "on in another time")
        }
    }

    private fun isOpenedFirstTimeOrFromBackground(): Boolean {
        val globalStartBeginTime = (application as MyApplication).appStartBeginTime
        if (localAppStartBeginTime != globalStartBeginTime) {
            localAppStartBeginTime = globalStartBeginTime
            return true
        }
        return false
    }
}