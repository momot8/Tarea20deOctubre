package actividad.octubre.home_fragments

import actividad.octubre.R
import actividad.octubre.adapters.RvListProfilesAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class listProfilesFragment : Fragment(),OnClickListener {


    lateinit var btnPerfil:Button
    private val viewModelProfiles : ListProfilesViewModel by activityViewModels()

    lateinit var rvListProfiles: RecyclerView

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


        rvListProfiles=view.findViewById(R.id.rvListProfiles)

        val rvListProfilesAdapter = RvListProfilesAdapter(viewModelProfiles.sharedProfilesList.value!!, this)
        rvListProfiles.layoutManager = LinearLayoutManager(requireContext())
        rvListProfiles.adapter=rvListProfilesAdapter

        viewModelProfiles.sharedProfilesList.observe(viewLifecycleOwner){value ->

            rvListProfilesAdapter.listaDeProfiles=value
            rvListProfilesAdapter.notifyDataSetChanged()
        }
        viewModelProfiles.descargarDatos()
    }

    override fun onClick(p0: View?) {
        if(p0!!.id==btnPerfil.id){
            findNavController().navigate(R.id.action_listProfilesFragment_to_homeProfileFragment)
        }
    }



}