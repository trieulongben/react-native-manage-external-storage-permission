import { Text, View, StyleSheet, TouchableOpacity } from 'react-native';
import { requestExternalStoragePermission } from 'react-native-manage-external-storage-permission';

export default function App() {
  return (
    <View style={styles.container}>
      <TouchableOpacity
        onPress={() => {
          requestExternalStoragePermission().then((permission) => {
            console.log('Permission:', permission);
          }).catch((e) => {
            console.log('Error:', e);
          });
        }}
      >
        <Text>Get Permission</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
