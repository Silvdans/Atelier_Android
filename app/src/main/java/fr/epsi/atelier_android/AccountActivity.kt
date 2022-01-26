package fr.epsi.atelier_android

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class AccountActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        showBack()

        val firstNameTextView = findViewById<TextView>(R.id.editTextFirstNameAccount)
        val lastNameTextView = findViewById<TextView>(R.id.editTextLastNameAccount)
        val emailTextView = findViewById<TextView>(R.id.editEmailAccount)
        val addressTextView = findViewById<TextView>(R.id.editTextAddressAcount)
        val zipcodeTextVIew = findViewById<TextView>(R.id.editTextZipcodeAccount)
        val cityTextView = findViewById<TextView>(R.id.editTextCityAccount)
        val buttonConfirm = findViewById<Button>(R.id.buttonConfirm)

        firstNameTextView.text = readSharedPref("firstName")
        lastNameTextView.text = readSharedPref("lastName")
        emailTextView.text = readSharedPref("email")
        addressTextView.text = readSharedPref("address")
        zipcodeTextVIew.text = readSharedPref("zipcode")
        cityTextView.text = readSharedPref("city")
        buttonConfirm.setOnClickListener(View.OnClickListener {
            writeSharedPref("firstName",firstNameTextView.text.toString())
            writeSharedPref("lastName",lastNameTextView.text.toString())
            writeSharedPref("email",emailTextView.text.toString())
            writeSharedPref("address",addressTextView.text.toString())
            writeSharedPref("zipcode",zipcodeTextVIew.text.toString())
            writeSharedPref("city",cityTextView.text.toString())
            val newIntent = Intent(application,FragmentActivity::class.java)
            startActivity(newIntent)
        })
    }
    fun writeSharedPref(key:String,value:String){
        val sharedPreferences: SharedPreferences= getSharedPreferences("account",Context.MODE_PRIVATE)
        val editor =sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences= getSharedPreferences("account",Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"not found").toString()
    }
}