package actividad.octubre.sigletone

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import actividad.octubre.fbclases.FBProfile
import actividad.octubre.HomeActivity

object DataHolder {
    var fbProfileUser:FBProfile?=null
    var fbProfileUserSelected:FBProfile?=null
    var homeActivity:HomeActivity?=null

    val TAG = "DataHolder"

    fun descargarPerfil(miActivity:Activity,miNav:NavController, idActionNav:Int){

        val db = Firebase.firestore
        val auth = FirebaseAuth.getInstance()
        val docRef = db.collection("Profiles").document(auth.currentUser!!.uid)
        docRef.get().addOnSuccessListener { document ->
            if (document.data != null) {
                fbProfileUser = document.toObject(FBProfile::class.java)

                Log.d(TAG, "DocumentSnapshot data: ${document.data!!}")
                Log.d(TAG, "Mi Perfil data NAME: ${fbProfileUser!!.name}")

                val intentHomeActivity: Intent = Intent(miActivity, HomeActivity::class.java)
                miActivity.startActivity(intentHomeActivity)
                miActivity.finish()

            } else {
                miNav.navigate(idActionNav)
                Log.d(TAG, "No existe el documento con los datos del perfil para esta ID")
            }
        }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

    }
}