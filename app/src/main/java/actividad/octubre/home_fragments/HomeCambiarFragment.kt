package actividad.octubre.home_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import actividad.octubre.R
import actividad.octubre.fbclases.FBProfile
import actividad.octubre.sigletone.DataHolder
import android.util.Log
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.squareup.picasso.Picasso


class HomeCambiarFragment : Fragment(), OnClickListener {

    lateinit var db: FirebaseFirestore
    lateinit var auth:FirebaseAuth

    lateinit var txtView: TextView
    lateinit var edTxtNombre: EditText
    lateinit var edTxtApellido: EditText
    lateinit var edTxtHobbie: EditText
    lateinit var edTxtEdad: EditText
    lateinit var edTxtFoto:EditText

    lateinit var btnVolver: Button

    lateinit var btnGuardar:Button

    val TAG:String = "HomeCambiarFragment"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         db = Firebase.firestore
        auth= FirebaseAuth.getInstance()


        btnVolver = view.findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener(this)

        btnGuardar = view.findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener(this)

        txtView = view.findViewById(R.id.textView2)
        edTxtNombre = view.findViewById(R.id.edTxtName)
        edTxtApellido = view.findViewById(R.id.edTxtApellidos)
        edTxtHobbie = view.findViewById(R.id.edTxtHobbies)
        edTxtEdad = view.findViewById(R.id.edTxtEdad)
        edTxtFoto = view.findViewById(R.id.edTxtFoto)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var db = FirebaseDatabase.getInstance().reference

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_cambiar, container, false)
    }

    override fun onClick(p0: View?) {
        if(p0!!.id==btnGuardar.id){
            guardarDatos()
            Log.w(TAG, "Btn Guardar clickeado")

        }
        if(p0!!.id==btnVolver.id){
            findNavController().navigate(R.id.action_homeCambiarFragment_to_homeProfileFragment)
        }
    }




    private fun guardarDatos(){
        val nombre = edTxtNombre.text.toString()
        val apellido = edTxtApellido.text.toString()
        val hobbie = edTxtHobbie.text.toString()
        val edad = edTxtEdad.text.toString()
        val foto = edTxtFoto.text.toString()


        if(nombre.isNotEmpty() && apellido.isNotEmpty() && hobbie.isNotEmpty() && edad.isNotEmpty() && foto.isNotEmpty() ){

            // val nuevoPerfil = FBProfile(edTxtNombre.text.toString(),edTxtEdad.text.toString(), edTxtHobbie.text.toString(), edTxtApellido.text.toString(), edTxtFoto.text.toString(), auth.currentUser!!.uid)

            val nuevoPerfil = FBProfile(nombre, edad, hobbie, apellido, foto,auth.currentUser!!.uid)


            DataHolder.fbProfileUser=nuevoPerfil


            db.collection("Profiles").document(auth.currentUser!!.uid).set(nuevoPerfil)

            findNavController().navigate(R.id.action_homeCambiarFragment_to_homeProfileFragment)
        }else {
            Log.e(TAG, "Todos los campos son obligatorios")
        }


    }
}