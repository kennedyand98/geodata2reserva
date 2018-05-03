package usjt.br.geodata.model.service;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by KENNEDY on 02/05/2018.
 */

public class NetworkStatus {

    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
