package fr.epsi.atelier_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class FormActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        setHeaderTitle("Création de compte")
        showBack()

        val firstName = intent.getStringExtra("firstName")
        val editFirstName = findViewById<EditText>(R.id.editTextFirstName)

        val lastName = intent.getStringExtra("lastName")
        val editLastName = findViewById<EditText>(R.id.editTextLastName)

        val email = intent.getStringExtra("email")
        val editEmail = findViewById<EditText>(R.id.editEmail)

        val address = intent.getStringExtra("address")
        val editAddress = findViewById<EditText>(R.id.editTextAddress)

        val zipcode = intent.getStringExtra("zipcode")
        val editZipcode = findViewById<EditText>(R.id.editTextZipcode)

        val city = intent.getStringExtra("city")
        val editCity = findViewById<EditText>(R.id.editTextCity)

        val cardRef = intent.getStringExtra("cardRef")
        val editCardRef = findViewById<EditText>(R.id.editTextRefCard)

        if(!(firstName.equals(null)))
        {
            editFirstName.setText(firstName)
            editLastName.setText(lastName)
            editEmail.setText(email)
            editAddress.setText(address)
            editZipcode.setText(zipcode)
            editCity.setText(city)
            editCardRef.setText(cardRef)
        }
    }
}