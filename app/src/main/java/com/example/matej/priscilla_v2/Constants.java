package com.example.matej.priscilla_v2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import androidx.annotation.RequiresApi;

public class Constants {
    private static Constants _instance;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;

    public static Constants instance(Context context) {
        if (_instance == null) {
            _instance = new Constants();
            _instance.configSessionUtils(context);
        }
        return _instance;
    }

    public static Constants instance() {
        return _instance;
    }

    public void configSessionUtils(Context context) {
        sharedPref = context.getSharedPreferences("SP", Activity.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
    }

    public void storeValueString(String key, String value) {
        sharedPrefEditor.putString(key, value);
        sharedPrefEditor.apply();
    }

    public String fetchValueString(String key) {
        return sharedPref.getString(key, null);
    }

}
