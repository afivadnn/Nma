package com.afivadnan.nma

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.afivadnan.nma.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var switchMode: Switch
    private lateinit var binding : ActivityMainBinding
    private lateinit var Share : NamShare

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        switchMode=findViewById(R.id.night)
        switchMode.setOnCheckedChangeListener { _, isChecked ->
            switchMode(isChecked)
        }

        Share= NamShare(this)
        binding.nama.text=Share.dpNama()

        binding.btnSve.setOnClickListener{
            val nama = binding.input.text.toString().trim()
            Share.saveNama(nama)
            binding.nama.text = Share.dpNama()
        }
    }

    private fun switchMode(isChecked:Boolean){
        if (isChecked){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}