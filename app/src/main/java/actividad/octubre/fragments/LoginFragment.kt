package actividad.octubre.fragments

import actividad.octubre.HomeActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import actividad.octubre.R
import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.View.OnClickListener
import android.widget.*
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.protobuf.Internal.BooleanList



class LoginFragment : Fragment(), OnClickListener {

    lateinit var btnLogin:Button
    lateinit var btnRegister:Button
    lateinit var edtxtCorreo:EditText
    lateinit var edtxtContraseña:EditText

    private lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore


    val TAG:String = "LoginFragment"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Señalamos a quien pertenece las variables de los botones
            btnLogin = view.findViewById(R.id.button)
            btnRegister = view.findViewById(R.id.button2)
            edtxtCorreo = view.findViewById(R.id.editTextCorreo)
            edtxtContraseña = view.findViewById(R.id.editTextTextPassword2)

        btnLogin.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
    }

    override fun onClick(p0:View?){
        if(p0!!.id== btnLogin.id){
            val usuario=edtxtCorreo.text.toString()
            val contraseña = edtxtContraseña.text.toString()

            if(usuario.isEmpty() || contraseña.isEmpty()){

                for(i in 0 until 5){
                    Log.e(TAG, "No ha rellenado los datos para logearse")
                }

                Toast.makeText(requireActivity(), "Debes rellenar ambos campos", Toast.LENGTH_SHORT).show()
            }else{
                comprobarPerfil(usuario, contraseña)
            }

        }else if(p0!!.id== btnRegister.id){
            irRegistro()

        }
    }



    private fun irRegistro() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }



    private fun comprobarPerfil(email:String, password:String) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    Log.i(TAG,"El usuario se ha logeado correctamente")
                    val user = auth.currentUser

                    comprobarDatos()








//                    Toast.makeText(requireActivity(), "Re-dirigiendose a perfiles", Toast.LENGTH_SHORT).show()
//                    findNavController().navigate(R.id.action_loginFragment_to_profileFragment)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireActivity(),
                        "Correo o contraseña incorrectas.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }


    }
    @SuppressLint("SuspiciousIndentation")
    private fun comprobarDatos(){
        val currentUser = FirebaseAuth.getInstance().currentUser

        currentUser?.let { user ->
            val uid = user.uid


            val docRef = db.collection("Profiles").document(uid)

            docRef.get().addOnSuccessListener { document ->
                if (document != null) {

                    val name = document.getString("name")
                    val age = document.getLong("age")

                        if(name!=null || age!=null){
                            val intentHomeActivity: Intent = Intent(requireActivity(), HomeActivity::class.java)
                            requireActivity().startActivity(intentHomeActivity)
                            requireActivity().finish()
                        }else{
                            Toast.makeText(requireActivity(), "Re-dirigiendose a perfiles", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
                        }




                } else {
                    Log.w(TAG,"No existe un documento para este usuario")
                }
            }.addOnFailureListener { exception ->
                println("Error al obtener los datos: ${exception.message}")
            }
        }
    }


}