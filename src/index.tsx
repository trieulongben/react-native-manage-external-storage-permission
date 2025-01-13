import ManageExternalStoragePermission from './NativeManageExternalStoragePermission';

export function getExternalStoragePermission(): Promise<boolean> {
  return ManageExternalStoragePermission.getExternalStoragePermission();
}

export function requestExternalStoragePermission(): Promise<boolean> {
  return ManageExternalStoragePermission.requestExternalStoragePermission();
}
