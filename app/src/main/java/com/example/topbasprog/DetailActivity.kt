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

        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
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
        binding.btnOverview.setOnClickListener {
            val url = basprog.url
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }


        // button pindah ke wa
        binding.actionShare.setOnClickListener {
            val title = basprog.name
            val description = basprog.description
            val url = basprog.url
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val textToShare = "Bahasa Pemrograman: $title\n\nDeskripsi : \n$description\n\nLihat Selengkapnya : $url"
            shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare)
            shareIntent.setPackage("com.whatsapp")

            try {
                startActivity(shareIntent)
            } catch (e: Exception) {
                val webUrl = "https://web.whatsapp.com/"
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
                startActivity(browserIntent)
            }
        }
    }
}