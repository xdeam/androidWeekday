package me.xdream.fsql.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.xdream.fsql.utils.DBHelper;
import me.xdream.fsql.vo.Person;

/**
 * Created by kwinter on 2017/11/1.
 */

public class DataBaseServices   {

    // 获取数据库的连接
    private DBHelper helper;

    // 通过构造方法 完成数据库的链接操作
    public DataBaseServices(Context context) { // 该参数由Activity传入
        helper = new DBHelper(context);
    }


    //使用ContentValue进行数据库操作的重写
    //编写save方法
    public void save(Person p){
        //通过数据库的链接,获取数据库的操作对象
        SQLiteDatabase db = helper.getWritableDatabase();//写入方式的创建

        //创建ContentValue对象
        ContentValues values=new ContentValues();
        //将插入的参数放置在values对象中
        values.put("pid", p.getId());
        values.put("pname",p.getName());
        values.put("age", p.getAge());

        //调用db操作对象插入contentValues对象
        db.insert("person", null, values);

    }

    //修改操作

    public void update(Person p){
        //update person set name=? where pid=?
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("pname", p.getName());
        //注意执行语句的书写
        db.update("person", values, "pid=?", new String[]{""+p.getId()});

    }
    //删除
    public void delete(Integer id){
        // delete from person where pid=?
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("person", "pid=?", new String[]{id.toString()});

    }





    //查询单个数据对象
    public Person findById(Integer id){
        SQLiteDatabase db = helper.getReadableDatabase();
        //面向对象的查询
        Cursor cursor=db.query("person", new String[]{"pid","pname","age"},
                "pid=?",new String[]{id.toString()}, null, null	, null);
        //db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)

        if (cursor.moveToFirst()) { // 只能出现一个记录
            int pid = cursor.getInt(cursor.getColumnIndex("pid"));
            String name = cursor.getString(cursor.getColumnIndex("pname"));
            int amount = cursor.getInt(cursor.getColumnIndex("age"));
            return new Person(pid, name, amount);
        }
        return null;


    }

    //查询分页
    public List<Person> findAll(Integer offset, Integer maxResult) { // 分页的技巧
        List<Person> ps = new ArrayList<Person>();
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query("person", null, null, null, null, null, null, offset+" , "+maxResult);
        while (cursor.moveToNext()) {
            int pid = cursor.getInt(cursor.getColumnIndex("pid"));
            String name = cursor.getString(cursor.getColumnIndex("pname"));
            int amount = cursor.getInt(cursor.getColumnIndex("age"));
            ps.add( new Person(pid, name, amount));
        }
        return ps;
    }

    //聚合函数的使用
    public long getCount(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("person", new String[]{"count(*)"}, null, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getLong(0);


    }


}
