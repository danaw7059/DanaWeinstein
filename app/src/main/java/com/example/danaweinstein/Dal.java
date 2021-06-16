package com.example.danaweinstein;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Dal extends SQLiteAssetHelper {
    public Dal(Context context) {
        super(context, "my_db.db", null, 1);
    }


    public boolean checkForAccount(String username, String password) {
        String st = "SELECT * FROM accounts WHERE (username = '" + username + "' AND password = '" + password + "')";
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(st, null).getCount() == 1;
    }
    public Account getAccount(String username) {
        String st = "SELECT * FROM accounts WHERE username = ?";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, new String[]{username});
        Account a = new Account();
        cursor.moveToNext();
        a.setId(cursor.getInt(cursor.getColumnIndex("id")));
        a.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        a.setPassword(cursor.getString(cursor.getColumnIndex("password")));

        return a;
    }
    public boolean checkAccountType(int id)
    {
        String st = "SELECT * FROM manicurists WHERE (account_id =" + id+ " )";
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(st, null).getCount() == 1;
    }

    public Manicurist getManicuristByAccountId(int accountId) {
        String st = "SELECT * FROM manicurist WHERE account_id = " + accountId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        Manicurist m = new Manicurist();
        cursor.moveToNext();
        m.setId(cursor.getInt(cursor.getColumnIndex("id")));
        m.setFull_name(cursor.getString(cursor.getColumnIndex("full_name")));
        m.setAccount_id(cursor.getInt(cursor.getColumnIndex("account_id")));
        m.setDetails(cursor.getString(cursor.getColumnIndex("details")));

        return m;
    }

    public Client getClientByAccountId(int accountId) {
        String st = "SELECT * FROM client WHERE account_id = " + accountId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        Client c = new Client();
        cursor.moveToNext();
        c.setId(cursor.getInt(cursor.getColumnIndex("id")));
        c.setFull_name(cursor.getString(cursor.getColumnIndex("full_name")));
        c.setAccount_id(cursor.getInt(cursor.getColumnIndex("account_id")));
        c.setMani_id(cursor.getInt(cursor.getColumnIndex("mani_id")));

        return c;
    }

    public void addClient(String full_name,int account_id,int mani_id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO client (account_id, mani_id, full_name) values(?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindDouble(1, account_id);
        statement.bindDouble(2, mani_id);
        statement.bindString(3, full_name);
        statement.execute();
    }

    public void addManicurist(String full_name,int account_id,String details) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO manicurist (account_id, details, full_name) values(?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindDouble(1, account_id);
        statement.bindString(2, details);
        statement.bindString(3, full_name);
        statement.execute();
    }

    public void addAccount(String username,String password,String email) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO accounts (username, password, email) values(?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindString(1, username);
        statement.bindString(2, password);
        statement.bindString(3, email);
        statement.execute();
    }

    public void updateManicuristDetails(int id, String details) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_UPDATE = "UPDATE manicurist SET details = ? WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql_UPDATE);

        statement.bindString(1, details);
        statement.bindDouble(2, id );

        statement.execute();
    }

    public boolean checkForManicurist(int id) {
        String st = "SELECT * FROM manicurist WHERE (id = "+ id + ")";
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(st, null).getCount() == 1;
    }

    public void updateClientManiId(int id, int mani_id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_UPDATE = "UPDATE client SET mani_id = ? WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql_UPDATE);

        statement.bindDouble(1, mani_id);
        statement.bindDouble(2, id );

        statement.execute();
    }



}
