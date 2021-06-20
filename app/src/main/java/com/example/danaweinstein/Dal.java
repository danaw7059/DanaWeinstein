package com.example.danaweinstein;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.telecom.Call;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.sql.Blob;
import java.util.ArrayList;

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

    public boolean checkAccountType(int id) {
        String st = "SELECT * FROM manicurist WHERE (account_id =" + id + " )";
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
        String st = "SELECT * FROM client WHERE id_account = " + accountId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        Client c = new Client();
        cursor.moveToNext();
        c.setId(cursor.getInt(cursor.getColumnIndex("id")));
        c.setFull_name(cursor.getString(cursor.getColumnIndex("full_name")));
        c.setAccount_id(cursor.getInt(cursor.getColumnIndex("id_account")));
        c.setMani_id(cursor.getInt(cursor.getColumnIndex("id_mani")));

        return c;
    }

    public Client getClientByClientId(int client_id) {
        String st = "SELECT * FROM client WHERE id = " + client_id;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        Client c = new Client();
        cursor.moveToNext();
        c.setId(cursor.getInt(cursor.getColumnIndex("id")));
        c.setFull_name(cursor.getString(cursor.getColumnIndex("full_name")));
        c.setAccount_id(cursor.getInt(cursor.getColumnIndex("id_account")));
        c.setMani_id(cursor.getInt(cursor.getColumnIndex("id_mani")));

        return c;
    }

    public void addClient(String full_name, int account_id, int mani_id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO client (id_account, id_mani, full_name) values(?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindDouble(1, account_id);
        statement.bindDouble(2, mani_id);
        statement.bindString(3, full_name);
        statement.execute();
    }

    public void addManicurist(String full_name, int account_id, String details) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO manicurist (id_account, details, full_name) values(?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindDouble(1, account_id);
        statement.bindString(2, details);
        statement.bindString(3, full_name);
        statement.execute();
    }

    public void addAccount(String username, String password, String email) {
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
        statement.bindDouble(2, id);

        statement.execute();
    }

    public boolean checkForManicurist(int id) {
        String st = "SELECT * FROM manicurist WHERE (id = " + id + ")";
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(st, null).getCount() != 0;
    }

    public void updateClientManiId(int id, int mani_id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_UPDATE = "UPDATE client SET id_mani = ? WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql_UPDATE);

        statement.bindDouble(1, mani_id);
        statement.bindDouble(2, id);

        statement.execute();
    }

    public String getManiDetail(int maniId) {
        String st = "SELECT details FROM manicurist WHERE id = " + maniId;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        cursor.moveToNext();
        String details = cursor.getString(cursor.getColumnIndex("details"));
        return details;
    }

    public void addNewPolishNail(Design d) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO designs (id_mani, details, image, company_name, polish_nail_code) values(?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindDouble(1, d.getId_mani());
        statement.bindString(2, d.getDetails());
        statement.bindBlob(3, d.getImage());
        statement.bindString(4, d.getCompany_name());
        statement.bindString(5, d.getPolish_nail_code());
        statement.execute();
    }

    public ArrayList<Design> findNewPolishNail(int mani_id, String details) {
        /*0 - Light
          1 - Dark
          2 - Winter
          3 - Summer
          4 - Sparkle
          5 - Fancy
        */
        String[] d = new String[]{"'%Light%'", "'%Dark%'", "'%Winter%'", "'%Summer%'", "'%Sparkle%'", "'%Fancy%'"};
        Design[][] designs = new Design[6][];
        int counter = 0;
        for (int i = 0; i < 6; i++) {
            designs[i] = helperFindNewPolishNail(d[i], mani_id);
            counter += designs[i].length;
        }
        ArrayList<Design> arrDesigns = new ArrayList<>();
        Design[] connected = new Design[counter];
        counter = 0;
        if (details.contains("Light")) {
            System.arraycopy(designs[0], 0, connected, 0, designs[0].length);
            counter += designs[0].length;
        }
        if (details.contains("Dark")) {
            System.arraycopy(designs[1], 0, connected, counter, designs[1].length);
            counter += designs[1].length;
        }
        if (details.contains("Winter")) {
            System.arraycopy(designs[2], 0, connected, counter, designs[2].length);
            counter += designs[2].length;
        }
        if (details.contains("Summer")) {
            System.arraycopy(designs[3], 0, connected, counter, designs[3].length);
            counter += designs[3].length;
        }
        if (details.contains("Sparkle")) {
            System.arraycopy(designs[4], 0, connected, counter, designs[4].length);
            counter += designs[4].length;
        }
        if (details.contains("Fancy")) {
            System.arraycopy(designs[5], 0, connected, counter, designs[5].length);
            counter += designs[5].length;
        }
        counter = 1;

        for (int i = 0; i < details.length(); i++) {
            if (details.charAt(i) == ',')
                counter++;
        }

        while (arrDesigns.isEmpty()) {
            for (int i = 0; i < connected.length; i++) {
                int count = 0;
                for (int j = 0; j < connected.length; j++) {
                    {
                        if (connected[i] == connected[j]) {
                            count++;
                        }

                    }
                    if (count >= counter) {
                        arrDesigns.add(connected[i]);
                    }
                }
            }
            counter--;
        }
        return arrDesigns;
    }

    private Design[] helperFindNewPolishNail(String st, int mani_id) {
        String sqlst = "SELECT * FROM designs WHERE id_mani = " + mani_id + " AND details LIKE " + st;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlst, null);
        Design[] designs = new Design[cursor.getCount()];
        int i = 0;
        while(cursor.moveToNext())
        {
            designs[i] = new Design();
            designs[i].setId(cursor.getInt(cursor.getColumnIndex("id")));
            designs[i].setId_mani(cursor.getInt(cursor.getColumnIndex("id_mani")));
            designs[i].setDetails(cursor.getString(cursor.getColumnIndex("details")));
            designs[i].setImage(cursor.getBlob(cursor.getColumnIndex("image")));
            designs[i].setCompany_name(cursor.getString(cursor.getColumnIndex("company_name")));
            designs[i].setPolish_nail_code(cursor.getString(cursor.getColumnIndex("polish_nail_code")));
            i++;
        }
        /*
        for(int i=0;i<designs.length;i++)
        {
            cursor.moveToNext();
            designs[i] = new Design();
            designs[i].setId(cursor.getInt(cursor.getColumnIndex("id")));
            designs[i].setId_mani(cursor.getInt(cursor.getColumnIndex("id_mani")));
            designs[i].setDetails(cursor.getString(cursor.getColumnIndex("details")));
            designs[i].setImage(cursor.getBlob(cursor.getColumnIndex("image")));
            designs[i].setCompany_name(cursor.getString(cursor.getColumnIndex("company_name")));
            designs[i].setPolish_nail_code(cursor.getString(cursor.getColumnIndex("polish_nail_code")));

        }
        */
        return designs;
    }

    public boolean checkMeetings(int mani_id, String date) {
        String st = "SELECT * FROM appointments WHERE id_mani = " + mani_id + " AND date = '" + date + "'";
        SQLiteDatabase db = getWritableDatabase();
        if (db.rawQuery(st, null).getCount() != 0)
            return true;
        return false;
    }

    public void removeMeetings(int mani_id, String date) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_DELETE = "DELETE FROM appointments WHERE id_mani = " + mani_id + " AND id_client = " + 0 + " AND date LIKE '%" + date + "%'";
        SQLiteStatement statement = db.compileStatement(sql_DELETE);

        statement.execute();
    }

    public void addMeeting(int mani_id, int client_id, String date) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO appointments (id_mani, id_client, date) values(?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindDouble(1, mani_id);
        statement.bindDouble(2, client_id);
        statement.bindString(3, date);
        statement.execute();
    }

    public ArrayList<Meeting> getManicuristEmptyMeetingsByDay(int mani_id, String date) {
        ArrayList<Meeting> ary = new ArrayList<>();
        String st = "SELECT * FROM appointments WHERE id_mani = " + mani_id + " AND date LIKE '%" + date.substring(0, 10) + "%' AND id_client = 0";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Meeting m = new Meeting();
            m.setMani_id(cursor.getInt(cursor.getColumnIndex("id_mani")));
            m.setMani_id(cursor.getInt(cursor.getColumnIndex("id_client")));
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));

            ary.add(m);
        }
        return ary;
    }

    public void updateMeeting(int mani_id, int client_id, String date) {
        String st = "UPDATE appointments SET id_client = " + client_id + " WHERE id_mani = " + mani_id + " AND id_client = 0 AND date = '" + date + "'";
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(st);
        statement.execute();
    }

    public void addImageToGallery(int mani_id, byte[] image) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO gallery (id_mani, image) values(?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindDouble(1, mani_id);
        statement.bindBlob(2, image);
        statement.execute();
    }

    public ArrayList<GalleryPhoto> getGalleryPhotos(int mani_id) {
        String st = "SELECT * FROM gallery WHERE id_mani = " + mani_id;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        ArrayList<GalleryPhoto> images = new ArrayList<GalleryPhoto>();
        while (cursor.moveToNext()) {
            GalleryPhoto galleryPhoto = new GalleryPhoto(BitmapFactory.decodeByteArray(cursor.getBlob(cursor.getColumnIndex("image")), 0, cursor.getBlob(cursor.getColumnIndex("image")).length));
            images.add(galleryPhoto);
        }
        return images;
    }

    public ArrayList<Meeting> getManicuristMeetingsByDay(int mani_id, String date) {
        ArrayList<Meeting> ary = new ArrayList<>();
        String st = "SELECT * FROM appointments WHERE id_mani = " + mani_id + " AND date LIKE '%" + date.substring(0, 10) + "%' AND NOT client_id = 0";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Meeting m = new Meeting();
            m.setMani_id(cursor.getInt(cursor.getColumnIndex("id_mani")));
            m.setClient_id(cursor.getInt(cursor.getColumnIndex("id_client")));
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));

            ary.add(m);
        }
        return ary;
    }

    public ArrayList<Meeting> getClientMeetingsByDay(int client_id, String date) {
        ArrayList<Meeting> ary = new ArrayList<>();
        String st = "SELECT * FROM appointments WHERE id_client = " + client_id + " AND date LIKE '%" + date.substring(0, 10) + "%'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Meeting m = new Meeting();
            m.setMani_id(cursor.getInt(cursor.getColumnIndex("id_mani")));
            m.setClient_id(cursor.getInt(cursor.getColumnIndex("id_client")));
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));

            ary.add(m);
        }
        return ary;
    }

    public Manicurist getManicuristById(int id) {
        String st = "SELECT * FROM manicurist WHERE id = " + id;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        Manicurist m = new Manicurist();
        cursor.moveToNext();
        m.setId(cursor.getInt(cursor.getColumnIndex("id")));
        m.setId(cursor.getInt(cursor.getColumnIndex("account_id")));
        m.setDetails(cursor.getString(cursor.getColumnIndex("details")));
        m.setFull_name(cursor.getString(cursor.getColumnIndex("full_name")));
        return m;
    }

    public int getClientIdByMeeting(int mani_id, String date) {
        String st = "SELECT * FROM appointments WHERE id_mani = " + mani_id + " AND  date LIKE '%" + date + "%' AND NOT id_client = 0";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        cursor.moveToNext();
        return cursor.getInt(cursor.getColumnIndex("id_client"));
    }

    public int getManicuristIdByMeeting(int client_id, String date) {
        String st = "SELECT * FROM appointments WHERE id_client = " + client_id + " AND  date LIKE '%" + date + "%'";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        cursor.moveToNext();
        return cursor.getInt(cursor.getColumnIndex("id_mani"));
    }


}








