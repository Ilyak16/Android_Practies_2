package ci.nsu.mobile.main.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = FrameLayout(this).apply { id = View.generateViewId() }
        setContentView(container)

        val navHost = NavHostFragment()
        supportFragmentManager.beginTransaction()
            .replace(container.id, navHost)
            .setPrimaryNavigationFragment(navHost)
            .commitNow()
    }
}
