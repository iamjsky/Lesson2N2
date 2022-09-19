package kr.co.soundleader.android.lesson2n2.ui.classroom.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kr.co.soundleader.android.lesson2n2.data.model.api.ArticleList
import kr.co.soundleader.android.lesson2n2.R
import kr.co.soundleader.android.lesson2n2.di.repository.DataRepository
import kr.co.soundleader.android.lesson2n2.ui.curriculum.activity.CurriculumDetailActivity
import kr.co.soundleader.android.lesson2n2.ui.mypage.activity.MyPageCurriculumDetailTeacherActivity
import org.koin.java.KoinJavaComponent.inject

class ClassRoomTeacherChatListAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val dataRepo : DataRepository by inject(DataRepository::class.java)

    private val HEADER = 0 // 헤더 뷰
    private val ITEM = 1 // 리사이클러 아이템 뷰
    private val EMPTY = 2 // 데이터가 없을 때 뜨는 뷰


     var datas = ArrayList<ArticleList>()


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            //val view = LayoutInflater.from(context).inflate(R.layout.item_teacher_list,parent,false)
            return when (viewType) {
                HEADER ->
                    HeaderViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_recycler_view_header,parent,false)
                    )
                ITEM ->
                    ItemViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_chat_bubble_list,parent,false)
                    )
                EMPTY -> // EMPTY
                    EmptyViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_recycler_view_empty,parent,false)
                    )
                else -> {
                    throw ClassCastException("Unknown viewType $viewType")
                }
            }
        }

        override fun getItemCount(): Int{
            return if (datas.size == 0) 2 else datas.size + 1
        }

    var headerVisibleState:Boolean = true

    fun setHeaderVisible(value:Boolean){
        headerVisibleState = value
    }

    override fun getItemViewType(position: Int): Int {
        return if (datas.size != 0) {
            if (position == 0) HEADER else ITEM
        } else {
            if (position == 0) HEADER else EMPTY
        }
    }
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            when(holder){
                //HEADER
                is HeaderViewHolder -> {
                    holder.bind("커리큘럼")
                }
                //ITEM
                is ItemViewHolder -> {
                    holder.bind(datas[position-1])
                }
                //EMPTY
                is EmptyViewHolder -> {

                }
            }



        }


    fun addData(newData: List<ArticleList>){
        datas.addAll(newData)

        notifyDataSetChanged()
    }
    // 헤더 부분에 해당하는 뷰객체 가지는 뷰홀더
   inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val layout_header: LinearLayout = itemView.findViewById(R.id.layout_header)
        private val tv_header_title: TextView = itemView.findViewById(R.id.tv_header_title)

        fun bind(title: String) {
            if(headerVisibleState){
                layout_header.visibility = View.VISIBLE
            }else{
                layout_header.visibility = View.GONE
            }
            tv_header_title.text = title+""
        }
   }


    // 데이터가 없을 때 보여줄 부분에 해당하는 뷰객체 가지는 뷰홀더
    class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {

        }
    }

    // 항목에 해당하는 뷰객체 가지는 뷰홀더
        inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
                private val layout_sender: ConstraintLayout = itemView.findViewById(R.id.layout_sender)
             private val layout_receiver: ConstraintLayout = itemView.findViewById(R.id.layout_receiver)
        private val tv_sender_chat_content: TextView = itemView.findViewById(R.id.tv_sender_chat_content)
        private val tv_receiver_chat_content: TextView = itemView.findViewById(R.id.tv_receiver_chat_content)



//            private val layout_skeleton: ConstraintLayout = itemView.findViewById(R.id.layout_skeleton)
//            private val iv_songCover: ImageView = itemView.findViewById(R.id.iv_songCover)
//            private val tv_songTitle: TextView = itemView.findViewById(R.id.tv_songTitle)
//            private val tv_songArtist: TextView = itemView.findViewById(R.id.tv_songArtist)


            fun bind(item: ArticleList) {
                if(item.idx!!.toInt() % 2 == 0){
                    layout_sender.visibility = View.VISIBLE
                    layout_receiver.visibility = View.GONE
                    tv_sender_chat_content.text = item.title
                }else{
                    layout_sender.visibility = View.GONE
                    layout_receiver.visibility = View.VISIBLE
                    tv_receiver_chat_content.text = item.title
                }

              //  layout_skeleton.visibility = View.VISIBLE
              //  iv_songCover.setOnClickListener {

                   // Log.d(TAG, pos.toString() + "c : " + getItemCount())
             //   }



//                Glide.with(itemView)
//                    .load(item.coverImg)
//                    .listener(object : RequestListener<Drawable>{
//                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                            tv_songTitle.text = item.title
//                            tv_songArtist.text = item.artist
//
//                            layout_skeleton.visibility = View.GONE
//                            return false
//                        }
//                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                            tv_songTitle.text = item.title
//                            tv_songArtist.text = item.artist
//
//                            layout_skeleton.visibility = View.GONE
//
//                            return false
//                        }
//                    })
//                    .error(R.color.skeleton_color_01)
//                    .override(100,100)
//                    .skipMemoryCache(false)
//                    .into(iv_songCover)





            }
        }

    }

