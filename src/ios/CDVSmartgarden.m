#import "CDVSmartgarden.h"
#import <Cordova/CDVPlugin.h>
#import "ViewController.h"

@implementation CDVSmartgarden

- (void)changeTitle:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* title = [command.arguments objectAtIndex:0];

    if (title != nil && [title length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:title];
        
        ViewController *vc = (ViewController *)self.viewController.parentViewController;
        [vc.topNavigtion setTitle:title];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)showBottomBar:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"OK"];
    
    ViewController *vc = (ViewController *)self.viewController.parentViewController;
    [vc.bottomNavigation setHidden:false];
    
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)hideBottomBar:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"OK"];
    
    ViewController *vc = (ViewController *)self.viewController.parentViewController;
    [vc.bottomNavigation setHidden:true];
    
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setBottomBarItem:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* item = [command.arguments objectAtIndex:0];

    if (item != nil && [item length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:item];
        
        ViewController *vc = (ViewController *)self.viewController.parentViewController;
        [vc resetButtonTints];
        [vc setBottomButtonActive:item];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
