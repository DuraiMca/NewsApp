package com.newsapp.duraimurugan.Utility;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


import com.newsapp.duraimurugan.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Utility extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
    }





    public static boolean isConnectingToInternet(Context d_context) {
        boolean isConnected = false;
        try {
            ConnectivityManager d_connectivityMgr = (ConnectivityManager) d_context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkinfo = d_connectivityMgr.getActiveNetworkInfo();
            if (networkinfo != null && networkinfo.isConnected() && networkinfo.getState() == NetworkInfo.State.CONNECTED) {
                isConnected = true;
                Log.i("IsConnected", "" + isConnected);
            } else {
                Toast.makeText(d_context, "Internet not connected", Toast.LENGTH_SHORT).show();

            }


        } catch (Exception ex) {

            Log.i("Er: Check_Connectivity", ex.getMessage());
        }
        return isConnected;
    }



}
