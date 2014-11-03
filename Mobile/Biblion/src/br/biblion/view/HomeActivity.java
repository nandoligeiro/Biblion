package br.biblion.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import br.biblion.R;
import br.biblion.helper.DbHelper;

public class HomeActivity extends DashboardActivity {

	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);

		final Spinner spLivro = (Spinner) findViewById(R.id.spLivro);
		final Spinner spCap = (Spinner) findViewById(R.id.spCap);
		// final Spinner spVer = (Spinner) findViewById(R.id.spVer);

		// Nova instancia do DbHelper

		final DbHelper db = new DbHelper(this);

		// db.checkDataBase();

		try {
			db.createDataBase();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			db.openDataBase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// spinner Livros

		List listaLivro = db.buscarLivros();
		final ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, listaLivro);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spLivro.setAdapter(adapter);

		// spinner position

		spLivro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(final AdapterView parent, View v,
					final int pos, long id) {

				// spinner cap

				List listaCap = db.buscarCap(spLivro.getSelectedItemId());
				ArrayAdapter adapter2 = new ArrayAdapter(HomeActivity.this,
						android.R.layout.simple_spinner_item, listaCap);
				adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spCap.setAdapter(adapter2);

				// spinner position

				spCap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					public void onItemSelected(AdapterView parentc, View vc,
							int posc, long idc) {

					}

					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		ImageButton btConsulta = (ImageButton) findViewById(R.id.btConsulta);
		btConsulta.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {

				Intent intent = new Intent(getApplicationContext(),
						LeituraActivity.class);
				intent.putExtra("livroC",
						(CharSequence) spLivro.getSelectedItem());
				intent.putExtra("capC", (CharSequence) spCap.getSelectedItem());
				intent.putExtra("capId", spCap.getSelectedItemId());
				intent.putExtra("livroId", spLivro.getSelectedItemId());
				// intent.putExtra("capId", (Boolean) spCap.getSelectedItem());

				startActivity(intent);

			}

		});

	}

	protected void onDestroy() {
		super.onDestroy();
	}

	protected void onPause() {
		super.onPause();
	}

	protected void onRestart() {
		super.onRestart();
	}

	protected void onResume() {
		super.onResume();
	}

	protected void onStart() {
		super.onStart();
	}

	protected void onStop() {
		super.onStop();
	}

	public void mensagemExibir(String titulo, String texto) {

		AlertDialog.Builder mensagem = new AlertDialog.Builder(
				HomeActivity.this);
		mensagem.setTitle(titulo);
		mensagem.setMessage(texto);
		mensagem.setNeutralButton("Ok", null);
		mensagem.show();
	}

}
