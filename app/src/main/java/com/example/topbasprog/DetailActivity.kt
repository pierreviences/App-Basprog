package com.example.topbasprog

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

}