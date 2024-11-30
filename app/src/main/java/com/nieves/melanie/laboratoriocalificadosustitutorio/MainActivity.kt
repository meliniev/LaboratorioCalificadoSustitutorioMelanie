package com.nieves.melanie.laboratoriocalificadosustitutorio

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nieves.melanie.laboratoriocalificadosustitutorio.adapter.PostAdapter
import com.nieves.melanie.laboratoriocalificadosustitutorio.model.Post
import com.nieves.melanie.laboratoriocalificadosustitutorio.network.RetrofitClient
import com.nieves.melanie.laboratoriocalificadosustitutorio.databinding.ActivityMainBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class
MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Llamar a la API
        RetrofitClient.apiService.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body() ?: emptyList()
                    binding.recyclerView.adapter = PostAdapter(this@MainActivity, posts)
                } else {
                    Toast.makeText(this@MainActivity, "Error al obtener datos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}