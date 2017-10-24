package pointlessgroup.pointless.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import pointlessgroup.pointless.R
import pointlessgroup.pointless.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val fragments = HashMap<String, Fragment>(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.reports -> switchFragment("reports") { DashboardFragment() }
                R.id.dashboard -> switchFragment("dashboard") { DashboardFragment() }
                R.id.settings -> switchFragment("settings") { SettingsFragment() }
                else -> switchFragment("dashboard") { DashboardFragment() }
            }
            true
        }
        binding.bottomNavigation.selectedItemId = R.id.dashboard
    }

    private fun switchFragment(tag: String, creator: () -> Fragment) {
        replaceFragment(getOrCreate(tag, creator), tag)
    }

    private fun getOrCreate(tag: String, creator: () -> Fragment): Fragment {
        if (fragments.containsKey(tag) && fragments[tag] != null)
            return fragments[tag]!!
        val fragment = creator.invoke()
        fragments[tag] = fragment
        return fragment
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment, tag)
                .commit()
    }

}