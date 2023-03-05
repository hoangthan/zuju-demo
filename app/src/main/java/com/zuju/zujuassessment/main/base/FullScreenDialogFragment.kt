package com.zuju.zujuassessment.main.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.zuju.zujuassessment.R

abstract class FullScreenDialogFragment<BINDING : ViewBinding> : DialogFragment(),
    View.OnClickListener {

    protected lateinit var binding: BINDING

    /**
     * LeadingView is the icon at the most top left of screen.
     * Regularly, is gonna be an close icon, hamburger button, back icon..
     */
    private val _leadingView: View? by lazy { getLeadingView() }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.FullDialogFragmentStyle)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        requireDialog().let {
            val matchParent = ViewGroup.LayoutParams.MATCH_PARENT
            it.window?.setLayout(matchParent, matchParent)
        }
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = getViewBinding(inflater)
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewListener()
        initHeaderListener()
    }

    private fun initHeaderListener() {
        _leadingView?.setOnClickListener(this)
    }

    /**
     * This function MUST be implemented to return the inflated view binding for fragment
     */
    abstract fun getViewBinding(inflater: LayoutInflater): BINDING

    @CallSuper
    override fun onClick(view: View?) {
        //Maybe we will need track what view is clicked, which area is the most clicked...
        view ?: return
        when (view.id) {
            _leadingView?.id -> onLeadingClicked()
            else -> onViewClicked(view)
        }
    }

    /**
     * This function should be override if you want to trigger when view is clicked and keep tracking also
     */
    protected open fun onViewClicked(view: View) {}

    /**
     * This function should be override if you want initial view's listener such as: Click, TextChange, Checkbox..
     */
    protected open fun initViewListener() {}

    protected open fun getLeadingView(): View? = null

    /**
     * This function should be override if you want initial view for first time after view is created
     */
    protected open fun initView() {}

    /**
     * This function is used to handle when the `LeadingView`
     */
    protected open fun onLeadingClicked() {
        dismiss()
    }
}
