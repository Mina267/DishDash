package com.example.dishdash.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

public class NetworkConnectionStatus {
    private static NetworkConnectionStatus instance;
    private ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;
    private boolean isRegistered = false;

    private NetworkConnectionStatus(Context context) {
        /*  first obtain an instance of ConnectivityManager: */
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

     /* Singleton */
    public static synchronized NetworkConnectionStatus getInstance(Context context) {
        if (instance == null) {
            instance = new NetworkConnectionStatus(context.getApplicationContext());
        }
        return instance;
    }

    /* Check if network is available and can access or not */
    public boolean isNetworkAvailable() {
        /* Second: use this instance to get a reference to the current default network for your app: */
        Network network = connectivityManager.getActiveNetwork();
        if (network == null)
        { return false;}
        /* Third: With a reference to a network, your app can request information about it: */
        NetworkCapabilities caps  = connectivityManager.getNetworkCapabilities(network);
        /*
            NET_CAPABILITY_INTERNET: indicates that the network is set up to access the internet.
            This is about setup and not actual ability to reach public servers
            NET_CAPABILITY_VALIDATED: indicates that the network provides actual access to the public internet when it is probed.
            A network behind a captive portal or a network that doesn't provide domain name resolution doesn't have this capability.
         */
        if (caps != null && caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /* network callback to monitor connection changes */
    public void registerNetworkCallback(NetworkChangeListener listener) {
        if (!isRegistered) {
            NetworkRequest networkRequest = new NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    .build();

            networkCallback = new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    listener.onNetworkAvailable();
                }

                @Override
                public void onLost(Network network) {
                    listener.onNetworkLost();
                }
            };

            connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
            isRegistered = true;
        }
    }


    /*
     * Unregister your callback when you have no use for it anymore by calling
     * ConnectivityManager.unregisterNetworkCallback(NetworkCallback).
     * Your main activity's onPause() is a good place to do this,
     * especially if you register the callback in onResume().
     */
    public void unregisterNetworkCallback() {
        if (networkCallback != null && isRegistered) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
            isRegistered = false;
        }
    }

    /* interface to communicate connectivity  */
    public interface NetworkChangeListener {
        void onNetworkAvailable();
        void onNetworkLost();
    }
}
