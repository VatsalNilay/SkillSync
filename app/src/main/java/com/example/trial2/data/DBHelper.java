package com.example.trial2.data;

import static com.example.trial2.params.Params.DB_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.trial2.AlumniModel;
import com.example.trial2.params.Params;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    private static String  DB_NAME = "MyDB.db";
    private static String DB_PATH = "";
    private Context mContext;
    private SQLiteDatabase myDataBase;
    private  Context myContext;
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, Params.DB_VERSION);
        this.myContext = context;
//        assert myContext != null;
//        Log.d("jajaj","sds");
        DB_PATH = myContext.getDatabasePath(DB_NAME).toString();
//        Log.d("gaag",DB_PATH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/"+Params.DB_NAME;
        }
        else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/"+ Params.DB_NAME;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + " ( " +
                Params.KEY_ID + " INTEGER PRIMARY KEY, " +
                Params.KEY_NAME + " TEXT, " +
                Params.KEY_EMAIL + " TEXT, " +
                Params.KEY_PASSWORD + " TEXT, " +
                Params.KEY_DOB + " TEXT )";
        try {
            Log.d("Creating user table...", "Query being run is: " + create);
            db.execSQL(create);
        } catch (SQLException e) {
            Log.e("ErrorUserTable", "Error creating user table: " + e.getMessage());
        }
    };

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            // do nothing - database already exisy
        } else {

            this.getWritableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e) {
            Log.e("message", "" + e);
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }


    private void copyDataBase() throws IOException
    {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException
    {
        // Open the database
        String myPath = DB_PATH;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean doesUserExist(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {Params.KEY_EMAIL};
        String selection = Params.KEY_EMAIL + "=?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(
                Params.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        return userExists;
    }
    public void insertUser(String name,  String email, String password, String dob, Context context) {
        if (doesUserExist(email)){
            Toast.makeText(context, "Email already Exists", Toast.LENGTH_SHORT).show() ;
            return;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Params.KEY_NAME, name);
        values.put(Params.KEY_PASSWORD, password);
        values.put(Params.KEY_EMAIL, email);
        values.put(Params.KEY_DOB, dob);

        // Inserting Row
        db.insert(Params.TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
    public boolean loginUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = Params.KEY_EMAIL + " = ? AND " + Params.KEY_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(
                Params.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        return userExists;
    }

    @SuppressLint("Range")
    public String getUserName(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT name FROM users WHERE email = ?", new String[]{email});

        String user = null;

        if (cursor.moveToFirst()) {
            user = cursor.getString(cursor.getColumnIndex("name"));
        }

        cursor.close();
        MyDB.close();
        return user;
    }

//    public ArrayList<CourseModel> getCourses()
//    {
//        ArrayList<CourseModel> courseList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + Params.TABLE_COURSE, null);
//
//        if (cursor.moveToFirst()) {
//            do {
////                int courseId = cursor.getInt(cursor.getColumnIndex("course_id"));
//                String courseName = cursor.getString(1);
//                CourseModel course = new CourseModel(courseName);
//                courseList.add(course);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        Log.d("kitna bada","ss" + courseList.size());
//        return courseList;
//    }


    public ArrayList<AlumniModel> getAlumni()
    {
        ArrayList<AlumniModel> alumniModel = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("sab gg hai",""+alumniModel.size());
        Cursor cursor = db.rawQuery("SELECT * FROM alumni", null);
        Log.d("sab ss hai",""+alumniModel.size());

        if (cursor.moveToFirst()) {
            do {
//                int courseId = cursor.getInt(cursor.getColumnIndex("course_id"));
                String alumniName = cursor.getString(1);
                int alumniId = cursor.getInt(0);
                String alumniEmail = cursor.getString(2);
                String alumniContact = cursor.getString(3);
                String alumniSkill = cursor.getString(4);
                String alumniDescription = cursor.getString(5);
                String alumniGender = cursor.getString(6);
                AlumniModel course = new AlumniModel(alumniId,alumniName,alumniEmail,alumniContact,alumniSkill,alumniDescription,alumniGender);
                alumniModel.add(course);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return alumniModel;
    }
}
