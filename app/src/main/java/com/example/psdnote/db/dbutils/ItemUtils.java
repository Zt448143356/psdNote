package com.example.psdnote.db.dbutils;

import android.content.Context;

import com.example.psdnote.db.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    private DaoManager manager;

    public ItemUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }

    /**
     * 完成对数据库中item 表的插入操作
     * @param item
     * @return
     */
    public boolean insertOrReplace(Item item) {
        boolean flag = false;
        flag = manager.getDaoSession().insertOrReplace(item) != -1;
        return flag;
    }

    /**
     * 实现删除
     * @return
     */
    public boolean deleteItem(){
        boolean flag =false;
        try{
            //删除所有的记录
            manager.getDaoSession().deleteAll(Item.class);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 返回多条记录
     * @return
     */
    public ArrayList<Item> listAll(){
        List<Item> dataList= manager.getDaoSession().loadAll(Item.class);
        ArrayList<Item> dataArrayList = new ArrayList<>();
        for(Item item:dataList){
            dataArrayList.add(item);
        }
        return dataArrayList;
    }

    public void close(){
        manager.closeConnection();
    }
}
