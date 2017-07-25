package pl.xdk78.nastoage.ui

import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import co.zsmb.materialdrawerkt.builders.accountHeader
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.profile.profile
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.Drawer

import kotlinx.android.synthetic.main.activity_main.*
import pl.xdk78.nastoage.R

class MainActivity : AppCompatActivity() {

    private lateinit var result: Drawer
    private lateinit var headerResult: AccountHeader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        result = drawer {
            hasStableIds = true
            toolbar = this@MainActivity.toolbar
            savedInstance = savedInstanceState
            headerResult = accountHeader {
                background = R.drawable.side_nav_bar
                savedInstance = savedInstanceState
                translucentStatusBar = true

                profile("Nastoletni", "nastoletni@nastoletni.pl") {
                    icon = R.drawable.ic_person
                    identifier = 100
                }
            }

            primaryItem("Newsy") {
                identifier = 0
                onClick { _ ->
                    false
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.menu_main, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        result.saveInstanceState(outState)
        headerResult.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (result.isDrawerOpen) {
            result.closeDrawer()
        }
        else {
            super.onBackPressed()
        }
    }
}
