package actividad.octubre.home_fragments

import actividad.octubre.R
import actividad.octubre.adapters.RvListProfilesAdapter
import actividad.octubre.fbclases.FBProfile
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class listProfilesFragment : Fragment(),OnClickListener {


    lateinit var btnPerfil:Button
    lateinit var btnMayores:Button
    private val viewModelProfiles : ListProfilesViewModel by activityViewModels()
    val db = Firebase.firestore

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

        btnMayores=view.findViewById(R.id.btnMayores)
        btnMayores.setOnClickListener(this)


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
        if(p0!!.id==btnMayores.id){
              //  mostrarPerfilesMayoresDe35()
        }
    }


    // Fun para mostrar perfiles mayores de 35

//    private fun mostrarPerfilesMayoresDe35() {
//        db.collection("Profiles")
//            .whereGreaterThan("edad", 35) // Consulta para filtrar por edad
//            .get()
//            .addOnSuccessListener { result ->
//                listProfilesFragment.clear() // Limpia la lista antes de agregar nuevos perfiles
//                for (document in result) {
//                    val profile = FBProfile(
//                        sUID = document.id,
//                        name = document.getString("nombre") ?: "",
//                        edad = document.getString("edad")?: "",
//                        apellido = document.getString("apellidos") ?: "",
//                        hobbie = document.getString("hobbies") ?: "",
//                        sImgUrl = document.getString("imagenUrl")
//                    )
//                    listProfilesFragment.add(profile)
//                }
//                rvListProfiles.notifyDataSetChanged()
//                if (ListProfilesViewModel.isEmpty()) {
//                    Toast.makeText(requireContext(), "No hay perfiles mayores de 35 años disponibles.", Toast.LENGTH_SHORT).show()
//                }
//            }
//            .addOnFailureListener { e ->
//                Log.e("ProfilesFragment", "Error fetching older profiles: ", e)
//                Toast.makeText(requireContext(), "Error al recuperar perfiles mayores de 35 años.", Toast.LENGTH_SHORT).show()
//            }
//    }




}