package actividad.octubre.home_fragments

import actividad.octubre.fbclases.FBProfile
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListProfilesViewModel: ViewModel() {

    val db = Firebase.firestore



    //var fbProfileUserSelected: FBProfile?=null

    // LiveData or MutableLiveData for the string variable
    private val _sharedProfilesList = MutableLiveData<List<FBProfile>>()
    val sharedProfilesList: LiveData<List<FBProfile>> get() = _sharedProfilesList

    // LiveData or MutableLiveData for the string variable
    private val _sharedProfile = MutableLiveData<FBProfile>()
    val fbProfileUserSelected: LiveData<FBProfile> get() = _sharedProfile

    init {
        setProfileList(mutableListOf())
    }

    // Function to set the value
    fun setProfile(value: FBProfile) {
        _sharedProfile.value = value
    }

    // Function to set the value
    fun setProfileList(value: List<FBProfile>) {
        _sharedProfilesList.value = value
    }

    fun descargarDatos(){
        val docRef = db.collection("Profiles")
        /*docRef.get().addOnSuccessListener { resultQuery ->
            profilesList.clear()
            rvListProfilesAdapter.notifyItemRangeRemoved(0,profilesList.size-1)

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({

                if(!resultQuery.isEmpty){
                    val posIni=profilesList.size
                    for (document in resultQuery.documents){
                        val fbProfileTemp=document.toObject(FBProfile::class.java)
                        profilesList.add(fbProfileTemp!!)
                        //Log.v("FIREBASE","NOMBRE DOCUMENTO DESCARGO: "+fbProfileTemp!!.name)
                    }
                    val posFin=profilesList.size-1
                    rvListProfilesAdapter.notifyItemRangeInserted(posIni,posFin)
                    //rvListProfilesAdapter.notifyDataSetChanged()
                }

            }, 2000)
        }.addOnFailureListener {}
        */

        docRef.addSnapshotListener { resultQuery, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (resultQuery != null && !resultQuery.isEmpty) {
                val listaTemp= mutableListOf<FBProfile>()

                //val tempSize=listaTemp.size
                //listaTemp.clear()
                //rvListProfilesAdapter.notifyItemRangeRemoved(0,tempSize)


                val posIni=0
                for (document in resultQuery.documents){

                    val fbProfileTemp=document.toObject(FBProfile::class.java)
                    fbProfileTemp!!.sUID=document.id
                    listaTemp.add(fbProfileTemp!!)
                    //Log.v("FIREBASE","NOMBRE DOCUMENTO DESCARGO: "+fbProfileTemp!!.name)
                }

                setProfileList(listaTemp)
                //val posFin=profilesList.size
                //rvListProfilesAdapter.notifyItemRangeInserted(posIni,posFin)
                //rvListProfilesAdapter.notifyDataSetChanged()
            } else {

            }
        }
    }

}