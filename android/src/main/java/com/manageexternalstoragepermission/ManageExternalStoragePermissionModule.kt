package com.manageexternalstoragepermission

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.facebook.react.bridge.ActivityEventListener
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule


@ReactModule(name = ManageExternalStoragePermissionModule.NAME)
class ManageExternalStoragePermissionModule(reactContext: ReactApplicationContext) :
  NativeManageExternalStoragePermissionSpec(reactContext), ActivityEventListener {

  init {
    reactContext.addActivityEventListener(this)
  }

  override fun getName(): String {
    return NAME
  }


  override fun getExternalStoragePermission(promise: Promise?) {
    if (promise===null) return
    if (Build.VERSION.SDK_INT >= 30) {
      promise.resolve(Environment.isExternalStorageManager())
    } else {
      val result = ContextCompat.checkSelfPermission(reactApplicationContext, READ_EXTERNAL_STORAGE)
      val result1 =
        ContextCompat.checkSelfPermission(reactApplicationContext, WRITE_EXTERNAL_STORAGE)
      val checkResult=result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
      promise.resolve(checkResult)
    }
  }

  override fun requestExternalStoragePermission(promise: Promise?) {
    if (Build.VERSION.SDK_INT >= 30) {
      try {
        val intent: Intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
        intent.addCategory("android.intent.category.DEFAULT")
        intent.setData(Uri.parse(String.format("package:%s", reactApplicationContext.packageName)))
        currentActivity!!.startActivityForResult(intent, 2296)
        promise?.resolve(true)
      } catch (e: Exception) {
        val intent = Intent()
        intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
        currentActivity!!.startActivityForResult(intent, 2296)
        promise?.reject(e)
      }
    } else {
      ActivityCompat.requestPermissions(currentActivity!!, arrayOf(WRITE_EXTERNAL_STORAGE), 100)
    }
  }




  companion object {
    const val NAME = "ManageExternalStoragePermission"
  }

  override fun onActivityResult(
    activity: Activity?,
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    if (requestCode == 2296) {
      if (Build.VERSION.SDK_INT >= 30) {
        if (Environment.isExternalStorageManager()) {
          Toast.makeText(reactApplicationContext, "Access granted", Toast.LENGTH_SHORT).show()
        } else {
          Toast.makeText(reactApplicationContext, "Access not granted", Toast.LENGTH_SHORT).show()
        }
      }
    }
  }

  override fun onNewIntent(p0: Intent?) {
    TODO("Not yet implemented")
  }
}
