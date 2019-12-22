package com.kelompok.projectsubmussion.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.kelompok.projectsubmussion.R
import org.jetbrains.anko.intentFor

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.title = "Welcome To My Project"

        Handler().postDelayed({
            finish()
            startActivity(intentFor<LoginActivity>())
        },5000)
    }
}
