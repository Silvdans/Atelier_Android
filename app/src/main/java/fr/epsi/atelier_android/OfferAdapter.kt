package fr.epsi.atelier_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class OfferAdapter(val offers: ArrayList<Offer>): RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewDescription = view.findViewById<TextView>(R.id.textViewDescription)
        val imageViewPicture = view.findViewById<ImageView>(R.id.imageViewPicture)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cell_offer, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val offer = offers.get(position)
        holder.textViewName.text=offer.name
        holder.textViewDescription.text=offer.description
        Picasso.get().load(offer.picture_url).into(holder.imageViewPicture)

    }

    override fun getItemCount(): Int {
        return offers.size
    }
}