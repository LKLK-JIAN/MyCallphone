package com.example.benetech.mycallphone;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import static android.content.ContentValues.TAG;

public class MyPhoneAdapter extends BaseAdapter {
    private List<PhoneInfo> list;
    private Context context;

    public MyPhoneAdapter(List<PhoneInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder;
        if (convertView == null) {
            Log.e(TAG, "getView: ======" + position);
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.myadapteritem, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_adapteritem_name);
            viewHolder.number = (TextView) convertView.findViewById(R.id.tv_adapteritem_number);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.number.setText(list.get(position).getNumber());
        return convertView;
    }

    public class ViewHolder {
        TextView name;
        TextView number;
    }

}
