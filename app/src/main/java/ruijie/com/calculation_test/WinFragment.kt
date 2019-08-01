package ruijie.com.calculation_test


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import ruijie.com.calculation_test.databinding.FragmentWinBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class WinFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel = ViewModelProviders.of(requireActivity(), SavedStateViewModelFactory(requireActivity())).get(MyViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentWinBinding>(inflater, R.layout.fragment_win, container, false)
        binding.data = viewModel
        binding.lifecycleOwner = requireActivity()
        binding.buttonBack.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_winFragment_to_titleFragment)
        }

        return binding.root
    }


}
