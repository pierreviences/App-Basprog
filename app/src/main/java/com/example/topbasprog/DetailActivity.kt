package com.example.topbasprog

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.topbasprog.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_BASPROG = "extra_basprog"
    }
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val basprog = intent.getParcelableExtra<Basprog>(EXTRA_BASPROG)
        basprog?.let {
            showDetails(it)
        }

    }

    private fun showDetails(basprog: Basprog) {
        binding.tvName.text = basprog.name
        binding.tvLike.text = basprog.like
        binding.tvCreator.text = basprog.creator
        binding.tvTahun.text = basprog.years
        binding.tvDescription.text = basprog.description
        binding.gambar.setImageResource(basprog.photo)
        binding.kelebihan.text = basprog.kelebihan
        binding.kekurangan.text = basprog.kekurangan
       
    }
}