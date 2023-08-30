package com.example.topbasprog


import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topbasprog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Basprog>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(ContextCompat.getColor(this, R.color.blueBar))
        actionBar?.setBackgroundDrawable(colorDrawable)

        binding.recView.setHasFixedSize(true)
        list.addAll(getListBasprog())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListBasprog(): ArrayList<Basprog> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataLikes = resources.getStringArray(R.array.data_suka)
        val dataYears = resources.getStringArray(R.array.data_tahun)
        val dataCreators = resources.getStringArray(R.array.data_pencipta)
        val dataKelebihan = resources.getStringArray(R.array.data_kelebihan)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataKekurangan = resources.getStringArray(R.array.data_kekurangan)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listBasprog = ArrayList<Basprog>()
        for (i in dataName.indices) {
            val basprog = Basprog(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataLikes[i], dataCreators[i], dataYears[i], dataKelebihan[i], dataKekurangan[i])
            listBasprog.add(basprog)
        }
        return listBasprog
    }

    private fun showRecyclerList() {
        binding.recView.layoutManager = LinearLayoutManager(this)
        val listBasprogAdapter = ListBasprogAdapter(list)
        binding.recView.adapter = listBasprogAdapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            runSlideInAnimation(ListBasprogAdapter(list))
        }

        listBasprogAdapter.setOnItemClickCallback(object : ListBasprogAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Basprog) {
                showSelectedBasprog(data)
            }
        })
    }

    private fun runSlideInAnimation(adapter: ListBasprogAdapter) {
        for (i in 0 until adapter.itemCount) {
            val holder = binding.recView.findViewHolderForAdapterPosition(i) as ListBasprogAdapter.ListViewHolder?
            holder?.cardView?.startAnimation(
                android.view.animation.AnimationUtils.loadAnimation(
                    this,
                    R.anim.animscroll
                )
            )
        }
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun showSelectedBasprog(basprog: Basprog) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_BASPROG, basprog)
        }
        this.startActivity(intent)
      }
}
