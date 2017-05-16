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
        somethingHappenedUrl: PropTypes.string
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
                nativeComponentProps={{ onSomethingHappened: this.onSomethingHappenedEvent, somethingHappenedUrl: this.props.somethingHappenedUrl }}
            />
        );
    }
}

var RCTCustomWebView = requireNativeComponent('RCTCustomWebView', CustomWebView, WebView.extraNativeComponentConfig);

export default CustomWebView;