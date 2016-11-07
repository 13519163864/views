package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import entry.DBInfo;

/**
 * Created by Administrator on 2016/11/4.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public MyDatabaseHelper(Context context) {
        super( context, DBInfo.DB_NAME, null, DBInfo.DB_VERSION );
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建表

        String sql = "create table %1$s( %2$s Integer, %3$s String, %4$s,String, %5$s String, %6$s String, %7$s String, %8$s Integer )";
        String format = String.format( sql, DBInfo.TABLE_NAME, DBInfo._NID, DBInfo._TITLE, DBInfo._SUMMARY, DBInfo._ICON, DBInfo._LINK, DBInfo._STAMP, DBInfo._TYPE );
        //执行sql语句创建一张表
        sqLiteDatabase.execSQL( format );

    }

    //版本更新
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
