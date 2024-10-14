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


class ProfileFragment : Fragment(),OnClickListener {

    lateinit var btnRegreso:Button

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
    }

    override fun onClick(p0: View?) {
        if(p0!!.id==btnRegreso.id){
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }


}