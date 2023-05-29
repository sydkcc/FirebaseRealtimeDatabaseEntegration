package app.presentation.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

//B : Binding(.xml Binding)
//M : Model(Response)
abstract class BaseDialogHelper<T : ViewDataBinding, M>
    : DialogFragment() {
    protected var dataBinding: T? = null
    var onItemClick: ((M) -> Unit)? = null
    var onDismisClick: (() -> Unit)? = null

    val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, getContentView(), container, false)
        dataBinding?.executePendingBindings()
        return dataBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subScribe()
    }


    @CallSuper
    protected open fun subScribe() {
    }

    protected abstract fun getContentView(): Int

    override fun onDestroy() {
        super.onDestroy()
        dataBinding = null
    }
}

enum class PopupActionType(val type: String) {
    CLOSE("CLOSE"),
    DISMISS("DISMISS"),
    DELETE("DELETE"),
    OK("OK"),
    YES("YES"),
    NO("NO"),
    FEMALE("FEMALE"),
    MALE("MALE"),

}