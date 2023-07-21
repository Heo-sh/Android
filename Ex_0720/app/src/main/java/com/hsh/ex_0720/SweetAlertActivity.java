package com.hsh.ex_0720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class SweetAlertActivity extends AppCompatActivity {

    Button warning, error, success;

    SweetAlertDialog sweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweet_alert);

        warning = findViewById(R.id.warning);
        error = findViewById(R.id.error);
        success = findViewById(R.id.success);

        warning.setOnClickListener(click);
        error.setOnClickListener(click);
        success.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            if (id == warning.getId()) {
                sweetAlert("warning", getString(R.string.alert_warning), SweetAlertDialog.WARNING_TYPE, SweetAlertActivity.this);
            } else if (id == error.getId()) {
                sweetAlert("error", getString(R.string.alert_error), SweetAlertDialog.ERROR_TYPE, SweetAlertActivity.this);
            } else if (id == success.getId()) {
                sweetAlert("success", getString(R.string.alert_success), SweetAlertDialog.SUCCESS_TYPE, SweetAlertActivity.this);
            }

        }
    };

    //Type별 Dialog 호출 메서드
    public void sweetAlert(String msg, String title, int type, Context context) {
        sweetAlertDialog = new SweetAlertDialog(context, type);
        sweetAlertDialog.setTitleText(title);
        sweetAlertDialog.setContentText(msg);
        sweetAlertDialog.setConfirmText("OK");

        sweetAlertDialog.show();
    }


















}