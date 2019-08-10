package com.example.matej.priscilla_v2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class LoginResolver {

    public static SharedPreferences getSharedPreferences(Context ctx) {
        return ctx.getSharedPreferences("SP", Activity.MODE_PRIVATE);
    }

    public static void clearSp(Context ctx){
        SharedPreferences preferences = LoginResolver.getSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
