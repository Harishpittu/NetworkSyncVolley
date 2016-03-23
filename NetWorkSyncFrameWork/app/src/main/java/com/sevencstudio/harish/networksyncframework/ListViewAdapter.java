package com.sevencstudio.harish.networksyncframework;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sevencstudio.harish.networksyncframework.models.ListViewModel;
import com.sevencstudio.harish.networksyncvolley.ModelViewBinding;

import java.util.ArrayList;

/**
 * Created by harish on 19/03/16.
 */
public class ListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<ListViewModel> listViewModelArrayList;

    public ListViewAdapter(Context context, ArrayList<ListViewModel> listViewModelArrayList) {
        this.context = context;
        this.listViewModelArrayList = listViewModelArrayList;
    }

    @Override
    public int getCount() {
        return listViewModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        return ModelViewBinding.Bind(context, R.layout.custom_item, listViewModelArrayList.get(i));
    }
}
