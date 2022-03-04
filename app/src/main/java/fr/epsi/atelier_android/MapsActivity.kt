package fr.epsi.atelier_android

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import org.json.JSONObject
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.*
import java.io.IOException

class MapsActivity : Fragment() {

    lateinit var googleMap :GoogleMap

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("MissingPermission")
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    )
    { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                googleMap.isMyLocationEnabled=true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                googleMap.isMyLocationEnabled=true
            } else -> {
            // No location access granted.
        }
        }
    }

    val market = "{\"stores\": [\n" +
            "        {\n" +
            "            \"storeId\":3456,\n" +
            "            \"name\": \"Le Grand marché\",\n" +
            "            \"description\": \"situé au centre de la capitale\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkZLcXb2slwlM0RGjyuiAj5jdF1_qrN-MBcA&usqp=CAU\",\n" +
            "            \"address\": \"10 Rue Merlot\",\n" +
            "            \"zipcode\":\"75000\",\n" +
            "            \"city\":\"Paris\",\n" +
            "            \"longitude\":2.338646,\n" +
            "            \"latitude\":48.854885\n" +
            "        },\n" +
            "        {\n" +
            "            \"storeId\":5856,\n" +
            "            \"name\": \"Marché de Lyon \",\n" +
            "            \"description\": \"situé au centre ville\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkZLcXb2slwlM0RGjyuiAj5jdF1_qrN-MBcA&usqp=CAU\",\n" +
            "            \"address\": \"10 Rue Merlot\",\n" +
            "            \"zipcode\":\"6900\",\n" +
            "            \"city\":\"Lyon\",\n" +
            "            \"longitude\":4.834604,\n" +
            "            \"latitude\":45.759132\n" +
            "        },\n" +
            "        {\n" +
            "            \"storeId\":9566,\n" +
            "            \"name\": \"Marché de Bordeaux\",\n" +
            "            \"description\": \"situé au centre ville\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDSfySJzTR0Wbzxi05hiZxQxdqW6AQA4rqA&usqp=CAU\",\n" +
            "            \"address\": \"Centre ville\",\n" +
            "            \"zipcode\":\"33000\",\n" +
            "            \"city\":\"Bordeaux\",\n" +
            "            \"longitude\":-0.57918,\n" +
            "            \"latitude\":44.837789\n" +
            "        },\n" +
            "        {\n" +
            "            \"storeId\":3566,\n" +
            "            \"name\": \"Marché de Pau\",\n" +
            "            \"description\": \"situé au centre ville\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDSfySJzTR0Wbzxi05hiZxQxdqW6AQA4rqA&usqp=CAU\",\n" +
            "            \"address\": \"Centre ville\",\n" +
            "            \"zipcode\":\"64000\",\n" +
            "            \"city\":\"Pau\",\n" +
            "            \"longitude\":-0.370797,\n" +
            "            \"latitude\":43.2951\n" +
            "        },\n" +
            "        {\n" +
            "            \"storeId\":4427,\n" +
            "            \"name\": \"Le Marché de Lille\",\n" +
            "            \"description\": \"situé au centre ville\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkZLcXb2slwlM0RGjyuiAj5jdF1_qrN-MBcA&usqp=CAU\",\n" +
            "            \"address\": \"Centre ville\",\n" +
            "            \"zipcode\":\"59000\",\n" +
            "            \"city\":\"Lille\",\n" +
            "            \"longitude\":3.063295,\n" +
            "            \"latitude\":50.608719\n" +
            "        },\n" +
            "        {\n" +
            "            \"storeId\":9536,\n" +
            "            \"name\": \"Marché de Nantes\",\n" +
            "            \"description\": \"situé au centre ville\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDSfySJzTR0Wbzxi05hiZxQxdqW6AQA4rqA&usqp=CAU\",\n" +
            "            \"address\": \"Centre ville\",\n" +
            "            \"zipcode\":\"44000\",\n" +
            "            \"city\":\"Nantes\",\n" +
            "            \"longitude\":-1.553621,\n" +
            "            \"latitude\":47.218371\n" +
            "         },\n" +
            "         {\n" +
            "            \"storeId\":9529,\n" +
            "            \"name\": \"Marché de Strasbourg\",\n" +
            "            \"description\": \"situé au centre ville\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDSfySJzTR0Wbzxi05hiZxQxdqW6AQA4rqA&usqp=CAU\",\n" +
            "            \"address\": \"Centre ville\",\n" +
            "            \"zipcode\":\"67000\",\n" +
            "            \"city\":\"Strasbourg\",\n" +
            "            \"longitude\":7.7521113,\n" +
            "            \"latitude\":48.5734053\n" +
            "         },\n" +
            "         {\n" +
            "            \"storeId\":9562,\n" +
            "            \"name\": \"Marché de Toulouse\",\n" +
            "            \"description\": \"situé au centre ville\",\n" +
            "            \"pictureStore\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEDSfySJzTR0Wbzxi05hiZxQxdqW6AQA4rqA&usqp=CAU\",\n" +
            "            \"address\": \"Centre ville\",\n" +
            "            \"zipcode\":\"31000\",\n" +
            "            \"city\":\"Toulouse\",\n" +
            "            \"longitude\":1.411209,\n" +
            "            \"latitude\":43.533513\n" +
            "        }\n" +
            "    ]\n" +
            "}"
    private val callback = OnMapReadyCallback { googleMap ->

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestUrl = "https://djemam.com/epsi/stores.json"
        val request = Request
            .Builder()
            .url(mRequestUrl)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()


        val call = okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {

                val data = response.body?.string()


            }
        })

        val jsonCities= JSONObject(market)
        val items=jsonCities.getJSONArray("stores")

        var marketAddress: String
        var marketName: String
        var marketZipcode= String()
        var marketDescription = String()
        var marketPicture= String()
        var marketCity= String()

        for(i in 0 until items.length()){
            val jsonCity= items.getJSONObject(i)
            val cityLatLng = LatLng(jsonCity.optDouble("latitude", 0.0), jsonCity.optDouble("longitude",0.0))
             marketAddress = jsonCity.optString("address")
             marketName = jsonCity.optString("name")
             marketZipcode = jsonCity.optString("zipcode")
             marketDescription = jsonCity.optString("description")
             marketPicture = jsonCity.optString("pictureStore")
             marketCity = jsonCity.optString("city")

            googleMap.addMarker(MarkerOptions()
                .position(cityLatLng)
                .title(marketName)
                .snippet(marketAddress))

        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.854885,2.338646),5f))

        googleMap.setOnMapClickListener {
            (activity as BaseActivity).showToast(it.toString())
        }
        googleMap.setOnInfoWindowClickListener {
            (activity as BaseActivity).showToast(it.title.toString())
            val newIntent = Intent(activity,MarketActivity::class.java)
            newIntent.putExtra("urlImage",marketPicture)
            newIntent.putExtra("zipecode",marketZipcode)
            newIntent.putExtra("city",marketCity)
            newIntent.putExtra("description",marketDescription)
            startActivity(newIntent)
        }



        this.googleMap=googleMap
        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
    }

override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_maps_fragment, container, false)
    }

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
    mapFragment?.getMapAsync(callback)
}
}


