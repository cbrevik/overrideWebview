import React, { Component } from 'react';
import {
  AppRegistry,
} from 'react-native';

import App from './app';

export default class overrideWebview extends Component {
  render() {
    return (
      <App />
    );
  }
}

AppRegistry.registerComponent('overrideWebview', () => overrideWebview);