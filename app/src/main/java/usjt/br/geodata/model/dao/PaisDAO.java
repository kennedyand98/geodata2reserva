package usjt.br.geodata.model.dao;

import java.io.IOException;

import usjt.br.geodata.model.entity.Pais;
import usjt.br.geodata.model.entity.Regiao;

/**
 * Created by KENNEDY on 02/05/2018.
 */

public interface PaisDAO {
    Pais[] buscarPaises(Regiao regiao) throws IOException;
}