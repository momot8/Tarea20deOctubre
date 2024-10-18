package actividad.octubre.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import actividad.octubre.R
import android.util.Log
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase




class RegisterFragment : Fragment(), OnClickListener {

    lateinit var btnRegistrarse:Button
    lateinit var btnVolver:Button
    lateinit var txtContraseña:EditText
    lateinit var txtCorreo:EditText

    lateinit var auth:FirebaseAuth
  //  lateinit var db:FirebaseFirestore

    val TAG:String = "RegisterFragment"





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        //db = Firebase.firestore

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnVolver = view.findViewById(R.id.btnVolver)
        btnRegistrarse = view.findViewById(R.id.button3)


        txtCorreo= view.findViewById(R.id.editTextTextEmailAddress)
        txtContraseña=view.findViewById(R.id.editTextTextPassword)

        btnVolver.setOnClickListener(this)
        //AGREGA TODAS LAS FUNCIONES GABI, NO TE FUNCIONABA X ESTO
        btnRegistrarse.setOnClickListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onClick(p0: View?) {

        if(p0!!.id== btnRegistrarse.id){
            val usuario=txtCorreo.text.toString()
            val contraseña=txtContraseña.text.toString()

            if (usuario.isEmpty() || contraseña.isEmpty()) {
                for(i in 0 until 5) {
                    Log.e(TAG, "No ha rellenado los campos")
                }
                Toast.makeText(requireActivity(), "Has de rellenar todos los huecos", Toast.LENGTH_SHORT).show()
            } else {
                createAccount(usuario, contraseña)
            }


        }else if(p0!!.id==btnRegistrarse.id && txtCorreo.text.toString().isEmpty() && txtContraseña.text.toString().isEmpty()){
            for(i in 0 until 5) {
                Log.e(TAG, "No ha rellenado los gaps")
            }


            Toast.makeText(requireActivity(), "Has de rellenar todos los huecos", Toast.LENGTH_SHORT).show()
        }


        if(p0!!.id== btnVolver.id){
            Toast.makeText(requireActivity(), "Volviendo...", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }



    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(requireActivity(), "Usuario creado. Dirigiendose a la pantalla de Login", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser

                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireActivity(),
                        "Error al crear el perfil. Intentalo de nuevo, porfavor.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }

    }



}