package actividad.octubre.fragments

import actividad.octubre.HomeActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import actividad.octubre.R
import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.View.OnClickListener
import android.widget.*
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.protobuf.Internal.BooleanList



class LoginFragment : Fragment(), OnClickListener {


    //Declaro los botones y textos que mas tarde tendran utilidad
    lateinit var btnLogin:Button
    lateinit var btnRegister:Button
    lateinit var edtxtCorreo:EditText
    lateinit var edtxtContraseña:EditText


    //Declaracion de varaibles de firebase
    private lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore

//TAG para los log.
    val TAG:String = "LoginFragment"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
    }
//Este se crea solo y no hay que tocar nada
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

//Esta funcion la tenemos que declarar nosotros, no viene de base, es opcional
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Señalamos a quien pertenece las variables de los botones
            btnLogin = view.findViewById(R.id.button)
            btnRegister = view.findViewById(R.id.button2)
            edtxtCorreo = view.findViewById(R.id.editTextCorreo)
            edtxtContraseña = view.findViewById(R.id.editTextTextPassword2)


    //Añadimos que los botones tengan utilidad debido al OnClickListener
        btnLogin.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
    }



    //Añadimos funciones a los clicks gracias a la funcion de OnClick
    override fun onClick(p0:View?){
        if(p0!!.id== btnLogin.id){
            val usuario=edtxtCorreo.text.toString()
            val contraseña = edtxtContraseña.text.toString()

            if(usuario.isEmpty() || contraseña.isEmpty()){
                //Si los campos de usuario y contraseña estan vacios dara un Log y toast para que la aplicacion NO deje de funcionar
                Log.e(TAG, "No ha rellenado los datos para logearse")
                Toast.makeText(requireActivity(), "Debes rellenar ambos campos", Toast.LENGTH_SHORT).show()
            }else{
                //Si ambos campos NO estan vacios, se ejecutara la siguiente funcion privada
                comprobarPerfil(usuario, contraseña)
            }

        }else if(p0!!.id== btnRegister.id){
            //Si al darle click se le da en registro hara la funcion privada
            irRegistro()

        }
    }



    private fun irRegistro() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }



    private fun comprobarPerfil(email:String, password:String) {
//Con 2 parametros de entrada, Email y Password, se ejecutara la funcion que se implementa desde Firebase la cual usa sus propios metodos.
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
             //Si la funcion  de hacer el login es correcto con ese email y contraseña, hara lo siguiente:
                    Log.d(TAG, "signInWithEmail:success")
                    Log.i(TAG,"El usuario se ha logeado correctamente")
                    val user = auth.currentUser
//Funcion privada para comprobar si ya tienes cuenta o no
                    comprobarDatos()



                } else {
                    //Codigo que, en caso de que ingreses las credenciales que no estan en la BBDD, vaya a pasar
                    Log.w(TAG, "signInWithEmail:failure", task.exception)

                    Toast.makeText(
                        requireActivity(),
                        "Correo o contraseña incorrectas.",
                        Toast.LENGTH_SHORT,
                    ).show()

                }
            }


    }
    @SuppressLint("SuspiciousIndentation")
    //Lo que hace esta funcion es saltarse un fragment, ese donde añades tus primeras credenciales la primera vez
    private fun comprobarDatos(){
        //Recogemos el usuario actual logeado.
        val currentUser = FirebaseAuth.getInstance().currentUser

        currentUser?.let { user ->
            val uid = user.uid


            val docRef = db.collection("Profiles").document(uid)

            docRef.get().addOnSuccessListener { document ->
                if (document != null) { //Si el documento NO ES NULL, es decir, si el documento sacado de Firebase tiene minimamente ALGO

                    val name = document.getString("name")
                    val age = document.getLong("age")

                        if(name!=null || age!=null){
                            //Con que uno de esos campos este completado se saltara el profileFragment e ira directamente a la actividad de HOME
                            val intentHomeActivity: Intent = Intent(requireActivity(), HomeActivity::class.java)
                            requireActivity().startActivity(intentHomeActivity)
                            requireActivity().finish()
                        }else{
                            //Si name o age estan vacios iran al profileFragment para añadir los datos del usuario necesarios
                            Toast.makeText(requireActivity(), "Re-dirigiendose a perfiles", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
                        }




                } else {
                    //Esto pasara si no hay nada creado sobre ese usuario, tan solo la cuenta
                    Log.w(TAG,"No existe un documento para este usuario")
                }
            }.addOnFailureListener { exception ->
                println("Error al obtener los datos: ${exception.message}")
            }
        }
    }


}