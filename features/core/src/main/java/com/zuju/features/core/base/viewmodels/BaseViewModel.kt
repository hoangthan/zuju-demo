package com.zuju.features.core.base.viewmodels

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<EVENT> : ViewModel() {

    abstract fun dispatchEvent(event: EVENT)
}
