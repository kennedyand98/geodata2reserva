package usjt.br.geodata.model.dao;

import android.provider.BaseColumns;

/**
 * Created by KENNEDY on 02/05/2018.
 */

public class PaisDAOContract {

    public static abstract class PaisEntry implements BaseColumns {
        public static final String TABLE_NAME = "pais";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_REGIAO = "regiao";
        public static final String COLUMN_NAME_CAPITAL = "capital";
        public static final String COLUMN_NAME_BANDEIRA = "bandeira";
        public static final String COLUMN_NAME_CODIGO3 = "codigo3";
        public static final String COLUMN_NAME_GINI = "gini";
        public static final String COLUMN_NAME_SUBREGIAO = "subregiao";
        public static final String COLUMN_NAME_DEMONIMO = "demonimo";
        public static final String COLUMN_NAME_POPULACAO = "populacao";
        public static final String COLUMN_NAME_AREA = "area";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";



    }
}
