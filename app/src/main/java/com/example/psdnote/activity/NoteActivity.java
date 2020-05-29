package com.example.psdnote.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.psdnote.R;
import com.example.psdnote.db.dbutils.ItemUtils;
import com.example.psdnote.db.entity.Item;
import com.example.psdnote.util.RecyclerView.ItemAdapter;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    private List<Item> itemList;
    private RecyclerView recyclerView;
    private ItemUtils itemUtils;
    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initView();
    }

    private void initView(){
        itemUtils = new ItemUtils(this);
        itemList = itemUtils.listAll();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }

    public void goToAdd(View view){
        intent =new Intent(NoteActivity.this,AddItemActivity.class);
        startActivity(intent);
        itemUtils.close();
        finish();
    }

    public void clean(View view){
        itemUtils.deleteItem();
        itemList = itemUtils.listAll();
        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);
        itemUtils.close();
    }
}
