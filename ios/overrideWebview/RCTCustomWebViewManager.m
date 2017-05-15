#import "RCTCustomWebViewManager.h"

#import <React/RCTBridge.h>
#import <React/RCTUIManager.h>
#import "RCTCustomWebView.h"
#import <React/UIView+React.h>

@interface RCTCustomWebViewManager () <RCTWebViewDelegate>

@end

@implementation RCTCustomWebViewManager
{

}

RCT_EXPORT_MODULE()

- (UIView *)view
{
  RCTCustomWebView *webView = [RCTCustomWebView new];
  webView.delegate = self;
  return webView;
}

RCT_EXPORT_VIEW_PROPERTY(onSomethingHappened, RCTDirectEventBlock)


@end
