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
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso



class StringViewHolder(val view: View, val fragmentoPadre: Fragment) : RecyclerView.ViewHolder(view), OnClickListener {

    // Referencias a las vistas usando los IDs definidos en el XML
    var txtName: TextView = view.findViewById(R.id.list_name)
    var txtHobbie: TextView = view.findViewById(R.id.list_hobbie)
    var txtApellido: TextView = view.findViewById(R.id.list_apellido)
    var txtEdad: TextView = view.findViewById(R.id.list_edad)

    val ivAvatar: ImageView = itemView.findViewById(R.id.list_cell1_ivAvatar)
    val constrains: ConstraintLayout = view.findViewById(R.id.list1_cell_raiz)

    lateinit var fbProfile: FBProfile

    init {
        view.setOnClickListener(this)
    }

    fun asignarDatos(profile: FBProfile) {
        txtName.text = profile.name
        txtHobbie.text = profile.hobbie
        txtApellido.text = profile.apellido
        txtEdad.text = profile.edad.toString()
        //Llamada a la clase piccaso
        Picasso.get().load(profile.sImgUrl).into(ivAvatar)

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
            .inflate(R.layout.list_cell1, parent, false)
        return StringViewHolder(view, fragmentoPadre)
    }

}
