package usjt.br.geodata.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KENNEDY on 02/05/2018.
 */

public class PaisDAODbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Paises.db";
    public static final String SQL_CREATE_PAIS =
            "CREATE TABLE " + PaisDAOContract.PaisEntry.TABLE_NAME + "(" +
                    PaisDAOContract.PaisEntry._ID + " INTEGER PRIMARY KEY,"+
                    PaisDAOContract.PaisEntry.COLUMN_NAME_NOME + " TEXT," +
                    PaisDAOContract.PaisEntry.COLUMN_NAME_REGIAO + " TEXT," +
                    PaisDAOContract.PaisEntry.COLUMN_NAME_CAPITAL + " TEXT," +
                    PaisDAOContract.PaisEntry.COLUMN_NAME_BANDEIRA + " TEXT," +
                    PaisDAOContract.PaisEntry.COLUMN_NAME_CODIGO3 + " TEXT," +
                    PaisDAOContract.PaisEntry.COLUMN_NAME_GINI + " REAL," +
                    PaisDAOContract.PaisEntry.COLUMN_NAME_SUBREGIAO + " TEXT," +
                    PaisDAOContract.PaisEntry.COLUMN_NAME_DEMONIMO + " TEXT," +
                    PaisDAOContract.PaisEntry.COLUMN_NAME_POPULACAO + " INTEGER," +
                    PaisDAOContract.PaisEntry.COLUMN_NAME_AREA + " INTEGER," +
                    PaisDAOContract.PaisEntry.COLUMN_NAME_LATITUDE + " REAL," +
                    PaisDAOContract.PaisEntry.COLUMN_NAME_LONGITUDE + " REAL)";
    public static final String SQL_DROP_PAIS =
            "DROP TABLE IF EXISTS " + PaisDAOContract.PaisEntry.TABLE_NAME;

    public PaisDAODbHelper(Context contexto){
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PAIS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_PAIS);
        db.execSQL(SQL_CREATE_PAIS);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, newVersion, oldVersion);
    }
}
