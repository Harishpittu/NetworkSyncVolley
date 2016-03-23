package com.sevencstudio.harish.networksyncframework.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.sevencstudio.harish.networksyncframework.ListViewAdapter;
import com.sevencstudio.harish.networksyncframework.R;
import com.sevencstudio.harish.networksyncframework.models.ListViewModel;

import java.util.ArrayList;

public class ModelViewBindingExample extends AppCompatActivity {
    ArrayList<ListViewModel> listViewModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_view_binding_example);


       AddData();

        ListView listView = (ListView) findViewById(R.id.listview);

        ListViewAdapter listAdapter = new ListViewAdapter(this, listViewModelArrayList);

        listView.setAdapter(listAdapter);
    }
    public void AddData()
    {
        listViewModelArrayList  = new ArrayList<>();
        for (int i = 0; i < 4; i++) {

            ListViewModel listViewModel = new ListViewModel();
            listViewModel.setImage("http://markcarson.com/images/Sunbird-4-200x200.png");
            listViewModel.setName("User Name " + i);
            listViewModel.setAge(20 + i);
            listViewModelArrayList.add(listViewModel);

        }
    }
}
