package com.github.kereis.medit.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.ActivityMainBinding
import com.github.kereis.medit.ui.explorer.FileExplorerStorageListFragment
import com.github.kereis.medit.ui.explorer.recent.FileExplorerRecentListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    AppCompatActivity(R.layout.activity_main),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sectionPagerAdapter: FragmentStateAdapter
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager = binding.mainViewPager
        viewPager.adapter = sectionPagerAdapter
        viewPager.offscreenPageLimit = binding.bottomNavigationBar.menu.size()

        binding.bottomNavigationBar.setOnNavigationItemSelectedListener(this)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_fileExplorerRecentListFragment -> {
                binding.mainViewPager.currentItem = 0
                toolbar.setTitle(R.string.file_explorer_nav_title_recent)
                return true
            }
            R.id.navigation_fileExplorerStorageListFragment -> {
                binding.mainViewPager.currentItem = 1
                toolbar.setTitle(R.string.file_explorer_nav_title_storage)
                return true
            }
        }

        return false
    }

    inner class SectionPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(
        fragmentActivity
    ) {
        override fun getItemCount(): Int {
            return binding.bottomNavigationBar.menu.size()
        }

        override fun createFragment(position: Int): Fragment {
            when (binding.bottomNavigationBar.menu[position].itemId) {
                R.id.navigation_fileExplorerStorageListFragment
                -> return FileExplorerStorageListFragment()
                R.id.navigation_fileExplorerRecentListFragment
                -> return FileExplorerRecentListFragment()
            }

            return FileExplorerRecentListFragment()
        }
    }
}
