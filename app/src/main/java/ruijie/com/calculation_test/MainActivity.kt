package ruijie.com.calculation_test

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        if(navController.currentDestination!!.id == R.id.questionFragment) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.quit_dialog_title)
            builder.setPositiveButton(R.string.dialog_positive_message) { _: DialogInterface, _: Int ->
                navController.navigate(R.id.action_questionFragment_to_titleFragment)
            }
            builder.setNegativeButton(R.string.dialog_negative_message) { _: DialogInterface, _: Int ->}
            builder.create().show()
        } else if(navController.currentDestination!!.id == R.id.titleFragment) {
            finish()
        }else {
            navController.navigate(R.id.titleFragment)
        }

        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }
}
