#import "RCTCustomWebView.h"
#import <React/RCTWebView.h>
#import "RCTWebView+Custom.h"

@interface RCTCustomWebView ()

@property (nonatomic, copy) RCTDirectEventBlock onSomethingHappened;

@end

@implementation RCTCustomWebView
{
  
}

- (BOOL)webView:(__unused UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request
 navigationType:(UIWebViewNavigationType)navigationType
{
  BOOL allowed = [super webView:webView shouldStartLoadWithRequest:request navigationType:navigationType];
  
  if (allowed) {
    NSString* url = request.URL.absoluteString;
    if (url && [url localizedCaseInsensitiveContainsString:_somethingHappenedUrl]){
      if (_onSomethingHappened) {
        NSMutableDictionary<NSString *, id> *event = [[NSMutableDictionary alloc] initWithDictionary:@{ @"message": @"Yes indeed!" }];
        _onSomethingHappened(event);
      }
    }
  }
  
  return allowed;
}

@end
