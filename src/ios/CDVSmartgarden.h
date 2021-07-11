#import <Cordova/CDVPlugin.h>

@interface CDVSmartgarden : CDVPlugin

- (void)changeTitle:(CDVInvokedUrlCommand*)command;
- (void)showBottomBar:(CDVInvokedUrlCommand*)command;
- (void)hideBottomBar:(CDVInvokedUrlCommand*)command;
- (void)setBottomBarItem:(CDVInvokedUrlCommand*)command;
@end
