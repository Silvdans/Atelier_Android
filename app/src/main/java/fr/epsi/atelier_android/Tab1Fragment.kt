package fr.epsi.atelier_android

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.res.ResourcesCompat
import com.google.zxing.BarcodeFormat
import com.google.zxing.oned.Code128Writer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Tab1Framgent.newInstance] factory method to
 * create an instance of this fragment.
 */
class Tab1Fragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val editLastName = view.findViewById<TextView>(R.id.last_name_text_view)
        val editFirstName = view.findViewById<TextView>(R.id.first_name_text_view)
        val cardRef :String = readSharedPref("cardRef")
        editFirstName.text = readSharedPref("firstName")
        editLastName.text = readSharedPref("lastName")
        displayBitmap(cardRef.toString())
    }
    private fun displayBitmap(value: String) {
        val widthPixels = resources.getDimensionPixelSize(R.dimen.width_barcode)
        val heightPixels = resources.getDimensionPixelSize(R.dimen.height_barcode)
        val imageBarcode = view?.findViewById<ImageView>(R.id.image_barcode)
        val textBarcodeNumber = view?.findViewById<TextView>(R.id.text_barcode_number)

        imageBarcode?.setImageBitmap(
            createBarcodeBitmap(
                barcodeValue = value,
                barcodeColor = ResourcesCompat.getColor(resources,R.color.black,null),
                backgroundColor = ResourcesCompat.getColor(resources,R.color.white,null),
                widthPixels = widthPixels,
                heightPixels = heightPixels
            )
        )
        if (textBarcodeNumber != null) {
            textBarcodeNumber.text = value
        }
    }

    private fun createBarcodeBitmap(
        barcodeValue: String,
        @ColorInt barcodeColor: Int,
        @ColorInt backgroundColor: Int,
        widthPixels: Int,
        heightPixels: Int
    ): Bitmap {
        val bitMatrix = Code128Writer().encode(
            barcodeValue,
            BarcodeFormat.CODE_128,
            widthPixels,
            heightPixels
        )

        val pixels = IntArray(bitMatrix.width * bitMatrix.height)
        for (y in 0 until bitMatrix.height) {
            val offset = y * bitMatrix.width
            for (x in 0 until bitMatrix.width) {
                pixels[offset + x] =
                    if (bitMatrix.get(x, y)) barcodeColor else backgroundColor
            }
        }

         val bitmap = Bitmap.createBitmap(
            bitMatrix.width,
            bitMatrix.height,
            Bitmap.Config.ARGB_8888
        )
        bitmap.setPixels(
            pixels,
            0,
            bitMatrix.width,
            0,
            0,
            bitMatrix.width,
            bitMatrix.height
        )
        return bitmap
    }
    fun readSharedPref(key:String):String{
        activity?.let{
            val sharedPreferences: SharedPreferences = it.getSharedPreferences("account", Context.MODE_PRIVATE)
            return sharedPreferences.getString(key,"not found").toString()
        }
        return ""

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Tab1Frament.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            Tab1Fragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}