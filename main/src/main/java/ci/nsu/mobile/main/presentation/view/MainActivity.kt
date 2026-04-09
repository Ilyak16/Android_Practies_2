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

        val navController = navHost.navController
        val graph = navController.createGraph(startDestination = "main") {
            fragment<MainFragment>("main")
            fragment<Step1Fragment>("step1")
            fragment<Step2Fragment>("step2")
            fragment<ResultFragment>("result")
            fragment<HistoryFragment>("history")
        }
        navController.graph = graph
    }
}
