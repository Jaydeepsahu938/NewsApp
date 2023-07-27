package com.example.multipleviewrecyclerview


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleviewrecyclerview.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }
    lateinit var adapter:Adapter
    lateinit var recyclerView:RecyclerView
    lateinit var Button:Button
    var pageNo:Int=1
    var countryCode:String="us"
    lateinit var dataBase:NewsDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        recyclerView = mBinding.recyclerView
        dataBase=NewsDataBase.getDatabase(this@MainActivity)
        pageNo=1
        countryCode="us"
        getNews()
        Button=mBinding.button

        Button.setOnClickListener{
            if(pageNo==2)
            {
                Toast.makeText(this,"No more page Available",Toast.LENGTH_SHORT).show()
            }
            else
            {
                pageNo += 1
                getNews()
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getNews() {
        if(MyUtils.isInternateAvailable(applicationContext)) {
            val news = NewsService.newsInstance.getHeadlines(countryCode, pageNo)
            news.enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news_response = response.body()

                    if (news_response != null) {
                        GlobalScope.launch {
                            dataBase.NewsDao().insertNews(news_response.articles)
                        }
                        Log.d("TAG", news_response.toString())

                        adapter = Adapter(this@MainActivity, news_response.articles)
                        recyclerView.adapter = adapter
                    }
                }
                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("TAG", "Error in news fetching", t)
                }

            })
        }else
        {
            var news_data:List<Article>
            GlobalScope.launch {
                 news_data =dataBase.NewsDao().getNews()
                adapter= Adapter(this@MainActivity,news_data)
                recyclerView.adapter = adapter
            }

        }

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
     }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.argentina ->{
                countryCode="ar"
                getNews()
                Toast.makeText(this,"Argentina Selected",Toast.LENGTH_SHORT).show()
            }
            R.id.australia ->{
                countryCode="au"
                getNews()
                Toast.makeText(this,"Australia Selected",Toast.LENGTH_SHORT).show()
            }
            R.id.india ->{
                countryCode="in"
                getNews()
                Toast.makeText(this,"India Selected",Toast.LENGTH_SHORT).show()
            }
            R.id.america -> {
                countryCode="us"
                getNews()
                Toast.makeText(this,"America Selected",Toast.LENGTH_SHORT).show()
            }
            R.id.turkey ->{
                countryCode="tr"
                getNews()
                Toast.makeText(this,"Turkey Selected",Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}