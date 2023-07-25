package com.example.multipleviewrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleviewrecyclerview.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private val mBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        val datalist =ArrayList<Data>()
        datalist.add(Data(1,this.getString(R.string.str1),null))
        datalist.add(Data(1,this.getString(R.string.str2),null))
        datalist.add(Data(2,this.getString(R.string.str12),R.drawable.img_1))
        datalist.add(Data(2,this.getString(R.string.str11),R.drawable.img2))
        datalist.add(Data(1,this.getString(R.string.str3),null))
        datalist.add(Data(2,this.getString(R.string.str10),R.drawable.img3))
        datalist.add(Data(1,this.getString(R.string.str1),null))
        datalist.add(Data(2,this.getString(R.string.str9),R.drawable.img4))
        datalist.add(Data(2,this.getString(R.string.str8),R.drawable.img5))
        datalist.add(Data(1,this.getString(R.string.str2),null))
        datalist.add(Data(1,this.getString(R.string.str3),null))
        datalist.add(Data(1,this.getString(R.string.str4),null))
        datalist.add(Data(2,this.getString(R.string.str7),R.drawable.img6))
        datalist.add(Data(2,this.getString(R.string.str6),R.drawable.img7))
        datalist.add(Data(1,this.getString(R.string.str12),null))
        datalist.add(Data(2,this.getString(R.string.str5),R.drawable.img8))
        datalist.add(Data(2,this.getString(R.string.str4),R.drawable.img9))
        datalist.add(Data(1,this.getString(R.string.str7),null))
        datalist.add(Data(2,this.getString(R.string.str3),R.drawable.img10))
        datalist.add(Data(2,this.getString(R.string.str2),R.drawable.img11))
        datalist.add(Data(1,this.getString(R.string.str6),null))
        datalist.add(Data(1,this.getString(R.string.str7),null))
        datalist.add(Data(1,this.getString(R.string.str8),null))
        datalist.add(Data(2,this.getString(R.string.str1),R.drawable.img12))

        val adapter=Adapter(this,datalist)
        val recyclerView=mBinding.recyclerView
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=adapter

        val addbtn=mBinding.add
        addbtn.setOnClickListener{
            recyclerView.getLayoutManager()?.scrollToPosition(2)
            datalist.add(2,Data(2,this.getString(R.string.str1),R.drawable.img12))
            adapter.notifyItemInserted(2)
        }

        val deletebtn=mBinding.delete

        deletebtn.setOnClickListener{
            datalist.removeAt(datalist.size-1)
            adapter.notifyItemRemoved(datalist.size)
        }

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    // this method is called
                    // when the item is moved.
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    val deleteItem: Data = datalist.get(viewHolder.adapterPosition)
                    val position = viewHolder.adapterPosition

                    datalist.removeAt(position)
                    adapter.notifyItemRemoved(position - 1)

                    Snackbar.make(recyclerView, "Deleted " + deleteItem.textData, Snackbar.LENGTH_LONG)
                        .setAction(
                            "Undo",
                            View.OnClickListener {
                                // adding on click listener to our action of snack bar.
                                // below line is to add our item to array list with a position.
                                datalist.add(position, deleteItem)

                                // below line is to notify item is
                                // added to our adapter class.
                                adapter.notifyItemInserted(position)
                            }).show()
                }
                // at last we are adding this
                // to our recycler view.
            }).attachToRecyclerView(recyclerView)
    }

}