package actividad.octubre.fbclases

import com.google.firebase.Timestamp

data class FBProfile(var name: String? = null,
                     var age: Int = 0,
                     var birthdate:Timestamp? = null,
                     var sUID:String?=null
)

