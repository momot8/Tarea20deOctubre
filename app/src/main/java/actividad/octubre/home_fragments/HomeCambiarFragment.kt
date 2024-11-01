package actividad.octubre.home_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import actividad.octubre.R
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController



class HomeCambiarFragment : Fragment(), OnClickListener {

    lateinit var txtView: TextView
    lateinit var edTxtNombre: EditText
    lateinit var edTxtApellido: EditText
    lateinit var edTxtHobbie: EditText
    lateinit var edTxtEdad: EditText

    lateinit var btnVolver: Button
    lateinit var btnGuardar:Button

    val TAG:String = "HomeCambiarFragment"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnVolver = view.findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener(this)

        btnGuardar = view.findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener(this)

        txtView = view.findViewById(R.id.textView2)
        edTxtNombre = view.findViewById(R.id.edTxtName)
        edTxtApellido = view.findViewById(R.id.edTxtApellidos)
        edTxtHobbie = view.findViewById(R.id.edTxtHobbies)
        edTxtEdad = view.findViewById(R.id.edTxtEdad)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            findNavController().navigate(R.id.action_homeCambiarFragment_to_homeProfileFragment)

        }
        if(p0!!.id==btnVolver.id){
            findNavController().navigate(R.id.action_homeCambiarFragment_to_homeProfileFragment)
        }
    }

}