import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  getExternalStoragePermission(): Promise<boolean>;
  requestExternalStoragePermission(): Promise<boolean>;
}

export default TurboModuleRegistry.getEnforcing<Spec>(
  'ManageExternalStoragePermission'
);
