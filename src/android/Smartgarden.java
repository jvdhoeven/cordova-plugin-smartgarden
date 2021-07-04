package de.joergv.smartgarden.plugin;

import android.app.Activity;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
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
                    switch (item) {
                        case "select":
                            bottomNavigationView.setSelectedItemId(R.id.menu_select);
                        case "dashboard":
                            bottomNavigationView.setSelectedItemId(R.id.menu_dashboard);
                        case "watering":
                            bottomNavigationView.setSelectedItemId(R.id.menu_watering);
                        case "settings":
                            bottomNavigationView.setSelectedItemId(R.id.menu_settings);
                    }

                    callbackContext.success(); // Thread-safe.
                }
            });
            return true;
        }
        return false;
    }
}