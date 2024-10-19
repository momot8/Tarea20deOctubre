package actividad.octubre.home_fragments

import actividad.octubre.HomeActivity
import actividad.octubre.MainActivity
import actividad.octubre.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.util.Timer
import java.util.TimerTask


class homeProfileFragment : Fragment(),OnClickListener {

    lateinit var btnCerrarSesion:Button
    lateinit var btnCambiar:Button


    lateinit var txtUser:TextView
    lateinit var txtAge:TextView
    var TAG = "homeProfileFragment"


    lateinit var auth:FirebaseAuth
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCerrarSesion= view.findViewById(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener(this)

        btnCambiar=view.findViewById(R.id.btnCambiar)
        btnCambiar.setOnClickListener(this)

        auth= FirebaseAuth.getInstance()
        db = Firebase.firestore

        txtUser=view.findViewById(R.id.txtUser)
        txtAge=view.findViewById(R.id.txtAge)


        val currentUser = FirebaseAuth.getInstance().currentUser

        currentUser?.let { user ->
            val uid = user.uid


            val docRef = db.collection("Profiles").document(uid)

            docRef.get().addOnSuccessListener { document ->
                if (document != null) {

                    val name = document.getString("name")
                    val age = document.getLong("age")?.toInt()


                    txtUser.text="Nombre: $name"
                    txtAge.text="Edad: $age"


                } else {
                    Log.w(TAG,"No existe un documento para este usuario")
                }
            }.addOnFailureListener { exception ->
                println("Error al obtener los datos: ${exception.message}")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_profile, container, false)
    }

    override fun onClick(p0: View?) {

        if (p0!!.id==btnCerrarSesion.id){
          FirebaseAuth.getInstance().signOut()
            Log.i(TAG, "Cuenta cerrada y aplicación cerrada")


            startTimer()

        }
        if(p0!!.id==btnCambiar.id){
            //Si quiero cambiar he de ir desde homeActivty hasta  mainActiivty.profileFragment.

            val intentCambiarDatos: Intent = Intent(requireActivity(), MainActivity::class.java)
            requireActivity().startActivity(intentCambiarDatos)
            requireActivity().finish()

            Toast.makeText(requireActivity(), "Volviendo a perfil...", Toast.LENGTH_SHORT).show()
            Log.w(TAG, "Intento de volver al main activity para cambiar datos en profileFragment")



        }
    }

















    private fun startTimer() {
        val timer = Timer()
        var contador = 0
        var totalRuns = 4

        timer.schedule(object : TimerTask(){
            override fun run() {
                Handler(Looper.getMainLooper()).post{
                    contador++
                    if(contador==1){
                        Toast.makeText(requireActivity(), "Saliendo de la aplicacion", Toast.LENGTH_SHORT).show()
                    }

                    if(contador==totalRuns){
                        timer.cancel()
                        System.exit(0)

                    }
                }
            }


        }, 0, 1000)
    }

}