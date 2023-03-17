package com.mikko.intellimap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mikko.intellimap.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private val binding : ActivityStartBinding by lazy { ActivityStartBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            startActivity(Intent(this@StartActivity, MainActivity::class.java))
            finish()
        }
    }
}