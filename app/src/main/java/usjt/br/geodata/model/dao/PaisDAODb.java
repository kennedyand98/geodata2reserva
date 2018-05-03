package usjt.br.geodata.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import usjt.br.geodata.model.entity.Pais;
import usjt.br.geodata.model.entity.Regiao;

/**
 * Created by KENNEDY on 02/05/2018.
 */

public class PaisDAODb implements PaisDAO {
    PaisDAODbHelper dbHelper;

    public PaisDAODb(Context contexto){
        dbHelper = new PaisDAODbHelper(contexto);
    }

    public void salvarPaises(Regiao regiao, ArrayList<Pais> paises){
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        if(regiao == Regiao.all) {
            db.delete(PaisDAOContract.PaisEntry.TABLE_NAME, null, null);
        } else {
            String where = PaisDAOContract.PaisEntry.COLUMN_NAME_REGIAO + " = ?";
            String[] conditions = {regiao.toString()};
            db.delete(PaisDAOContract.PaisEntry.TABLE_NAME, where, conditions);
        }

        for(Pais pais:paises){
            ContentValues values = new ContentValues();
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_NOME, pais.getNome());
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_REGIAO, pais.getRegiao());
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_CAPITAL, pais.getCapital());
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_BANDEIRA, pais.getBandeira());
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_CODIGO3, pais.getCodigo3());
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_SUBREGIAO, pais.getSubRegiao());
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_GINI, pais.getGini());
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_POPULACAO, pais.getPopulacao());
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_AREA, pais.getArea());
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_LATITUDE, pais.getLatitude());
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_LONGITUDE, pais.getLongitude());
            values.put(PaisDAOContract.PaisEntry.COLUMN_NAME_DEMONIMO, pais.getDemonimo());

            db.insert(PaisDAOContract.PaisEntry.TABLE_NAME, null, values);
        }
        db.close();
    }

    public Pais[] buscarPaises(Regiao regiao){
        ArrayList<Pais> paises = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = { PaisDAOContract.PaisEntry.COLUMN_NAME_NOME,
                PaisDAOContract.PaisEntry.COLUMN_NAME_REGIAO,
                PaisDAOContract.PaisEntry.COLUMN_NAME_CAPITAL,
                PaisDAOContract.PaisEntry.COLUMN_NAME_BANDEIRA,
                PaisDAOContract.PaisEntry.COLUMN_NAME_CODIGO3,
                PaisDAOContract.PaisEntry.COLUMN_NAME_SUBREGIAO,
                PaisDAOContract.PaisEntry.COLUMN_NAME_DEMONIMO,
                PaisDAOContract.PaisEntry.COLUMN_NAME_AREA,
                PaisDAOContract.PaisEntry.COLUMN_NAME_GINI,
                PaisDAOContract.PaisEntry.COLUMN_NAME_POPULACAO,
                PaisDAOContract.PaisEntry.COLUMN_NAME_LATITUDE,
                PaisDAOContract.PaisEntry.COLUMN_NAME_LONGITUDE};
        String ordem = PaisDAOContract.PaisEntry.COLUMN_NAME_NOME;

        Cursor c = null;
        if(regiao == Regiao.all) {
            c = db.query(PaisDAOContract.PaisEntry.TABLE_NAME, colunas, null, null,
                    ordem, null, null);
        } else {
            String where = PaisDAOContract.PaisEntry.COLUMN_NAME_REGIAO + " = ?";
            String[] conditions = {regiao.toString()};
            c = db.query(PaisDAOContract.PaisEntry.TABLE_NAME, colunas, where, conditions,
                    ordem, null, null);
        }
        while(c.moveToNext()) {
            Pais pais = new Pais();
            pais.setNome(c.getString(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_NOME)));
            pais.setRegiao(c.getString(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_REGIAO)));
            pais.setCapital(c.getString(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_CAPITAL)));
            pais.setBandeira(c.getString(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_BANDEIRA)));
            pais.setCodigo3(c.getString(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_CODIGO3)));
            pais.setSubRegiao(c.getString(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_SUBREGIAO)));
            pais.setGini(c.getDouble(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_GINI)));
            pais.setArea(c.getInt(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_AREA)));
            pais.setPopulacao(c.getInt(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_POPULACAO)));
            pais.setDemonimo(c.getString(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_DEMONIMO)));
            pais.setLatitude(c.getDouble(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_LATITUDE)));
            pais.setLongitude(c.getDouble(c.getColumnIndex(PaisDAOContract.PaisEntry.COLUMN_NAME_LONGITUDE)));

            paises.add(pais);
        }
        c.close();
        db.close();
        if(paises.size()> 0) {
            return paises.toArray(new Pais[0]);
        } else {
            return new Pais[0];
        }
    }
}

