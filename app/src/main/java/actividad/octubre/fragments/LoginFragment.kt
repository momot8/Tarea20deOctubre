package actividad.octubre.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import actividad.octubre.R
import android.util.Log
import android.view.View.OnClickListener
import android.widget.*
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.protobuf.Internal.BooleanList



class LoginFragment : Fragment(), OnClickListener {

    lateinit var btnLogin:Button
    lateinit var btnRegister:Button
    lateinit var edtxtCorreo:EditText
    lateinit var edtxtContraseña:EditText
    private lateinit var auth: FirebaseAuth

    val TAG:String = "LoginFragment"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

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
            comprobarPerfil(edtxtCorreo.text.toString(), edtxtContraseña.text.toString())
        }else if(p0!!.id== btnRegister.id){
            irRegistro()

        }
    }


//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            reload()
//        }
//    }

    private fun irRegistro() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun comprobarPerfil(email:String, password:String) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    findNavController().navigate(R.id.action_loginFragment_to_profileFragment)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireActivity(),
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }


    }


}