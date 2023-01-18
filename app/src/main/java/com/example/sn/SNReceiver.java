package com.example.sn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.FileReader;

public class SNReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getStringExtra("ask").equals("SN")) {
            try {
                Intent it = new Intent("com.symlink.SN");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(context.getFilesDir().toString() + "/tmp.txt"));
                String SN = bufferedReader.readLine();
                it.putExtra("SN", SN);
                context.sendBroadcast(it);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}