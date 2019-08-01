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
import ruijie.com.calculation_test.databinding.FragmentLoseBinding


/**
 * 失败页面
 */
class LoseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val viewModel = ViewModelProviders.of(requireActivity(), SavedStateViewModelFactory(requireActivity())).get(MyViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentLoseBinding>(inflater, R.layout.fragment_lose, container, false)
        binding.data = viewModel
        binding.lifecycleOwner = requireActivity()
        binding.buttonBack.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_loseFragment_to_titleFragment)
        }

        return binding.root
    }

}
