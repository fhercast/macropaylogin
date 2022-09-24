package com.fdac.macropayloginexample.Utils;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class CodeGenerator {

    public static Bitmap CodeBar(String data)
    {
        Bitmap bitmap = null;
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.encodeBitmap("content", BarcodeFormat.CODE_128, 600, 200);

        } catch(Exception e) {

        }
        return bitmap;
    }
}
