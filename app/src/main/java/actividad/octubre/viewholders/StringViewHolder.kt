package actividad.octubre.viewholders

import actividad.octubre.R
import actividad.octubre.fbclases.FBProfile
import actividad.octubre.home_fragments.ListProfilesViewModel
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView



class StringViewHolder(val view: View, val fragmentoPadre: Fragment) : RecyclerView.ViewHolder(view), OnClickListener {

    // Referencias a las vistas usando los IDs definidos en el XML
    var txtName: TextView = view.findViewById(R.id.list_name)           // ID correcto
    var txtHobbie: TextView = view.findViewById(R.id.list_hobbie)      // ID correcto
    var txtApellido: TextView = view.findViewById(R.id.list_apellido)   // ID correcto
    var txtEdad: TextView = view.findViewById(R.id.list_edad)           // ID correcto
    val ivAvatar: ImageView = view.findViewById(R.id.list_cell1_ivAvatar) // ID correcto
    val constrains: ConstraintLayout = view.findViewById(R.id.list1_cell_raiz) // ID correcto

    lateinit var fbProfile: FBProfile

    init {
        view.setOnClickListener(this)
    }

    fun asignarDatos(profile: FBProfile) {
        txtName.text = profile.name          // Asegúrate de que FBProfile tiene una propiedad 'name'
        txtHobbie.text = profile.hobbie      // Asegúrate de que FBProfile tiene una propiedad 'hobbie'
        txtApellido.text = profile.apellido   // Asegúrate de que FBProfile tiene una propiedad 'apellido'
        txtEdad.text = profile.edad.toString() // Asegúrate de que FBProfile tiene una propiedad 'edad' y convierte a String si es necesario

        fbProfile = profile
    }

    override fun onClick(p0: View?) {
        val viewModel: ListProfilesViewModel by fragmentoPadre.activityViewModels()
        viewModel.setProfile(fbProfile)

        fragmentoPadre.findNavController().navigate(R.id.action_listProfilesFragment_to_homeProfileFragment)
        Log.v("StringViewHolder", "------>>>>>> CLICK CELDA " + fbProfile.name)
    }
   fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_cell1, parent, false) // Asegúrate de que estás usando el nombre correcto aquí
        return StringViewHolder(view, fragmentoPadre)
    }

}
