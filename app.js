/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
    WebView,
    StyleSheet
} from 'react-native';

import CustomWebView from './CustomWebView';

export default class overrideWebview extends Component {
    render() {
        return (
            <CustomWebView
                style={styles.webView}
                source={{ uri: 'https://github.com/facebook/react-native' }}
                onSomethingHappened={({ nativeEvent }) => {
                    console.log('Something happened!', nativeEvent.message)
                }}
            />
        );
    }
}

const styles = StyleSheet.create({
    webView: {
        flex: 1,
    }
});