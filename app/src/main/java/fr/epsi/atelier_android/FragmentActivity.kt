package fr.epsi.atelier_android

import android.os.Bundle
import android.view.View
import android.widget.TextView

class FragmentActivity : BaseActivity() {
    val tab1Fragment= Tab2Fragment.newInstance("yo1","yo1");
    val tab2Fragment= Tab3Fragment.newInstance("yo2","yo2");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val tab1=findViewById<TextView>(R.id.textViewTab1)
        val tab2=findViewById<TextView>(R.id.textViewTab2)
        val tab3=findViewById<TextView>(R.id.textViewTab3)
        showBack()

        tab1.setOnClickListener(View.OnClickListener {
            showTab1()
        })

        tab2.setOnClickListener(View.OnClickListener {
            showTab2()
        })
        tab3.setOnClickListener(View.OnClickListener {
            showTab3()
        })
        showTab1()
    }

    private fun showTab3() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("Tab3Fragment") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, MapsActivity::class.java, null)
        fragmentTransaction.commit()
    }

    private fun showTab1() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("Tab1Fragment") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, tab1Fragment, null)
        fragmentTransaction.commit()
    }

    private fun showTab2() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("Tab2Fragment") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, tab2Fragment, null)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>1)
            super.onBackPressed()
        else
            finish()
    }
}