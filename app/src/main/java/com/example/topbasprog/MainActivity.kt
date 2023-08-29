package com.example.topbasprog


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

    private fun getListBasprog(): ArrayList<Basprog> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listBasprog = ArrayList<Basprog>()
        for (i in dataName.indices) {
            val basprog = Basprog(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listBasprog.add(basprog)
        }
        return listBasprog
    }

    private fun showRecyclerList() {
        binding.recView.layoutManager = LinearLayoutManager(this)
        val listBasprogAdapter = ListBasprogAdapter(list)
        binding.recView.adapter = listBasprogAdapter


        listBasprogAdapter.setOnItemClickCallback(object : ListBasprogAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Basprog) {
                showSelectedBasprog(data)
            }
        })
    }


    private fun showSelectedBasprog(basprog: Basprog) {
        Toast.makeText(this, "Kamu memilih " + basprog.name, Toast.LENGTH_SHORT).show()
    }
}
