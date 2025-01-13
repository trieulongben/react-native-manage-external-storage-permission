package com.manageexternalstoragepermission

import com.facebook.react.BaseReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider
import java.util.HashMap

class ManageExternalStoragePermissionPackage : BaseReactPackage() {
  override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? {
    return if (name == ManageExternalStoragePermissionModule.NAME) {
      ManageExternalStoragePermissionModule(reactContext)
    } else {
      null
    }
  }

  override fun getReactModuleInfoProvider(): ReactModuleInfoProvider {
    return ReactModuleInfoProvider {
      val moduleInfos: MutableMap<String, ReactModuleInfo> = HashMap()
      moduleInfos[ManageExternalStoragePermissionModule.NAME] = ReactModuleInfo(
        ManageExternalStoragePermissionModule.NAME,
        ManageExternalStoragePermissionModule.NAME,
        false,  // canOverrideExistingModule
        false,  // needsEagerInit
        true,  // hasConstants
        false,  // isCxxModule
        true // isTurboModule
      )
      moduleInfos
    }
  }
}
