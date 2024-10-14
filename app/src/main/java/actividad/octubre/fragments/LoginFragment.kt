package actividad.octubre.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import actividad.octubre.R
import android.view.View.OnClickListener
import android.widget.Button
import androidx.navigation.fragment.findNavController

lateinit var btnLogin:Button
lateinit var btnRegister:Button


class LoginFragment : Fragment(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        btnLogin.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
    }

    override fun onClick(p0:View?){
        if(p0!!.id== btnLogin.id){
            comprobarPerfil()
        }else if(p0!!.id== btnRegister.id){
            irRegistro()

        }
    }

    private fun irRegistro() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun comprobarPerfil() {
        findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
    }


}