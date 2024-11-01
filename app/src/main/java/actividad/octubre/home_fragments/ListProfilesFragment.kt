package actividad.octubre.home_fragments

import actividad.octubre.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class listProfilesFragment : Fragment(),OnClickListener {


    lateinit var btnPerfil:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_profiles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPerfil = view.findViewById(R.id.btnPerfil)
        btnPerfil.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        if(p0!!.id==btnPerfil.id){
            findNavController().navigate(R.id.action_listProfilesFragment_to_homeProfileFragment)
        }
    }



}