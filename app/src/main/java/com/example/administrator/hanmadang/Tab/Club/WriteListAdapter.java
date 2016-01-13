package com.example.administrator.hanmadang.Tab.Club;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by park on 2016-01-11.
 */
public class WriteListAdapter extends BaseAdapter {
    private Context mContext;
    private List<WriteItem> mItems = new ArrayList<WriteItem>();
    public WriteListAdapter(Context context) {
        mContext = context;
    }

    public void addItem(WriteItem item) {
        mItems.add(item);
    }

    public void setListItems(List<WriteItem> listItem) {
        mItems = listItem;
    }

    public int getCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        WriteView writeView;
        if (convertView == null) {
            writeView = new WriteView(mContext, mItems.get(position));
        } else {
            writeView = (WriteView) convertView;

           // writeView.setIcon(mItems.get(position).getIcon());
            /**
            if(position == 0) {
                writeView.setTitle;
            }
            */
            writeView.setText(0, mItems.get(position).getData(0));
            writeView.setText(1, mItems.get(position).getData(1));
            writeView.setText(2, mItems.get(position).getData(2));
            }

        return writeView;
    }

    /**
    public boolean areAllItemsSelectable() {
        return false;
    }

    public boolean isSelectable(int position) {
        try {
            return mItems.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }
    */
}
