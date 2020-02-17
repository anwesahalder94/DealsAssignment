package com.example.dealsassignment.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dealsassignment.R
import com.example.dealsassignment.data.model.Datum
import com.squareup.picasso.Picasso


class DealsAdapter(private val dealsList: MutableList<Datum?>, private val context: Context) :
    RecyclerView.Adapter<DealsAdapter.DealsViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): DealsViewHolder {

        var view = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.layout_child, viewGroup, false);

        return DealsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {

        if ((dealsList[position]?.image != null && dealsList[position]?.image != "")) {
            //Picasso.get().load(dealsList[position]?.image).into(holder.image)
            Glide.with(context).load(dealsList[position]?.image)
                .apply(RequestOptions.centerCropTransform()).into(holder.image)

        } else {
            Picasso.get().load("https://photos.wavebid.com/images/noimage.png").into(holder.image)
        }
        holder.textTitle.text = dealsList[position]?.title
        holder.textScore.text = dealsList[position]?.score.toString()
        holder.textComment.text = dealsList[position]?.commentsCount.toString()
    }

    override fun getItemCount(): Int {
        return dealsList.size
    }

    inner class DealsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.image_display)
        val textTitle: TextView = view.findViewById(R.id.text_title)
        val textScore: TextView = view.findViewById(R.id.text_score)
        val textComment: TextView = view.findViewById(R.id.text_comments_count)
        val textDate: TextView = view.findViewById(R.id.text_date)
        val textAtOther: TextView = view.findViewById(R.id.text_at_other)
    }

}