package com.mikko.intellimap.dispatchers

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

val ioDispatcher : CoroutineContext
    get() = Dispatchers.IO
val uiDispatcher : CoroutineContext
    get() = Dispatchers.Main