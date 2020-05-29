package com.example.psdnote.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.psdnote.R;
import com.example.psdnote.db.dbutils.ItemUtils;
import com.example.psdnote.db.entity.Item;

public class AddItemActivity extends AppCompatActivity {

    private EditText title;
    private EditText account;
    private EditText password;
    private ItemUtils itemUtils;
    private Toast toast = null;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        initView();
    }

    private void initView(){
        title = (EditText) findViewById(R.id.add_edit_title);
        account = (EditText) findViewById(R.id.add_edit_account);
        password = (EditText) findViewById(R.id.add_edit_password);
        itemUtils = new ItemUtils(this);
    }

    public void addItem(View view){
        Item item = new Item(title.getText().toString(),account.getText().toString(),password.getText().toString());
        itemUtils.insertOrReplace(item);
        showToast("添加成功");
        intent =new Intent(AddItemActivity.this,NoteActivity.class);
        startActivity(intent);
        finish();
    }

    public void back(View view){
        itemUtils.close();
        intent =new Intent(AddItemActivity.this,NoteActivity.class);
        startActivity(intent);
        finish();
    }

    private void showToast(String s) {
        if (toast == null) {
            toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.setText(s);
            toast.show();
        }
    }
}
