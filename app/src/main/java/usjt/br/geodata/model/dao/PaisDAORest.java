package usjt.br.geodata.model.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import usjt.br.geodata.model.entity.Pais;
import usjt.br.geodata.model.entity.Regiao;
import usjt.br.geodata.model.Contexto;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by KENNEDY on 02/05/2018.
 */

public class PaisDAORest implements PaisDAO {

    public static final String URL = "https://restcountries.eu/rest/v2/";

    @Override
    public Pais[] buscarPaises(Regiao regiao) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ArrayList<Pais> paises = new ArrayList<>();
        String url;
        if(regiao == Regiao.all) {
            url = URL + regiao;
        }else{
            url = URL + "region/" + regiao;
        }

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        String resultado = response.body().string();

        try {
            JSONArray vetor = new JSONArray(resultado);
            for(int i = 0; i < vetor.length(); i++){
                JSONObject item = (JSONObject) vetor.get(i);
                Pais pais = new Pais();
                try {
                    pais.setArea(item.getInt("area"));
                } catch (Exception e){
                    pais.setArea(0);
                }
                pais.setBandeira(item.getString("flag"));
                pais.setCapital(item.getString("capital"));
                pais.setNome(item.getString("name"));
                pais.setRegiao(item.getString("region"));
                pais.setCodigo3(item.getString("alpha3Code"));
                try {
                    pais.setGini(item.getDouble("gini"));
                } catch (Exception e) {
                    pais.setGini(0.0);
                }
                try {
                    pais.setPopulacao(item.getInt("population"));
                } catch (Exception e) {
                    pais.setPopulacao(0);
                }
                pais.setDemonimo(item.getString("demonym"));
                pais.setSubRegiao(item.getString("subregion"));

                JSONArray latlng = item.getJSONArray("latlng");

                try {
                    pais.setLatitude(latlng.getDouble(0));
                } catch (Exception e) {
                    pais.setLatitude(0);
                }
                try {
                    pais.setLongitude(latlng.getDouble(1));
                } catch (Exception e) {
                    pais.setLongitude(0);
                }

                paises.add(pais);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        //salva os paises no cache (banco de dados)
        PaisDAODb db = new PaisDAODb(Contexto.getValue());
        db.salvarPaises(regiao, paises);

        return paises.toArray(new Pais[0]);
    }
}

