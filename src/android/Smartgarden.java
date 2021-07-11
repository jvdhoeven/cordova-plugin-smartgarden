package de.joergv.smartgarden.plugin;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.engine.SystemWebView;
import org.json.JSONArray;
import org.json.JSONException;

import de.joergv.smartgarden.R;

public class Smartgarden extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("changeTitle")) {
            String message = args.getString(0);
            if (message != null && message.length() > 0) {
                cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Activity activity = cordova.getActivity();
                        Toolbar toolbar = activity.findViewById(R.id.toolbar);
                        toolbar.setTitle(message);
                        callbackContext.success(); // Thread-safe.
                    }
                });
            } else {
                callbackContext.error("Expected one non-empty string argument.");
            }
            return true;
        }
        if (action.equals("showBottomBar")) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Activity activity = cordova.getActivity();

                    BottomNavigationView bottomNavigationView = (BottomNavigationView) activity.findViewById(R.id.bottom_nav);
                    bottomNavigationView.setVisibility(View.VISIBLE);

                    Integer pixels = 56 * ((int) activity.getApplicationContext().getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);

                    SystemWebView theWebView = (SystemWebView) activity.findViewById(R.id.tutorialView);
                    bottomNavigationView.getLayoutParams().height = pixels;
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) theWebView.getLayoutParams();
                    params.bottomMargin = pixels;

                    callbackContext.success(); // Thread-safe.
                }
            });
            return true;
        }
        if (action.equals("hideBottomBar")) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Activity activity = cordova.getActivity();
                    BottomNavigationView bottomNavigationView = (BottomNavigationView) activity.findViewById(R.id.bottom_nav);
                    bottomNavigationView.setVisibility(View.INVISIBLE);

                    SystemWebView theWebView = (SystemWebView) activity.findViewById(R.id.tutorialView);
                    bottomNavigationView.getLayoutParams().height = 0;
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) theWebView.getLayoutParams();
                    params.bottomMargin = 0;

                    callbackContext.success(); // Thread-safe.
                }
            });
            return true;
        }
        if (action.equals("setBottomBarItem")) {
            String item = args.getString(0);
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Activity activity = cordova.getActivity();

                    BottomNavigationView bottomNavigationView = (BottomNavigationView) activity.findViewById(R.id.bottom_nav);

                    Menu menu = bottomNavigationView.getMenu();
                    MenuItem menuItem = menu.findItem(R.id.menu_select);
                    switch (item) {
                        case "dashboard":
                            menuItem = menu.findItem(R.id.menu_dashboard);
                            break;
                        case "watering":
                            menuItem = menu.findItem(R.id.menu_watering);
                            break;
                        case "settings":
                            menuItem = menu.findItem(R.id.menu_settings);
                            break;
                    }
                    menuItem.setChecked(true);

                    callbackContext.success(); // Thread-safe.
                }
            });
        }
        return false;
    }
}