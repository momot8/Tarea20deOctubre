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
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

lateinit var btnRegistrarse:Button
lateinit var btnVolver:Button
lateinit var txtContraseña:EditText
lateinit var txtCorreo:EditText

lateinit var auth:FirebaseAuth
lateinit var db:FirebaseFirestore

val TAG:String = "RegisterFragment"


class RegisterFragment : Fragment(), OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        db = Firebase.firestore

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnVolver = view.findViewById(R.id.btnVolver)
        btnRegistrarse = view.findViewById(R.id.button3)


        txtCorreo= view.findViewById(R.id.editTextTextEmailAddress)
        txtContraseña=view.findViewById(R.id.editTextTextPassword)

        btnVolver.setOnClickListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onClick(p0: View?) {
        if(p0!!.id== btnVolver.id){
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }else if(p0!!.id== btnRegistrarse.id){
            registrarUsuario(txtCorreo.toString(), txtContraseña.toString())
        }
    }

     fun registrarUsuario(email:String, contraseña:String) {
        auth.createUserWithEmailAndPassword(email, contraseña).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                val user = auth.currentUser

                if(user != null){
                    val userId = user.uid

                    val userMap = hashMapOf(
                        "uid" to userId,
                        "email" to email,
                        "username" to user,
                        "createdAt" to FieldValue.serverTimestamp()
                    )
//ASDA
                    db.collection("Users").document(userId).set(userMap).addOnSuccessListener {
                        Log.d("Register", "User registrado")
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    }.addOnFailureListener{
                        e -> Log.e("Register","No se ha podido registrar este usuario")
                    }
                }
            }

        }
    }


}