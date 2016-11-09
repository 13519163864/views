package utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import dao.MyDatabaseHelper;
import entry.DBInfo;
import entry.Source;

/**
 * Created by Administrator on 2016/11/7.
 * 数据库工具类
 */

public class SqlUtils {
    //上下文参数
    Context mContext;
    //数据库辅助类
    MyDatabaseHelper sqlHelper;

    //构造方法
    public SqlUtils(Context mContext) {
        this.mContext = mContext;
        sqlHelper = new MyDatabaseHelper( mContext );
    }

    //插入方法
    public void inSert(String nid, String title, String summary, String stamp, String icon, String link, String type) {
        //获取 数据库
        SQLiteDatabase database = sqlHelper.getWritableDatabase();
//        String sql = "insert into newInfo values(nid,title,summary,icon,link,stamp,type)";
//
//        database.execSQL( sql );
        //ContentValues插入数据
        ContentValues values = new ContentValues();
        values.put( DBInfo._NID, nid );
        values.put( DBInfo._TITLE, title );
        values.put( DBInfo._SUMMARY, summary );
        values.put( DBInfo._STAMP, stamp );
        values.put( DBInfo._ICON, icon );
        values.put( DBInfo._LINK, link );
        values.put( DBInfo._TYPE, type );
        //插入
        database.insert( DBInfo.TABLE_NAME, null, values );
    }

    ArrayList<Source> mList = new ArrayList<>();

    //查询数据
    public ArrayList<Source> checkNews() {
        SQLiteDatabase database = sqlHelper.getReadableDatabase();
        Cursor cursor = database.query( DBInfo.TABLE_NAME, null, null, null, null, null, null );
        while (cursor.moveToNext()) {
            String nid = cursor.getString( cursor.getColumnIndex( DBInfo._NID ) );
            String title = cursor.getString( cursor.getColumnIndex( DBInfo._TITLE ) );
            String summary = cursor.getString( cursor.getColumnIndex( DBInfo._SUMMARY ) );
            String stamp = cursor.getString( cursor.getColumnIndex( DBInfo._STAMP ) );
            String icon = cursor.getString( cursor.getColumnIndex( DBInfo._ICON ) );
            String link = cursor.getString( cursor.getColumnIndex( DBInfo._LINK ) );
            String type = cursor.getString( cursor.getColumnIndex( DBInfo._TYPE ) );
            Log.e( "===", "数据库" + nid );
            mList.add( new Source( summary, stamp, title, icon, nid, link, type ) );

            //拿取数据
            //获取下标
//            int index = cursor.getColumnIndex( "_NID" );
//            //根据下标获取数据
//            int nid=cursor.getInt( index );
        }
        return mList;
    }


    public void check() {
        SQLiteDatabase database = sqlHelper.getReadableDatabase();
        database.query( DBInfo.TABLE_NAME, null, null, null, null, null, null );
    }

    public void delete(String nid) {
        SQLiteDatabase database = sqlHelper.getReadableDatabase();
        database.delete( DBInfo.TABLE_NAME, DBInfo._NID + " =?", new String[]{nid + ""} );
    }

}
