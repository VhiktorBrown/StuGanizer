package com.chocolatedevelopers.stuganizer.lists.classes;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class OnUxThread {

    public static void run(Thread t, View v) {
        try {
            Activity a = (Activity)v.getContext();
            a.runOnUiThread(t);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void run(Thread t, Context v) {
        Activity a = (Activity)v;
        a.runOnUiThread(t);
    }
}
