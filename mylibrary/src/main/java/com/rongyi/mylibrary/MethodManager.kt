package com.rongyi.mylibrary

import java.lang.reflect.Method

data class MethodManager(val type: Class<*>, val prams: NetType, val method: Method)