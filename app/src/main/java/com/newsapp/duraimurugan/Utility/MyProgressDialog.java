package com.newsapp.duraimurugan.Utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.newsapp.duraimurugan.R;

public class MyProgressDialog extends ProgressDialog {
    private TextView Text_Message;
    private String message;
    public MyProgressDialog(Context context, @Nullable String message) {
        super(context);
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Text_Message = (TextView) findViewById(R.id.Text_Message);
        if (!TextUtils.isEmpty(message)) {
            Text_Message.setText(message);
        }
    }

}