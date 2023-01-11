package com.gdscug.mooflix.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdscug.mooflix.R
import com.gdscug.mooflix.data.local.MoviesEntity
import com.gdscug.mooflix.databinding.ActivityHomeBinding
import com.gdscug.mooflix.ui.detail.DetailActivity
import com.gdscug.mooflix.utils.ViewModelFactory

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupViewModel()
        setupAdapter()
    }

    private fun setupView() {
        supportActionBar?.title = getString(R.string.home_title)
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(
            this@HomeActivity,
            factory
        )[HomeViewModel::class.java]
    }

    private fun setupAdapter() {
        val movieAdapter = MovieAdapter()

        viewModel.getMovies().observe(this) {
            val movies = it.results
            movieAdapter.submitList(movies)
        }

        binding.apply {
            rvMovies.layoutManager = GridLayoutManager(this@HomeActivity, 2)
            rvMovies.adapter = movieAdapter
        }
        movieAdapter.onItemClickCallback = object : MovieAdapter.OnItemClickcallback {
            override fun onItemClicked(movie: MoviesEntity) {
                Intent(this@HomeActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_MOVIE, movie)
                    startActivity(this)

                }

            }
        }
    }
}