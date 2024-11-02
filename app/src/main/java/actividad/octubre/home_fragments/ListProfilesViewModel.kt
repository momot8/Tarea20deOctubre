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


        docRef.addSnapshotListener { resultQuery, e ->
            if (e != null) {
                return@addSnapshotListener
            }

            if (resultQuery != null && !resultQuery.isEmpty) {
                val listaTemp= mutableListOf<FBProfile>()



                val posIni=0
                for (document in resultQuery.documents){

                    val fbProfileTemp=document.toObject(FBProfile::class.java)
                    fbProfileTemp!!.sUID=document.id
                    listaTemp.add(fbProfileTemp!!)

                }

                setProfileList(listaTemp)
            } else {

            }
        }
    }

}