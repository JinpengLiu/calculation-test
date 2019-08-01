package ruijie.com.calculation_test


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import ruijie.com.calculation_test.databinding.FragmentTitleBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val myViewModel:MyViewModel = ViewModelProviders.of(requireActivity(), SavedStateViewModelFactory(requireActivity())).get(MyViewModel::class.java)
        val titleBinding:FragmentTitleBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
        titleBinding.data = myViewModel
        titleBinding.lifecycleOwner = requireActivity()
        titleBinding.button.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_titleFragment_to_questionFragment)
        }
        return titleBinding.root
    }


}
