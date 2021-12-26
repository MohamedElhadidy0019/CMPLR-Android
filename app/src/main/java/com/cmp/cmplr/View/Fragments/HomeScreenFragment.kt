package com.cmp.cmplr.View.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.Adapter.InfiniteScrollRecycler
import com.cmp.cmplr.Controller.LocalStorage
import com.cmp.cmplr.DataClasses.Blog
import com.cmp.cmplr.DataClasses.HomePostData
import com.cmp.cmplr.DataClasses.Post
import com.cmp.cmplr.Model.UserPost
import com.cmp.cmplr.R
import kotlinx.coroutines.launch

class HomeScreenFragment:Fragment() {
    //lateinit var mainHandler: Handler



    private var localStorage = LocalStorage()




    lateinit var rv_showData :RecyclerView
    //val infiniteScrollRecycler : InfiniteScrollRecycler = InfiniteScrollRecycler()
    var postsList: ArrayList<HomePostData> = ArrayList()
    var listsize:Int=0;

    val infiniteScrollRecycler : InfiniteScrollRecycler by lazy {
      Log.d("kak2","lazy eval")
       InfiniteScrollRecycler()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("kak2","view made")

        return inflater.inflate(R.layout.infinite_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("kak2","before super made")
//        lifecycleScope.launch{
//            Log.d("kak2","ya bro el lifescope222222222")
//        }
//        lifecycleScope.launchWhenCreated{
//            Log.d("kak2","ya bro el lifescope")
//        }
        super.onViewCreated(view, savedInstanceState)
        Log.d("kak2","after super made")
        var token:String?=localStorage.getTokenData(requireActivity())

        for (i in 1 ..5){
            val blog:Blog=Blog("https://assets.tumblr.com//images//default_avatar//cone_closed_128.png",
            "r",1, "theUsrName", true,"everyone");
            val content: String="this post for <b>test update and edit</b> for ahmed khaled"
            val date: String="Friday, 24-Dec-21 15:35:30 UTC"
            val is_liked: Boolean=false
            val post_id: Int=54
            val source_content: String="google.com"
            val state: String="publish"
            val type: String="photos"
            //val tags = MutableList<String>()
            val tags = listOf("summer", "winter", "Evans")
            val notes_count:Int=99
            val post_data:Post=Post(content,date,is_liked,post_id,source_content,state,tags,type,notes_count)

            var post:HomePostData= HomePostData(blog,post_data)
            postsList.add(post)
        }
        Log.d("kak2","after array made")


        Log.d("kak2","before adapter setting made")

        rv_showData=requireView().findViewById<RecyclerView>(R.id.theinfinte)

        Log.d("kak2","line1")
        infiniteScrollRecycler.putToken(token) //passing the token to the adapter
        rv_showData.adapter=infiniteScrollRecycler

        Log.d("kak2","line2")

        infiniteScrollRecycler.setList(postsList)
        infiniteScrollRecycler.notifydataSet()
        listsize=infiniteScrollRecycler.itemCount
        Log.d("kak2","after adapter setting made")
        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                //minusOneSecond()
                //Toast.makeText( context, "HH", Toast.LENGTH_SHORT).show()
                var temp:String="list size="+listsize.toString()+", recycler list = "+infiniteScrollRecycler.itemCount.toString()
                Log.d("kak2,","Interrupt nowwwwwwwwwww  "+ temp)
                if(listsize!=infiniteScrollRecycler.itemCount)
                {
                    listsize=infiniteScrollRecycler.itemCount
                    infiniteScrollRecycler.notifydataSet()
                }
                mainHandler.postDelayed(this, 10000)
            }
        })

        //Log.d("kak2",infiniteScrollRecycler.postList[0].name.toString())
       // Log.d("kak2", (rv_showData.adapter as InfiniteScrollRecycler).postList[0].name.toString())

/*
        user_pic.setOnClickListener{
            Toast.makeText( context, "user picture", Toast.LENGTH_SHORT).show()
        }
*/


    }



}
