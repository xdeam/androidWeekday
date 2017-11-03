package me.xdream.fsql.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kwinter on 2017/11/1.
 */

public class DBHelper  extends SQLiteOpenHelper {
//创建一个Android的服务类,在该类中定义数据库以及数据表的创建方法
    /**
     * SQLiteOpenHelper :是系统提供的数据库维护操作类,此类中包含两个方法
     * 一个方法负责数据库表的创建,另一个负责数据表的更新
     */
    private static final String DataBaseName="android_test1.db";	//定义一个 数据库名字
    private static final int DataBaseVersion=1;//数据库的更新的版本号,默认为1

    public DBHelper(Context context) {

        // 构造方法中用于创建数据库
        super(context, DataBaseName, null, DataBaseVersion);
		/*
		 * context:当前的Android程序运行的环境
		 * DB_name: 当前需要创建的数据库名称
		 * CursorFactory:游标工厂,主要是用于获取select的执行结果,但这种方式很少使用
		 * version:当前的数据库的版本号
		 * */

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 做数据表的创建
        String sql=" create table person( " +
                "	pid int primary key  , " +
                "	pname varchar(20), " +
                "	age int )" ;
        db.execSQL(sql); //执行当前的sql语句

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据表的更新

    }



}
