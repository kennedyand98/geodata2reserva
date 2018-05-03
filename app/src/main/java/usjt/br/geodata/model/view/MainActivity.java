package usjt.br.geodata.model.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

import usjt.br.geodata.model.Contexto;
import usjt.br.geodata.model.entity.Pais;
import usjt.br.geodata.model.entity.Regiao;
import usjt.br.geodata.model.service.NetworkStatus;
import usjt.br.geodata.model.service.PaisService;

public class MainActivity extends Activity {
    Spinner spinnerContinente;
    Regiao regiao = Regiao.all;
    public static final String PAISES = "usjt.br.geodata.paises";
    Pais[] paises;
    Intent intent;
    ProgressBar timer;
    Context contexto;
    PaisService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerContinente = (Spinner) findViewById(R.id.spinnerContinente);
        spinnerContinente.setOnItemSelectedListener(new RegiaoSelecionada());
        timer = (ProgressBar) findViewById(R.id.timer);
        timer.setVisibility(View.INVISIBLE);
        Contexto.setValue(this);
        contexto = Contexto.getValue();
    }

    public void listarPaises(View view) {
        intent = new Intent(this, ListaPaisesActivity.class);
        if (NetworkStatus.isConnected(this)) {
            service = new PaisService(true);//online
            timer.setVisibility(View.VISIBLE);
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {

                            try {
                                paises = service.buscarPaises(regiao);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            runOnUiThread(new Runnable() {
                                              @Override
                                              public void run() {
                                                  intent.putExtra(PAISES, paises);
                                                  startActivity(intent);
                                                  timer.setVisibility(View.INVISIBLE);
                                              }
                                          }
                            );

                        }
                    }).start();
        } else {
            Toast.makeText(this, getApplicationContext().getResources().
                            getString(R.string.msg_rede),
                    Toast.LENGTH_SHORT).show();
            service = new PaisService(false);
            new CarregaPaisesDoBanco().execute("pais");
        }
    }

    private class RegiaoSelecionada implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String continente = (String) parent.getItemAtPosition(position);
            String[] continentes = getApplicationContext().getResources().
                    getStringArray(R.array.continentes);
            if (continente.equals(continentes[0])) {
                regiao = Regiao.all;
            } else if (continente.equals(continentes[1])) {
                regiao = Regiao.Africa;
            } else if (continente.equals(continentes[2])) {
                regiao = Regiao.Americas;
            } else if (continente.equals(continentes[3])) {
                regiao = Regiao.Asia;
            } else if (continente.equals(continentes[4])) {
                regiao = Regiao.Europe;
            } else if (continente.equals(continentes[5])) {
                regiao = Regiao.Oceania;
            } else if (continente.equals(continentes[6])) {
                regiao = Regiao.Polar;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class CarregaPaisesDoBanco extends AsyncTask<String, Void, Pais[]> {

        @Override
        protected Pais[] doInBackground(String... params) {
            Pais[] paises = null;
            try {
                paises = service.buscarPaises(regiao);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return paises;
        }

        public void onPostExecute(Pais[] paises) {
            intent.putExtra(PAISES, paises);
            startActivity(intent);
        }
    }
}
