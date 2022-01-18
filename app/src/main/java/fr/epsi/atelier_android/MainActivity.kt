package fr.epsi.atelier_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageQRCode = findViewById<ImageView>(R.id.button_qr_code)
        imageQRCode.setOnClickListener(View.OnClickListener {
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
            intentIntegrator.initiateScan()
        })
        val buttonForm = findViewById<Button>(R.id.button_form)
        buttonForm.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application,FormActivity::class.java)
            startActivity(newIntent)
        })
        setHeaderTitle("Création de compte")
    }
}