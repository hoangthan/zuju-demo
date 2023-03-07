package com.zuju.features.core.base.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.zuju.data.core.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

interface ProgressScreen {
    val loadingView: View
}

fun interface ErrorCallback {
    operator fun invoke(error: Result.Error)
}

//region Fragment+ProgressScreen
fun <T, D> T.consumeResult(
    result: Result<D>,
    onError: ErrorCallback? = null,
    onSuccess: (D) -> Unit,
) where T : Fragment, T : ProgressScreen {
    when (result) {
        is Result.Loading -> loadingView.show()
        is Result.Error -> {
            loadingView.hide()
            if (onError != null) onError(result)
            else showToast(result.toString())
        }
        is Result.Success -> {
            loadingView.hide()
            onSuccess(result.data)
        }
    }
}

fun <T, D> T.consumeResultFlow(
    flow: Flow<Result<D>>,
    onSuccess: (D) -> Unit,
) where T : Fragment, T : ProgressScreen {
    observeFlow(flow = flow, state = Lifecycle.State.CREATED) {
        consumeResult(result = it, onSuccess = onSuccess, onError = null)
    }
}

fun <T, D> T.consumeResultFlow(
    flow: Flow<Result<D>>
) where T : Fragment, T : ProgressScreen {
    observeFlow(flow = flow, state = Lifecycle.State.CREATED) { result ->
        consumeResult(result = result, onSuccess = { loadingView.hide(true) }, onError = null)
    }
}
//endregion

//region Fragment
fun <T> Fragment.observeFlow(
    flow: Flow<T>,
    observer: suspend (T) -> Unit,
) {
    observeFlow(Lifecycle.State.CREATED, flow, observer)
}

fun <T> Fragment.observeFlow(
    state: Lifecycle.State, flow: Flow<T>, observer: suspend (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(state) {
            flow.collectLatest { observer(it) }
        }
    }
}
//endregion
