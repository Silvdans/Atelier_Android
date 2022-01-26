package fr.epsi.atelier_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun showBack(){
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility= View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }
    fun setLogoHeader(){
        val logoHeader = findViewById<ImageView>(R.id.imageLogo)
        val textHeader = findViewById<TextView>(R.id.textViewTitle)
        val detailLogo = findViewById<ImageView>(R.id.detailImageView)
        logoHeader.visibility = View.VISIBLE
        textHeader.visibility = View.GONE
        detailLogo.visibility = View.VISIBLE
        detailLogo.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application,AccountActivity::class.java)
            startActivity(newIntent)
        })

    }
    fun showToast(txt : String){
        Toast.makeText(this,txt, Toast.LENGTH_SHORT).show()
    }
    fun setHeaderTitle(text:String){
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.text=text
    }
}