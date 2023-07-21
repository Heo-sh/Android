package com.hsh.ex_0721;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        //현재 수락이 필요한 권한들에 대한 상태 확인
        //전화걸기, 기록하기, 주소록 접근하기에 대한 권한이 비활성화 상태인지 확인

        //ActivityCompat: 권한을 확인하라고 제공하는 Class(원래 제공됨.)
        if(ActivityCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //비활성화되어 있는 상태일 때
            //전화 권한 수락
            setPermission();
            return;
        }

        if(ActivityCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //비활성화되어 있는 상태일 때
            //기록 권한 수락
            setPermission();
            return;
        }
        
        if(ActivityCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //비활성화되어 있는 상태일 때
            //주소록 접근 권한 수락
            setPermission();
            return;
        }
    }

    //앱 권한 설정에 관한 감지자
    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            //모든 권한이 수락되었을 경우 호출되는 메서드
            Intent i = new Intent(PermissionActivity.this, PermissionActivity.class);
            startActivity(i);
            finish();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            //한 가지라도 허용이 되지 않은 권한이 있다면 호출되는 메서드

            //모든 권한이 수락되지 않았다면 강제 종료
            Toast.makeText(PermissionActivity.this, "모든 권한을 수락해야 합니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    //권한 수락에 관한 여부를 묻는 메서드
    public void setPermission() {
        TedPermission.create().setPermissionListener(permissionListener).setDeniedMessage(
                "이 앱에서 요구하는 권한이 있습니다.\n[설정] > [권한]에서 해당 권한을 활성화해주세요."
        ).setPermissions(
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS
                ).check();
    }























}