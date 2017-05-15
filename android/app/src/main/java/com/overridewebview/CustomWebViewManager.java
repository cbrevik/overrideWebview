package com.overridewebview;

import java.util.Map;

import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.webview.ReactWebViewManager;
import com.facebook.react.views.webview.WebViewConfig;


@ReactModule(name = CustomWebViewManager.REACT_CLASS)
public class CustomWebViewManager extends ReactWebViewManager {
    /* This name must match what we're referring to in JS */
    protected static final String REACT_CLASS = "RCTCustomWebView";

    protected static class CustomWebViewClient extends ReactWebViewClient {
        private class SomethingHappenedEvent extends Event<SomethingHappenedEvent> {
            private WritableMap mParams;

            public SomethingHappenedEvent(int viewTag, WritableMap params) {
                super(viewTag);
                this.mParams = params;
            }

            @Override
            public String getEventName() {
                return "somethingHappened";
            }

            @Override
            public void dispatch(RCTEventEmitter rctEventEmitter) {
                init(getViewTag());
                rctEventEmitter.receiveEvent(getViewTag(), getEventName(), mParams);
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null && url.contains("github.com/facebook/react-native")) {
                final WritableMap params = Arguments.createMap();
                params.putString("message", "Yes indeed!");
                dispatchEvent(view, new SomethingHappenedEvent(view.getId(), params));
            }

            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected void addEventEmitters(ThemedReactContext reactContext, WebView view) {
        // Do not register default touch emitter and let WebView implementation handle touches
        view.setWebViewClient(new CustomWebViewClient());
    }

    protected static void dispatchEvent(WebView webView, Event event) {
        ReactContext reactContext = (ReactContext) webView.getContext();
        EventDispatcher eventDispatcher =
                reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
        eventDispatcher.dispatchEvent(event);
    }

    public @Nullable
    Map getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> s = super.getExportedCustomDirectEventTypeConstants();

        return MapBuilder.of("somethingHappened", MapBuilder.of("registrationName", "onSomethingHappened"));
    }
}
