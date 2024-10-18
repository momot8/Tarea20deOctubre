package actividad.octubre.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import actividad.octubre.R
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

import com.google.firebase.auth.FirebaseAuth
import java.util.Timer
import java.util.TimerTask
import actividad.octubre.sigletone.DataHolder
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class SplashFragment : Fragment() {
//Parte grafica
    lateinit var progressBar: ProgressBar
    lateinit var progressBar2: ProgressBar
    var iProgress:Int=0
//Parte auth
    lateinit var auth:FirebaseAuth

    var TAG ="SplashFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iProgress=0
        startTimer()

        auth = FirebaseAuth.getInstance()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar=view.findViewById(R.id.progressBar)
        progressBar2=view.findViewById(R.id.progressBar2)

    }

    private fun startTimer() {
        val timer = Timer()
        var contador = 0
        var totalRuns = 4

        timer.schedule(object :TimerTask(){
            override fun run() {
                Handler(Looper.getMainLooper()).post{
                    contador++
                    progressBar.progress=(contador)*20
                    progressBar2.progress=(contador)*20

                    if(contador==totalRuns){

                        timer.cancel()
                        comprobarUser()

                    }
                }
            }


        }, 0, 1000)
    }

    fun comprobarUser(){
        Log.e(TAG,"COMPROBAR USER" )


        val user = Firebase.auth.currentUser

        if(user!=null){
            DataHolder.descargarPerfil(requireActivity(),findNavController(),R.id.action_loginFragment_to_profileFragment)

        }else{
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.auth_nav, true).build()
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment, null, navOptions)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}