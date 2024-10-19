package actividad.octubre

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController

class HomeActivity : AppCompatActivity(), OnClickListener {


    lateinit var btnCerrarSesion:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)




    }

    override fun onClick(p0: View?) {


    }

    override fun onPause() {
        super.onPause()
    }
}