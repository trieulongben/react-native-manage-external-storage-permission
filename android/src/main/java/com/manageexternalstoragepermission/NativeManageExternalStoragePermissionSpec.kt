
package com.manageexternalstoragepermission

import com.facebook.proguard.annotations.DoNotStrip
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.turbomodule.core.interfaces.TurboModule
import javax.annotation.Nonnull

abstract class NativeManageExternalStoragePermissionSpec(reactContext: ReactApplicationContext?) :
    ReactContextBaseJavaModule(reactContext), TurboModule {
    @Nonnull
    override fun getName(): String {
        return NAME
    }

    @ReactMethod
    @DoNotStrip
    abstract fun getExternalStoragePermission(promise: Promise?)

    @ReactMethod
    @DoNotStrip
    abstract fun requestExternalStoragePermission(promise: Promise?)

    companion object {
        const val NAME: String = "ManageExternalStoragePermission"
    }
}
