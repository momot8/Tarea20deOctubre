package actividad.octubre.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import actividad.octubre.R
import actividad.octubre.fbclases.FBProfile
import actividad.octubre.sigletone.DataHolder
import android.content.Intent
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import actividad.octubre.HomeActivity
import android.nfc.Tag
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class ProfileFragment : Fragment(),OnClickListener {


    lateinit var edtxtNombre: EditText
    lateinit var edTxtEdad: EditText
    lateinit var edTxtApellido: EditText
    lateinit var edTxtHobbie: EditText

    lateinit var btnGuardar: Button
    lateinit var btnRegreso:Button

    lateinit var auth:FirebaseAuth
    lateinit var db: FirebaseFirestore

    var TAG="ProfileFragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnRegreso = view.findViewById(R.id.btnRegreso)
        btnRegreso.setOnClickListener(this)
        btnGuardar = view.findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener(this)

        auth= FirebaseAuth.getInstance()
        db = Firebase.firestore

        edtxtNombre= view.findViewById(R.id.edtxtNombre)
        edTxtEdad = view.findViewById(R.id.edtxtEdad)
        edTxtHobbie = view.findViewById(R.id.edTxtHobbie)
        edTxtApellido = view.findViewById(R.id.edTxtApellido )
    }

    override fun onClick(p0: View?) {

        if(edtxtNombre!=null || edTxtEdad!=null){
            if(p0!!.id==btnRegreso.id){
                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }else if(p0!!.id==btnGuardar.id){
                val nuevoPerfil= FBProfile(edtxtNombre.text.toString(), edTxtEdad.text.toString(), edTxtHobbie.text.toString(),edTxtApellido.text.toString(), auth.currentUser!!.uid)

                DataHolder.fbProfileUser=nuevoPerfil

                db.collection("Profiles").document(auth.currentUser!!.uid).set(nuevoPerfil).addOnSuccessListener {
                    val intentHomeActivity: Intent = Intent(requireActivity(), HomeActivity::class.java)
                    requireActivity().startActivity(intentHomeActivity)
                    requireActivity().finish()
                }
            }
        }else{
            Toast.makeText(requireActivity(), "Rellena los campos, porfavor", Toast.LENGTH_SHORT).show()
            Log.w(TAG,  "Campos Nombre y/o edad sin completar")
        }

    }


}