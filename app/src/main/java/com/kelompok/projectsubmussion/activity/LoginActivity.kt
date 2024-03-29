package com.kelompok.projectsubmussion.activity

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ScrollView
import com.bayreact.marvindcomunity.DatabaseHelper
import com.bayreact.marvindcomunity.SharedPrefManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kelompok.projectsubmussion.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val rcSignIn: Int = 1
    private lateinit var firebaseAuth: FirebaseAuth
    private var sharedPrefManager: SharedPrefManager? = null
    private var db: SQLiteDatabase? = null
    private var openHelper: SQLiteOpenHelper? = null
    private var cursor: Cursor? = null
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOption: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_login)

        supportActionBar?.title = "Login Page"

        firebaseAuth = FirebaseAuth.getInstance()

        sharedPrefManager = SharedPrefManager(this)
        configureGoogleSignIn()
        setupUI()

        //anim
        val img: ScrollView = findViewById<View>(R.id.anim) as ScrollView
        img.setBackgroundResource(R.drawable.bg_gradient0)
        val frameAnimation = img.background as AnimationDrawable
        frameAnimation.setEnterFadeDuration(2000)
        frameAnimation.setEnterFadeDuration(4000)
        frameAnimation.start()

        if (sharedPrefManager?.sPSudahLogin!!){
            startActivity(
                Intent(this@LoginActivity, SuccessActivity::class.java)
                    .addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                Intent.FLAG_ACTIVITY_NEW_TASK
                    )
            )
            super.onBackPressed()
        }

        openHelper = DatabaseHelper(this)
        db = openHelper?.readableDatabase

        btn_login.onClick {
            val email = edt_email.text.toString().trim()
            val password = edt_password.text.toString().trim()
            sharedPrefManager?.saveSPString(SharedPrefManager.COL_2,email)
            sharedPrefManager?.saveSPString(SharedPrefManager.COL_3,password)

            if (email.isEmpty() || password.isEmpty()){
                if(validation()){
                    return@onClick
                }
            } else{
                cursor = db?.rawQuery(
                    "SELECT * FROM "+ DatabaseHelper.TABLE_NAME + " WHERE " +
                            DatabaseHelper.COL_2 + "=? AND " + DatabaseHelper.COL_3 + "=?",
                    arrayOf(email,password)
                )
                if (cursor != null){
                    if (cursor!!.count > 0){
                        startActivity<SuccessActivity>()
                        sharedPrefManager?.saveBoolean(
                            SharedPrefManager.SP_SUDAH_LOGIN,
                            true
                        )
                        toast("Login Success")
                    } else{
                        toast("Username and Password do not match")
                    }
                }
            }
        }
    }
    private fun validation():Boolean{
        when{
            edt_email.text.toString().isBlank() ->{
                edt_email.requestFocus()
                edt_email.error = "Email cannot be empty"
                return false
            }
            edt_password.text.toString().isBlank() ->{
                edt_password.requestFocus()
                edt_password.error = "The password must not be blank"
                return false
            }
            else -> return false
        }
    }
    override fun onClick(v: View?) {
        when(v){
            btn_register ->{
                startActivity<RegisterActivity>()
            }
            btn_reset ->{
                startActivity<ResetActivity>()
            }
        }
    }
    private fun configureGoogleSignIn(){
        mGoogleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOption)
    }
    private fun setupUI(){
        btn_login_google.onClick {
            signIn()
        }
    }
    private fun signIn(){
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,rcSignIn)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == rcSignIn){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            }catch (e: ApiException){
                toast("Google Sign In Failed :(")
            }
        }
    }
    private fun firebaseAuthWithGoogle(acct:GoogleSignInAccount){
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                startActivity(SuccessActivity.getLaunchIntent(this))
            } else{
                toast("Google Sign In Failed :(")
            }
        }
    }
    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null){
            startActivity(SuccessActivity.getLaunchIntent(this))
        }
    }
}
