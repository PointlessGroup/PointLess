package pointlessgroup.pointless.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import pointlessgroup.pointless.R
import pointlessgroup.pointless.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.reports -> addFragment(DashboardFragment(), "reports")
                R.id.dashboard -> addFragment(DashboardFragment(), "dashboard")
                R.id.settings -> addFragment(DashboardFragment(), "settings")
                else -> addFragment(DashboardFragment(), "dashboard")
            }
            true
        }
        binding.bottomNavigation.selectedItemId = R.id.dashboard
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment, tag)
                .commit()
    }

}