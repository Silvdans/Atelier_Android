package fr.epsi.atelier_android

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        if(sharedPreferences.getBoolean("isCreated",false)){
            val newIntent = Intent(application,FragmentActivity::class.java)
            startActivity(newIntent)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageQRCode = findViewById<ImageView>(R.id.button_qr_code)
        imageQRCode.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application,QRCodeActivity::class.java)
            startActivity(newIntent)
        })
        val buttonForm = findViewById<Button>(R.id.button_form)
        buttonForm.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application,FormActivity::class.java)
            startActivity(newIntent)
        })
        setHeaderTitle("Création de compte")
    }
}