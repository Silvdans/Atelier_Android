package fr.epsi.atelier_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MarketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market)

        val imageView = findViewById<ImageView>(R.id.imageViewMarket)
        val urlImage = intent.getStringExtra("urlImage")

        val zipecodeView = findViewById<TextView>(R.id.postalViewMarket)
        val zipecode = intent.getStringExtra("zipecode")

        val cityView = findViewById<TextView>(R.id.cityViewMarket)
        val city = intent.getStringExtra("city")

        val firstnameView = findViewById<TextView>(R.id.descriptionViewMarket)
        val firstname = intent.getStringExtra("description")

        Picasso.get().load(urlImage).into(imageView)
        zipecodeView.text = zipecode
        cityView.text = city
        firstnameView.text = firstname
        
    }
}