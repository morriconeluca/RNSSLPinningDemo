import React from 'react';
import {SafeAreaView, Text, Button, StyleSheet} from 'react-native';

const App = () => {
  const onPress = async () => {
    try {
      const response = await fetch(
        'https://jsonplaceholder.typicode.com/todos/1',
      );
      const json = await response.json();
      console.log('FETCH SUCCESS:', JSON.stringify(json));
    } catch (error) {
      console.log('FETCH ERROR:', error);
    }
  };

  return (
    <SafeAreaView style={styles.container}>
      <Button onPress={onPress} title="FETCH" />
      <Text style={styles.message}>
        Click on the button and look at console!
      </Text>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingHorizontal: 24,
    justifyContent: 'center',
  },
  message: {
    marginTop: 24,
    alignSelf: 'center',
  },
});

export default App;
