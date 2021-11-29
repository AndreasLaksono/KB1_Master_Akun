package master_akun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    Context context;
    private static final String dbname = "masterakun.db";
    private static final int dbversion = 1;
    private static final String tbl_akun_ = "master_akun";
    private static final String tbl_akun_id = "_id";
    private static final String tbl_nomor_akun = "nomor";
    private static final String tbl_nama_akun = "nama";
    private static final String tbl_laporan_akun = "laporan";

    public Database(@Nullable Context context) {
        super(context, dbname, null, dbversion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table_akun_ = "CREATE TABLE IF NOT EXISTS " + tbl_akun_ + " ( "
                + tbl_akun_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tbl_nomor_akun + " TEXT,"
                + tbl_nama_akun + " TEXT,"
                + tbl_laporan_akun + " TEXT);";

        try {
            sqLiteDatabase.execSQL(create_table_akun_);
            Toast.makeText(context, "Database Berhasil Dibuat",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Database Gagal Dibuat",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tbl_akun_);
        onCreate(sqLiteDatabase);
    }

    public void insertData(int nomor, String nama, String laporan) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(tbl_nomor_akun, nomor);
        cv.put(tbl_nama_akun, nama);
        cv.put(tbl_laporan_akun, laporan);
        long hasil = sqLiteDatabase.insert(tbl_akun_, null, cv);
        if (hasil == -1) {
            Toast.makeText(context, "Gagal Menyimpan Data",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Menyimpan Data",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor MasteringAkun() {
        String sql_akun = "SELECT * FROM " + tbl_akun_;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql_akun, null);
        }

        return cursor;
    }

    public void updataAkun(String id_row, String nomor, String nama, String laporan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(tbl_nomor_akun,nomor);
        cv.put(tbl_nama_akun,nama);
        cv.put(tbl_laporan_akun,laporan);
        long hasil = db.update(tbl_akun_,cv,"_id=?",new String[]{id_row});
        if(hasil == -1){
            Toast.makeText(context,"Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,nama, Toast.LENGTH_SHORT).show();
        }
    }

    public void DeleteAkun(String id_row){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(tbl_akun_, "_id=?", new String[]{id_row});

        if(result == -1){
            Toast.makeText(context, "Data akun gagal dihapus", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Data akun Berhasil dihapus", Toast.LENGTH_SHORT).show();
        }
    }
}