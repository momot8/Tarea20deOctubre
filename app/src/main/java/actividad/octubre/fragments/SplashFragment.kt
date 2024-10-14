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
import androidx.navigation.fragment.findNavController
import java.util.Timer
import java.util.TimerTask


class SplashFragment : Fragment() {

    lateinit var progressBar: ProgressBar
    lateinit var progressBar2: ProgressBar
    var iProgress:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iProgress=0
        startTimer()

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
                        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                    }
                }
            }


        }, 0, 1000)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}