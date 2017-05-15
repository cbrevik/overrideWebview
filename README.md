Example for PR https://github.com/facebook/react-native/pull/10946#issuecomment-300095135
----

*Important files*

Javascript implementation:
* [CustomWebView.js](./CustomWebView.js)

Usage from JS:
* [app.js](./app.js)

Android implementation:
* [CustomWebViewManager.java](./android/app/src/main/java/com/overridewebview/CustomWebViewManager.java)
    * This file contains all the logic for the custom webview. In this case all I do is extend the ReactWebViewClient and override `shouldOverrideUrlLoading` which lets me hook into URL navigations. Dispatches `onSomethingHappened` event if URL contains a certain string.
* [CustomWebViewManagerPackager.java](./android/app/src/main/java/com/overridewebview/CustomWebViewManagerPackager.java)
    * The packager-class, used in MainApplication to hook up the above ViewManager to the app.

iOS implementation:
* [RCTCustomWebView.h](ios/overrideWebview/RCTCustomWebView.h)
    * Header-file for the webview
* [RCTCustomWebView.m](ios/overrideWebview/RCTCustomWebView.m)
    * Same logic as Android. Overriding `shouldStartLoadWithRequest` to hook into URL navigation. Dispatches `onSomethingHappened` event if URL contains a certain string.
* [RCTCustomWebViewManager.h](ios/overrideWebview/RCTCustomWebViewManager.h)
    * Header-file for view manager
* [RCTCustomWebViewManager.m](ios/overrideWebview/RCTCustomWebViewManager.m)
    * This is basically a copy-paste of RN's RCTWebViewManager. The only differences (noted in the file comments) is:
        1. Imports at the top
        2. The WebView returned (have to use the custom RCTCustomWebView of course)
        3. We also have to export the `onSomethingHappened` view property
* [RCTWebView+Custom.h](ios/overrideWebview/RCTWebView+Custom.h)
    * Adding a category to `RCTWebView` so we can expose (& call) `shouldStartLoadWithRequest` from (the child-cass) `RCTCustomWebView`. Not really important to this example, but good to note.