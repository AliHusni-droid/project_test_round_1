package com.kelompok.projectsubmussion.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.kelompok.projectsubmussion.R
import kotlinx.android.synthetic.main.activity_reset.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ResetActivity : AppCompatActivity() {

    private val TAG = "ResetPassword"
    private var etEmail: EditText? = null
    private var btn_submit: Button? = null
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_reset)
        supportActionBar?.title = "Reset Password Page"
        initialise()
    }
    private fun initialise(){
        etEmail = findViewById(R.id.edt_reset_email)
        btn_submit = findViewById(R.id.btn_reset_email)
        mAuth = FirebaseAuth.getInstance()
        btn_submit?.onClick { sendPasswordResetEmail() }
    }
    private fun sendPasswordResetEmail(){
        val email = etEmail?.text.toString()
        if (!TextUtils.isEmpty(email)){
            mAuth!!
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val message = "Email send"
                        Log.d(TAG,message)
                        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
                        updateUI()
                    } else{
                        Log.w(TAG,task.exception?.message)
                        toast("No User Found With This Email")
                    }
                }
        } else {
            toast("Enter Email")
        }
    }
    private fun updateUI(){
        val intent = Intent(this@ResetActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    fun onClick(v: View){
        when(v){
            btn_back_reset -> startActivity<LoginActivity>()
            btn_exit_reset -> {
                alert("Are you sure you want to leave ?"){
                    noButton {
                        toast("You are not going out")
                        startActivity(intentFor<ResetActivity>())
                        finish()
                    }
                    yesButton {
                        toast("You made it out")
                        super.onBackPressed()
                    }
                }.show()
            }
        }
    }
}