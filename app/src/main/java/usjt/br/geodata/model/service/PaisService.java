package usjt.br.geodata.model.service;

import java.io.IOException;

import usjt.br.geodata.model.dao.PaisDAO;
import usjt.br.geodata.model.dao.PaisDAOFactory;
import usjt.br.geodata.model.entity.Pais;
import usjt.br.geodata.model.entity.Regiao;

/**
 * Created by KENNEDY on 02/05/2018.
 */

public class PaisService {
    PaisDAO dao;

    public PaisService(boolean onLine){
        dao = PaisDAOFactory.getPaisDAO(onLine);
    }
    public Pais[] buscarPaises(Regiao regiao) throws IOException {
        return dao.buscarPaises(regiao);
    }
}
