package actividad.octubre.adapters

import actividad.octubre.R
import actividad.octubre.fbclases.FBProfile
import actividad.octubre.viewholders.StringViewHolder
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class RvListProfilesAdapter(var listaDeProfiles:List<FBProfile>, val fragmentoPadre: Fragment) : RecyclerView.Adapter<StringViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_cell1, parent, false)
        val stringViewHolder = StringViewHolder(view,fragmentoPadre)
        return stringViewHolder
    }

    override fun getItemCount(): Int {
        Log.v("FIREBASE","----------->>>>>>>>>>> "+listaDeProfiles.size)
        return listaDeProfiles.size
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {

        holder.asignarDatos(listaDeProfiles[position])
        //Log.v("RvListProfilesAdapter","------->>>>>>>>> "+listaDeProfiles[position].sImgUrl)


        //holder.constrains.layoutParams.height= 250
        /*if(position%3==0){
            holder.constrains.setBackgroundColor(Color.BLUE)
        }
        else if(position%2==0){
            holder.constrains.setBackgroundColor(Color.RED)
        }
        else{
            holder.constrains.setBackgroundColor(Color.YELLOW)
        }*/


    }

}