package vn.edu.poly.assignment1nc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {
    public static final String DB_NAME = "QuanLySinhVien";
    public static final String DB_LOP = "Lop";
    public static final String DB_SV = "SinhVien";
    public static final int DB_VERSION = 1;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        OpenHelper helper = new OpenHelper(context);
        database = helper.getWritableDatabase();
    }

    public void insertLop(String MaLop, String TenLop) {

        ContentValues values = new ContentValues();
        values.put("MaLop", MaLop);
        values.put("TenLop", TenLop);
        database.insert(DB_LOP, null, values);
    }

    public void insertSinhVien(String Ten, String DiaChi, String Lop) {
        ContentValues values = new ContentValues();
        values.put("Ten", Ten);
        values.put("Diachi", DiaChi);
        values.put("Tenlop", Lop);
        database.insert(DB_SV, null, values);
    }


    public void UpdateLop(String MaLop, String TenLop, int id) {
        ContentValues values = new ContentValues();
        values.put("MaLop", MaLop);
        values.put("TenLop", TenLop);
        database.update(DB_LOP, values, "_id = " + id, null);
    }

    public void UpdateSinhVien(String Ten, String DiaChi, String Lop, String id) {
        ContentValues values = new ContentValues();
        values.put("Ten", Ten);
        values.put("Diachi", DiaChi);
        values.put("Tenlop", Lop);
        database.update(DB_SV, values,
                "_id = " + id,
                null);
    }

    public void DeleteLop(int id) {
        database.delete(DB_LOP,
                "_id = " + id,
                null);
    }

    public void DeleteSinhVien(int id) {
        database.delete(DB_SV,
                "_id = " + id,
                null);
    }


    public Cursor getLop() {
        return database.query(DB_LOP,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public Cursor getSinhVien() {
        return database.query(DB_SV,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String BangLop = "CREATE TABLE IF NOT EXISTS Lop(_id INTEGER PRIMARY KEY AUTOINCREMENT, MaLop TEXT, TenLop TEXT)";
            String BangSinhVien = "CREATE TABLE IF NOT EXISTS SinhVien(_id INTEGER PRIMARY KEY AUTOINCREMENT, Ten Text, Diachi TEXT, Tenlop TEXT)";

            db.execSQL(BangLop);
            db.execSQL(BangSinhVien);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Lop");
            db.execSQL("DROP TABLE IF EXISTS SinhVien");
            onCreate(db);
        }
    }
}
