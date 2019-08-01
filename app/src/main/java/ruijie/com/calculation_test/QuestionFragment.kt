package ruijie.com.calculation_test


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import ruijie.com.calculation_test.databinding.FragmentQuestionBinding
import java.lang.StringBuilder


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class QuestionFragment : Fragment() {

    private lateinit var viewModel: MyViewModel
    private lateinit var questionBinding: FragmentQuestionBinding
    private val builder:StringBuilder = StringBuilder()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        viewModel = ViewModelProviders.of(requireActivity(), SavedStateViewModelFactory(requireActivity())).get(MyViewModel::class.java)
        viewModel.getCurrentScore().value = 0
        viewModel.generator()
        questionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        questionBinding.data = viewModel
        questionBinding.lifecycleOwner = requireActivity()

        initView()
        return questionBinding.root
    }

    private fun initView() {
        questionBinding.button0.setOnClickListener(numClickListener)
        questionBinding.button1.setOnClickListener(numClickListener)
        questionBinding.button2.setOnClickListener(numClickListener)
        questionBinding.button3.setOnClickListener(numClickListener)
        questionBinding.button4.setOnClickListener(numClickListener)
        questionBinding.button5.setOnClickListener(numClickListener)
        questionBinding.button6.setOnClickListener(numClickListener)
        questionBinding.button7.setOnClickListener(numClickListener)
        questionBinding.button8.setOnClickListener(numClickListener)
        questionBinding.button9.setOnClickListener(numClickListener)
        questionBinding.buttonClear.setOnClickListener(clearClickListener)
        questionBinding.buttonSubmit.setOnClickListener(submitClickListener)
    }

    private val numClickListener:View.OnClickListener = View.OnClickListener {
        val button:Button = it as Button
        builder.append(button.text)
        questionBinding.tvYourAnswer.text = builder.toString()
    }

    private val clearClickListener:View.OnClickListener = View.OnClickListener {
        builder.setLength(0)
        questionBinding.tvYourAnswer.setText(R.string.input_indicator)
    }

    private val submitClickListener:View.OnClickListener = View.OnClickListener {
        if (builder.isEmpty()) {
            Toast.makeText(requireContext(), R.string.answer_indicator_message, Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }

        if (builder.toString().toInt() == viewModel.getAnswer().value) {
            viewModel.answerCorrect()
            builder.setLength(0)
            questionBinding.tvYourAnswer.setText(R.string.answer_correct_message)
        } else {
            val navController = Navigation.findNavController(it)
            if (viewModel.winFlag) {
                navController.navigate(R.id.action_questionFragment_to_winFragment)
                viewModel.saveHighScore()
                viewModel.winFlag = false
            } else {
                navController.navigate(R.id.action_questionFragment_to_loseFragment)
            }
        }
    }
}
