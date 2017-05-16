import React, { Component, PropTypes } from 'react';
import {
    WebView
} from 'react-native';

import requireNativeComponent from 'requireNativeComponent';

class CustomWebView extends Component {
    constructor(props) {
        super(props);
    }

    static propTypes = {
        ...WebView.propTypes,
        onSomethingHappened: PropTypes.func,
    };

    onSomethingHappenedEvent = (event) => {
        var { onSomethingHappened } = this.props;
        onSomethingHappened && onSomethingHappened(event);
    }

    render() {
        return (
            <WebView
                {...this.props}
                nativeComponent={RCTCustomWebView}
                nativeComponentProps={{ onSomethingHappened: this.onSomethingHappenedEvent }}
            />
        );
    }
}

var RCTCustomWebView = requireNativeComponent('RCTCustomWebView', CustomWebView, WebView.extraNativeComponentConfig);

export default CustomWebView;