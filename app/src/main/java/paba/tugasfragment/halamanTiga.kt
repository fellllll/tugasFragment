package paba.tugasfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [halamanTiga.newInstance] factory method to
 * create an instance of this fragment.
 */
class halamanTiga : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_halaman_tiga, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        PINDAH KE FRAG 1 N KIRIM DATA
        val _edtBatasAngka = view.findViewById<EditText>(R.id.edtBatasAngka)
        val _btnFrag1 = view.findViewById<Button>(R.id.btnFrag1)
        _btnFrag1.setOnClickListener {

            val angka = _edtBatasAngka.text.toString()
            val mBundle = Bundle()
            mBundle.putString("DATAANGKA", angka)

            val mhalamanSatu = halamanSatu()
            mhalamanSatu.arguments = mBundle

            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mhalamanSatu, halamanSatu::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }



//        PINDAH KE FRAG 2 N KIRIM SCORE
        val _btnFrag2 = view.findViewById<Button>(R.id.btnFrag2)
        _btnFrag2.setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString("DATASCORE", arguments?.getString("DATASCORE"))

            val mhalamanDua = halamanDua()
            mhalamanDua.arguments = mBundle

            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mhalamanDua, halamanDua::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment halamanTiga.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            halamanTiga().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}