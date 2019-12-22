package com.kelompok.projectsubmussion.activity

import android.content.ContentValues
import android.content.Intent
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
import com.kelompok.projectsubmussion.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.btn_register
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private var sharedPrefManager: SharedPrefManager? = null
    private var openHelper: SQLiteOpenHelper? = null
    private var db: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_register)

        supportActionBar?.title = "Register Page"

        tv_choose.onClick {
            val choose = listOf("Man","Woman")
            selector("Select Gender", choose){ dialog,i ->
                tv_selection.setText(choose[i])
                toast("Gender Selected")
            }
        }
        //anim
        val img: ScrollView = findViewById<View>(R.id.cs_anim) as ScrollView
        img.setBackgroundResource(R.drawable.bg_gradient0)
        val frameAnimation = img.background as AnimationDrawable
        frameAnimation.setEnterFadeDuration(2000)
        frameAnimation.setEnterFadeDuration(4000)
        frameAnimation.start()

        sharedPrefManager = SharedPrefManager(this)
        openHelper = DatabaseHelper(this)

        if (sharedPrefManager?.sPSudahLogin!!){
            startActivity(
                Intent(this@RegisterActivity,SuccessActivity::class.java)
                    .addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                Intent.FLAG_ACTIVITY_NEW_TASK)
            )
            super.onBackPressed()
        }
        btn_register.onClick {
            db = openHelper?.readableDatabase
            val email = edt_email_register.text.toString().trim()
            val password = edt_password_register.text.toString().trim()
            val gender = tv_selection.text.toString().trim()

            sharedPrefManager?.saveSPString(SharedPrefManager.COL_2, email)
            sharedPrefManager?.saveSPString(SharedPrefManager.COL_3,password)
            sharedPrefManager?.saveSPString(SharedPrefManager.COL_4,gender)
            if (email.isEmpty() || password.isEmpty()){
            }else{
                dataInsert(email,password,gender)
                toast("Register Success")
                startActivity(intentFor<LoginActivity>())
            }
            if (validation()){
                return@onClick
            }
        }
    }
    private fun dataInsert(email:String?, password:String?, gender:String?){
        val contentValues = ContentValues()
        contentValues.put(DatabaseHelper.COL_2,email)
        contentValues.put(DatabaseHelper.COL_3,password)
        contentValues.put(DatabaseHelper.COL_4,gender)
        db?.insert(DatabaseHelper.TABLE_NAME,null,contentValues)
    }
    private fun validation():Boolean{
        when{
            edt_email.text.toString().isBlank() ->{
                edt_email_register.requestFocus()
                edt_email_register.error = "Email cannot be empty"
                return false
            }
            edt_password.text.toString().isBlank() ->{
                edt_password_register.requestFocus()
                edt_password_register.error = "The password must not be blank"
                return false
            }
            else -> return false
        }
    }

   override fun onClick(v: View?) {
        when(v){
            btn_login_register ->{
                startActivity<LoginActivity>()
            }
        }
    }
}

