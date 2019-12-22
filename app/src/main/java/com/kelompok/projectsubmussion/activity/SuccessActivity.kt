package com.kelompok.projectsubmussion.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.bayreact.marvindcomunity.SharedPrefManager
import com.kelompok.projectsubmussion.R
import kotlinx.android.synthetic.main.activity_success.*
import org.jetbrains.anko.*

class SuccessActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_success)
        supportActionBar?.title = "Home Page Project"
        sharedPrefManager = SharedPrefManager(this)

    }
    override fun onClick(v: View?) {
        when(v){
            sign_out_button -> {
                alert ("Are you sure logout ? "){
                    noButton {
                        toast("You are not going out")
                        startActivity(intentFor<LoginActivity>())
                        finish()
                    }
                    yesButton {
                        if (sharedPrefManager.sPSudahLogin){
                            sharedPrefManager.saveBoolean(
                                SharedPrefManager.SP_SUDAH_LOGIN,
                                false
                            )
                            startActivity(
                                Intent(
                                    this@SuccessActivity,
                                    LoginActivity::class.java
                                )
                                    .addFlags(
                                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                                Intent.FLAG_ACTIVITY_NEW_TASK
                                    )
                            )
                            super.onBackPressed()
                        } else{
                            toast("You failed to logout")
                        }
                    }
                }.show()
            }
            btn_fruit_and_vegetable -> {
                startActivity(intentFor<HomePageActivity>())
            }
        }
    }
    companion object{
        fun getLaunchIntent(from: Context) = Intent(from, SuccessActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
