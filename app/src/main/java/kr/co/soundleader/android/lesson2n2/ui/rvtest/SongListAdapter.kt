package kr.co.soundleader.android.lesson2n2.ui.rvtest

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kr.co.soundleader.android.lesson2n2.data.model.api.ArticleList
import kr.co.soundleader.android.lesson2n2.R

class SongListAdapter(private val context: Context) :
    RecyclerView.Adapter<SongListAdapter.ViewHolder>() {
     var datas = ArrayList<ArticleList>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_songlist,parent,false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = datas.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(datas[position])


        }


    fun addData(newData: List<ArticleList>){
        datas.addAll(newData)

        notifyDataSetChanged()
    }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            private val layout_skeleton: ConstraintLayout = itemView.findViewById(R.id.layout_skeleton)
            private val iv_songCover: ImageView = itemView.findViewById(R.id.iv_songCover)
            private val tv_songTitle: TextView = itemView.findViewById(R.id.tv_songTitle)
            private val tv_songArtist: TextView = itemView.findViewById(R.id.tv_songArtist)


            fun bind(item: ArticleList) {

                layout_skeleton.visibility = View.VISIBLE

                Glide.with(itemView)
                    .load(item.coverImg)
                    .listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            tv_songTitle.text = item.title
                            tv_songArtist.text = item.artist

                            layout_skeleton.visibility = View.GONE
                            return false
                        }
                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            tv_songTitle.text = item.title
                            tv_songArtist.text = item.artist

                            layout_skeleton.visibility = View.GONE

                            return false
                        }
                    })
                    .error(R.color.skeleton_color_01)
                    .override(100,100)
                    .skipMemoryCache(false)
                    .into(iv_songCover)





            }
        }

    }

