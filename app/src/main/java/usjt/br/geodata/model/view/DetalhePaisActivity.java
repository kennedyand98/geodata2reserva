package usjt.br.geodata.model.view;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import usjt.br.geodata.model.entity.Pais;

public class DetalhePaisActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pais);
        Intent intent = getIntent();
        Pais pais = (Pais)intent.getSerializableExtra(ListaPaisesActivity.PAIS);

        ImageView bandeira = (ImageView)findViewById(R.id.img_bandeira_pais);
        bandeira.setImageDrawable(usjt.br.geodata.view.Util.getDrawable(this, pais.getCodigo3().toLowerCase()));

        TextView nome = (TextView)findViewById(R.id.txt_nome_pais);
        nome.setText(pais.getNome());

        TextView capital = (TextView)findViewById(R.id.txt_capital);
        capital.setText(pais.getCapital());

        TextView regiao = (TextView)findViewById(R.id.txt_regiao);
        regiao.setText(pais.getRegiao());

        TextView subregiao = (TextView)findViewById(R.id.txt_subregiao);
        subregiao.setText(pais.getSubRegiao());

        TextView demonimo = (TextView)findViewById(R.id.txt_demonimo);
        demonimo.setText(pais.getDemonimo());

        TextView area = (TextView)findViewById(R.id.txt_area);
        area.setText(String.format("%1$,d km\u00B2", pais.getArea()));

        TextView populacao = (TextView)findViewById(R.id.txt_populacao);
        populacao.setText(String.format("%1$,d", pais.getPopulacao()));

        TextView gini = (TextView)findViewById(R.id.txt_gini);
        gini.setText(String.format("%.2f", pais.getGini()));

        TextView latitude = (TextView)findViewById(R.id.txt_latitude);
        latitude.setText(String.format("%.2f", pais.getLatitude()));

        TextView longitude = (TextView)findViewById(R.id.txt_longitude);
        longitude.setText(String.format("%.2f", pais.getLongitude()));
    }
}

