package com.example.aniguide

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBar
import com.example.aniguide.ui.home.EpisodeFragment
import com.google.android.material.appbar.AppBarLayout
import com.kobakei.ratethisapp.RateThisApp
import kotlinx.android.synthetic.main.action_bar.*

class MainActivity : AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration

    fun initActionBar(actionBar: ActionBar) {
        // Disable the default and enable the custom
        actionBar.setDisplayShowTitleEnabled(false)
        actionBar.setDisplayShowCustomEnabled(true)
        val customView: View = layoutInflater.inflate(R.layout.action_bar, null)
        actionBar.customView = customView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RateThisApp.onCreate(this)
        val config = RateThisApp.Config(1,1)
        config.setMessage(R.string.rate_title)
        RateThisApp.init(config)

        //Change to every x times after presentation
        RateThisApp.showRateDialog(this)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.let{
            initActionBar(it)
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_popular, R.id.nav_fall, R.id.nav_winter, R.id.nav_spring, R.id.nav_summer,
                R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        actionSearch.queryHint = ""
        actionSearch.setOnSearchClickListener {
            actionTitle.visibility = View.GONE
        }
        actionSearch.setOnCloseListener {
            actionTitle.visibility = View.VISIBLE
            false
        }

    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
        findViewById<AppBarLayout>(R.id.appbar_layout)?.setExpanded(false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
