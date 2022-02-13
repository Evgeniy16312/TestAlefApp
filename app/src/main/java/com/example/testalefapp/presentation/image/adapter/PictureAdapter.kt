package com.example.testalefapp.presentation.image.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testalefapp.R
import com.example.testalefapp.utils.inflater

class PictureAdapter(val pictureClickListener: OnPictureClickListener) :
    RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

    private val asyncPictureListDiffer = AsyncListDiffer(this, PictureDiffer)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view = parent.inflater(R.layout.image_item)
        return PictureViewHolder(view)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val picture = asyncPictureListDiffer.currentList[position]
        holder.bind(picture)
    }

    override fun getItemCount() = asyncPictureListDiffer.currentList.size

    fun submitList(pictures: List<String>) {
        asyncPictureListDiffer.submitList(pictures)
    }

    fun getItem(position: Int): String = asyncPictureListDiffer.currentList[position]

    inner class PictureViewHolder(private val imageView: View) : RecyclerView.ViewHolder(imageView),
        View.OnClickListener {
        private val imageViewPicture =
            this.imageView.findViewById<AppCompatImageView>(R.id.iv_photo)
        private var picture: String? = null

        fun bind(picture: String) {
            this.picture = picture
            imageViewPicture.setOnClickListener(this)
            Glide.with(imageView.context)
                .load(picture)
                .centerCrop()
                .error(R.drawable.ic_baseline_error_24)
                .into(imageViewPicture)
        }

        override fun onClick(v: View) {
            pictureClickListener.onPictureClicked(adapterPosition, imageViewPicture)
        }
    }

    object PictureDiffer : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = false

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }

    interface OnPictureClickListener {
        fun onPictureClicked(position: Int, imageView: View)
    }
}