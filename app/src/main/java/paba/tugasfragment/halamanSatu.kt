package paba.tugasfragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [halamanSatu.newInstance] factory method to
 * create an instance of this fragment.
 */
class halamanSatu : Fragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var score = 50;
        var angkaAwal = 1
        var angkaAkhir = 5

        val _tvScore = view.findViewById<TextView>(R.id.tvScore)
        _tvScore.text = score.toString()

//        GIVE UP
        val _btnGiveUp = view.findViewById<Button>(R.id.btnGiveUp)
        _btnGiveUp.setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString("DATASCORE", score.toString())

            val mhalamanDua = halamanDua()
            mhalamanDua.arguments = mBundle

            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(R.id.frameContainer, mhalamanDua, halamanDua::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        val buttons = arrayOf(
            view.findViewById<Button>(R.id.btn1),
            view.findViewById<Button>(R.id.btn2),
            view.findViewById<Button>(R.id.btn3),
            view.findViewById<Button>(R.id.btn4),
            view.findViewById<Button>(R.id.btn5),
            view.findViewById<Button>(R.id.btn6),
            view.findViewById<Button>(R.id.btn7),
            view.findViewById<Button>(R.id.btn8),
            view.findViewById<Button>(R.id.btn9),
            view.findViewById<Button>(R.id.btn10)

        )
        if (arguments != null) {
            val txtHasil = arguments?.getString("DATAANGKA")
            angkaAwal = txtHasil.toString().toInt()
            angkaAkhir = angkaAwal + txtHasil.toString().toInt()
        }

        val pairedNumbers = mutableListOf<Int>()
        for (i in angkaAwal..angkaAkhir) {
            pairedNumbers.add(i)
            pairedNumbers.add(i)
        }

        pairedNumbers.shuffle()

        for (button in buttons) {
            button.text = ""
        }

        // Inside the `onViewCreated` function
        var lastClickedIndex: Int? = null

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                val currentValue = pairedNumbers[index]

                // Show the number on the clicked button
                button.text = currentValue.toString()

                if (lastClickedIndex == null) {
                    // First button click, store index reference
                    lastClickedIndex = index
                } else {
                    // Second button click, compare with the first button's value
                    val firstButton = buttons[lastClickedIndex!!]

                    if (pairedNumbers[lastClickedIndex!!] == currentValue) {
                        // Numbers match, increase score and disable both buttons
                        score += 10
                        button.isEnabled = false
                        firstButton.isEnabled = false
                    } else {
                        // Numbers do not match, decrease score
                        score -= 5

                        // Show both numbers briefly, then hide them with a delay
                        Handler(Looper.getMainLooper()).postDelayed({
                            button.text = ""
                            firstButton.text = ""
                        }, 1000) // Delay of 1 second (1000 ms)
                    }

                    // Update score display
                    _tvScore.text = score.toString()

                    // Reset for the next pair attempt
                    lastClickedIndex = null
                }

                // Check if all buttons are disabled (all pairs matched)
                if (buttons.all { !it.isEnabled }) {
                    val mBundle = Bundle()
                    mBundle.putString("DATASCORE", score.toString())

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
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_halaman_satu, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment halamanSatu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            halamanSatu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}