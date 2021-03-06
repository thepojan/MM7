package com.example.parmila.milkmanager.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.parmila.milkmanager.modules.Customer;
import com.example.parmila.milkmanager.modules.Preference;
import com.example.parmila.milkmanager.modules.Seller;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="MilkManager.db";

    //Customer Table
    private static final String C_TABLE_NAME="Customer";
    private static final String COLUMN_C_ID="c_id";
    private static final String COLUMN_C_FNAME="c_fname";
    private static final String COLUMN_C_LNAME="c_lname";
    private static final String COLUMN_C_PHONE="c_phone";
    private static final String COLUMN_C_PIN="c_pin";
    private static final String COLUMN_C_EMAIL="c_email";
    private static final String COLUMN_C_PASS="c_pass";
    private static final String COLUMN_C_ADDR="c_address";

    //Seller Table
    private static final String S_TABLE_NAME="Seller";
    private static final String COLUMN_S_ID="s_id";
    private static final String COLUMN_S_FNAME="s_fname";
    private static final String COLUMN_S_LNAME="s_lname";
    private static final String COLUMN_S_PHONE="s_phone";
    private static final String COLUMN_S_PIN="s_pin";
    private static final String COLUMN_S_EMAIL="s_email";
    private static final String COLUMN_S_PASS="s_pass";


    //Milk Table

    private static final String M_TABLE_NAME="Milk";
    private static final String COLUMN_M_TYPE="m_type";
    private static final String COLUMN_M_PRICE="m_price";

    //Preference table
    private static final String P_TABLE_NAME="Preference";
    private static final String COLUMN_P_ID="p_id";
    private static final String COLUMN_P_CID="p_c_id";
    private static final String COLUMN_P_TYPE="p_type";
    private static final String COLUMN_P_QUANTITY="p_quantity";



    // MILK TABLE Creation
    private static  final String M_TABLE_CREATE=" CREATE TABLE "+M_TABLE_NAME+"("+COLUMN_M_TYPE+" text primary key not null , "
            +COLUMN_M_PRICE+ " integer not null)";


    //Customer table creation
    private static final String C_TABLE_CREATE="CREATE TABLE "+C_TABLE_NAME+"("+COLUMN_C_ID+" text primary key not null ,"
            +COLUMN_C_FNAME+" text not null,"
            +COLUMN_C_LNAME+" text not null,"
            +COLUMN_C_PHONE+" text not null,"
            +COLUMN_C_ADDR+" text not null,"
            +COLUMN_C_PIN+" text not null,"
            +COLUMN_C_EMAIL+" text not null,"
            +COLUMN_C_PASS+" text not null)";

    //Seller table creation
    private static final String S_TABLE_CREATE =" CREATE TABLE "+S_TABLE_NAME+"("+COLUMN_S_ID+" text primary key not null ,"
            +COLUMN_S_FNAME+" text not null,"
            +COLUMN_S_LNAME+" text not null,"
            +COLUMN_S_PHONE+" text not null,"
            +COLUMN_S_PIN+" text not null,"
            +COLUMN_S_EMAIL+" text not null,"
            +COLUMN_S_PASS+" text not null)";

    //Preference table create
    private static final String P_TABLE_CREATE =" CREATE TABLE "+P_TABLE_NAME+"("+COLUMN_P_ID+" text primary key not null ,"
            +COLUMN_C_ID+" text not null "
            +"REFERENCES "+C_TABLE_NAME+"("+COLUMN_C_ID+"),"
            +COLUMN_P_TYPE+" text not null,"
            +COLUMN_P_QUANTITY+" text not null)";




    //Customer table drop
    private static final String C_TABLE_DROP="DROP TABLE IF EXISTS " +C_TABLE_NAME;

    //Seller table drop
    private static final String S_TABLE_DROP="DROP TABLE IF EXISTS "+S_TABLE_NAME;

    //Milk table drop
    private static final String M_TABLE_DROP="DROP TABLE IF EXISTS "+M_TABLE_NAME;

    //Preference table drop
    private static final String P_TABLE_DROP="DROP TABLE IF EXISTS "+P_TABLE_NAME;



    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(C_TABLE_CREATE);
        db.execSQL(S_TABLE_CREATE);
        db.execSQL(M_TABLE_CREATE);
        db.execSQL(P_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(C_TABLE_DROP);
        db.execSQL(S_TABLE_DROP);
        db.execSQL(M_TABLE_DROP);
        db.execSQL(P_TABLE_DROP);
        onCreate(db);

    }


    public void insertMilk()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues val=new ContentValues();
        val.put(COLUMN_M_TYPE,"Cow");
        val.put(COLUMN_M_PRICE,40);
        db.insert(M_TABLE_NAME,null,val);
        val.put(COLUMN_M_TYPE,"Buffalo");
        val.put(COLUMN_M_PRICE,45);
        db.insert(M_TABLE_NAME,null,val);
        db.close();

    }

    // Customer Record Insertion
    public void insertCustomer(Customer c)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        //generating unique customer id
        String query="Select * FROM "+C_TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);
        String cust_id="C-"+Integer.toString(cursor.getCount());
        cursor.close();

        //inserting a record
        values.put(COLUMN_C_ID,cust_id);
        values.put(COLUMN_C_FNAME, c.getC_fname());
        values.put(COLUMN_C_LNAME, c.getC_lname());
        values.put(COLUMN_C_PHONE,c.getC_phone());
        values.put(COLUMN_C_ADDR,c.getC_address());
        values.put(COLUMN_C_PIN,c.getC_pin());
        values.put(COLUMN_C_EMAIL,c.getC_email());
        values.put(COLUMN_C_PASS,c.getC_pass());

        db.insert(C_TABLE_NAME,null,values);
        db.close();
    }
    
    //Seller Record Insertion
    public void insertSeller(Seller s)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        //generating unique customer id
        String query="Select * FROM "+S_TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);
        String seller_id="S-"+Integer.toString(cursor.getCount());
        cursor.close();

        //inserting a record
        values.put(COLUMN_S_ID,seller_id);
        values.put(COLUMN_S_FNAME, s.getS_fname());
        values.put(COLUMN_S_LNAME, s.getS_lname());
        values.put(COLUMN_S_PHONE,s.getS_phone());
        values.put(COLUMN_S_PIN,s.getS_pin());
        values.put(COLUMN_S_EMAIL,s.getS_email());
        values.put(COLUMN_S_PASS,s.getS_pass());

        db.insert(S_TABLE_NAME,null,values);
        db.close();
    }

    //insert preference record
    public void insertPreference(Preference p)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        //generating unique customer id
        String query="Select * FROM "+P_TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);
        String p_id="P-"+Integer.toString(cursor.getCount());
        cursor.close();

        //inserting a record
        values.put(COLUMN_P_ID,p_id);
        values.put(COLUMN_P_CID, p.getPc_id());
        values.put(COLUMN_P_TYPE, p.getP_Type());
        values.put(COLUMN_P_QUANTITY,p.getP_Quantity());
        db.insert(P_TABLE_NAME,null,values);
        db.close();
    }

    
    
    public List<Customer> getAllCust()
    {
        String[] columns= {
                COLUMN_C_ID,
                COLUMN_C_FNAME,
                COLUMN_C_LNAME,
                COLUMN_C_PHONE,
                COLUMN_C_ADDR,
                COLUMN_C_PIN,
                COLUMN_C_EMAIL,
                COLUMN_C_PASS};
        String sortOrder=COLUMN_C_ID+" ASC";
        List<Customer> custList= new ArrayList<>();
        
        SQLiteDatabase db=this.getReadableDatabase();
        
        Cursor cursor=db.query(C_TABLE_NAME,columns,null,null,null,null,sortOrder);
        
        if(cursor.moveToFirst())
        {
            do{
                Customer c=new Customer();
                c.setC_id(cursor.getString(cursor.getColumnIndex(COLUMN_C_ID)));
                c.setC_fname(cursor.getString(cursor.getColumnIndex(COLUMN_C_FNAME)));
                c.setC_lname(cursor.getString(cursor.getColumnIndex(COLUMN_C_LNAME)));
                c.setC_phone(cursor.getString(cursor.getColumnIndex(COLUMN_C_PHONE)));
                c.setC_address(cursor.getString(cursor.getColumnIndex(COLUMN_C_ADDR)));
                c.setC_pin(cursor.getString(cursor.getColumnIndex(COLUMN_C_PIN)));
                c.setC_email(cursor.getString(cursor.getColumnIndex(COLUMN_C_EMAIL)));
                c.setC_pass(cursor.getString(cursor.getColumnIndex(COLUMN_C_PASS)));

                custList.add(c);
            }while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return custList;
    }

    //get all seller
    public List<Seller> getAllSeller()
    {
        String[] columns= {
                COLUMN_S_ID,
                COLUMN_S_FNAME,
                COLUMN_S_LNAME,
                COLUMN_S_PHONE,
                COLUMN_S_PIN,
                COLUMN_S_EMAIL,
                COLUMN_S_PASS};
        String sortOrder=COLUMN_S_ID+" ASC";
        List<Seller> sellerList= new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(S_TABLE_NAME,columns,null,null,null,null,sortOrder);

        if(cursor.moveToFirst())
        {
            do{
                Seller s=new Seller();
                s.setS_id(cursor.getString(cursor.getColumnIndex(COLUMN_S_ID)));
                s.setS_fname(cursor.getString(cursor.getColumnIndex(COLUMN_S_FNAME)));
                s.setS_lname(cursor.getString(cursor.getColumnIndex(COLUMN_S_LNAME)));
                s.setS_phone(cursor.getString(cursor.getColumnIndex(COLUMN_S_PHONE)));
                s.setS_pin(cursor.getString(cursor.getColumnIndex(COLUMN_S_PIN)));
                s.setS_email(cursor.getString(cursor.getColumnIndex(COLUMN_S_EMAIL)));
                s.setS_pass(cursor.getString(cursor.getColumnIndex(COLUMN_S_PASS)));

                sellerList.add(s);
            }while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return sellerList;
    }


    public void updateCust(Customer c)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_C_FNAME, c.getC_fname());
        values.put(COLUMN_C_LNAME, c.getC_lname());
        values.put(COLUMN_C_PHONE,c.getC_phone());
        values.put(COLUMN_C_ADDR,c.getC_address());
        values.put(COLUMN_C_PIN,c.getC_pin());
        values.put(COLUMN_C_EMAIL,c.getC_email());
        values.put(COLUMN_C_PASS,c.getC_pass());

        db.update(C_TABLE_NAME,values,COLUMN_C_ID +" = ?",
                new String[]{String.valueOf(c.getC_id())});
        db.close();
    }

    public void deleteUser(Customer c)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        db.delete(C_TABLE_NAME,COLUMN_C_ID +" = ?",
                new String[]{String.valueOf(c.getC_id())});
        db.close();
    }


    // To check if the customer already exists or not
    public boolean checkCust(String email)
    {

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT "+COLUMN_C_EMAIL+","+
                COLUMN_C_PASS+" FROM "+C_TABLE_NAME+" WHERE "+COLUMN_C_EMAIL+" = '"+email+"'",null);
        int cursorCount=cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount==0)
        {
            return true;
        }
        return false;
    }

    // To check if the Seller login details are correct or not
    public boolean checkCust(String email, String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT "+COLUMN_C_EMAIL+","+
                COLUMN_C_PASS+" FROM "+C_TABLE_NAME+" WHERE "+COLUMN_C_EMAIL+" = '"+email+"'"+
                " AND "+COLUMN_C_PASS+"='"+password+"'",null);
        int cursorCount=cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount==1)
        {
            return true;
        }
        return false;
    }

    // To check if the Seller already exists or not
    public boolean checkSeller(String email)
    {

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT "+COLUMN_S_EMAIL+" FROM "+S_TABLE_NAME+" WHERE "+COLUMN_S_EMAIL+" = '"+email+"'",null);
        int cursorCount=cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount==0)
        {
            return true;
        }
        return false;
    }

    // To check if the Seller login details are correct or not
    public boolean checkSeller(String email, String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT "+COLUMN_S_EMAIL+","+
                COLUMN_S_PASS+" FROM "+S_TABLE_NAME+" WHERE "+COLUMN_S_EMAIL+" = '"+email+"'"+
                " AND "+COLUMN_S_PASS+"='"+password+"'",null);
        int cursorCount=cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount==1)
        {
            return true;
        }
        return false;
    }
}


