package com.example.benetech.mycallphone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lv;
    private MyPhoneAdapter adapter;
    private GetPhoneNumberFromMobile getPhoneNumberFromMobile;
    private List<PhoneInfo> list = new ArrayList<PhoneInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPhoneNumberFromMobile = new GetPhoneNumberFromMobile();
        lv=findViewById(R.id.contents_view);
        addPermissin1();
        list = getPhoneNumberFromMobile.getPhoneNumberFromMobile(this);
        for(PhoneInfo i:list){
            Log.e("TAG", "onCreate:----- "+i.getName() );
        }
        adapter = new MyPhoneAdapter(list, this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        addPermissin1();
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS },1);
        String number = list.get(position).getNumber();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {   //清除缓存
        list.clear();
        super.onBackPressed();
        return;
    }

    private void addPermissin1() {
        final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//如果 API level 是大于等于 23(Android 6.0) 时
            //判断是否具有权限
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                //判断是否需要向用户解释为什么需要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CALL_PHONE)) {
                    //Toast.makeText(this, "自Android 6.0开始需要打开位置权限才可以搜索到Ble设备", Toast.LENGTH_SHORT).show();
                }
                //请求权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_CODE_ACCESS_COARSE_LOCATION);
            }

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)
                    !=PackageManager.PERMISSION_GRANTED){

                if(ActivityCompat.shouldShowRequestPermissionRationale(
                        this,Manifest.permission.READ_CONTACTS )){}

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS },1);
            }
        }
    }
}
