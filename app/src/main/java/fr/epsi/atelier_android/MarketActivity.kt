package fr.epsi.atelier_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MarketActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market)

        intent.getStringExtra("title")?.let { setHeaderTitle(it) }

        val imageView = findViewById<ImageView>(R.id.imageViewMarket)
        val urlImage = intent.getStringExtra("urlImage")

        val adresseView = findViewById<TextView>(R.id.adresseViewMarket)
        val adresse = intent.getStringExtra("adresse")

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