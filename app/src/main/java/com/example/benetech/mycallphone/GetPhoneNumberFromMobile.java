package com.example.benetech.mycallphone;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class GetPhoneNumberFromMobile {

    private List<PhoneInfo> list;
    private List<String> listName;

    public List<PhoneInfo> getPhoneNumberFromMobile(Context context) {
        // TODO Auto-generated constructor stub
        list = new ArrayList<PhoneInfo>();
        listName=new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{"display_name", "sort_key", "contact_id",
                        "data1"}, null, null, null);
//        moveToNext方法返回的是一个boolean类型的数据
        while (cursor.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            int Id = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            String Sortkey = getSortkey(cursor.getString(1));
          //  Log.e(TAG, "getPhoneNumberFromMobile-------: "+name );
            if(!listName.contains(name)){
                listName.add(name);
                PhoneInfo phoneInfo = new PhoneInfo(name, number, Sortkey, Id);
                list.add(phoneInfo);
            }

        }
        cursor.close();
        return list;
    }

    private static String getSortkey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        } else
            return "#";
    }
   
}
