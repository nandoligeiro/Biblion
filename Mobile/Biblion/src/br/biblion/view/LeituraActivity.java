package br.biblion.view;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import br.biblion.R;
import br.biblion.helper.DbHelper;

public class LeituraActivity extends DashboardActivity {

	private Vibrator vibrator;

	private int capCount;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leitura);

		final Intent intent = getIntent();
		Bundle extras = intent.getExtras();

		final long livroId = extras.getLong("livroId");
		final CharSequence livroC = extras.getCharSequence("livroC");
		final CharSequence capC = extras.getCharSequence("capC");
		capCount = Integer.parseInt(String.valueOf(capC));

		final DbHelper db = new DbHelper(LeituraActivity.this);

		try {
			db.openDataBase();
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final TextView tvCap = (TextView) findViewById(R.id.tvCap);
		tvCap.setText(capC);

		// Nome do Livro
		TextView tvNomeLivro = (TextView) findViewById(R.id.tvNomeLivro);
		tvNomeLivro.setText(livroC);

		// Lista texto da consulta realizada

		final ListView lvConsulta = (ListView) findViewById(R.id.lvConsulta);

		final List listaConL = db.buscarLivroCap(livroId, capC);
		final ArrayAdapter adapter = (new ArrayAdapter(LeituraActivity.this,
				R.layout.mylist, listaConL));
		adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_2);
		lvConsulta.setAdapter(adapter);

		// Compartilhar
		lvConsulta
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView parent, View arg1,
							int posL, long arg3) {

						Intent sendIntent = new Intent(Intent.ACTION_SEND);
						sendIntent.setType("text/*");

						sendIntent
								.putExtra(intent.EXTRA_SUBJECT, " By Biblion");

						sendIntent.putExtra(
								Intent.EXTRA_TEXT,
								(String) livroC
										+ " "
										+ capCount
										+ ", "
										+ (CharSequence) parent
												.getItemAtPosition(posL)
										+ " \n" + "http://goo.gl/8kDPMp");

						startActivity(Intent.createChooser(sendIntent,
								"Compartilhe:"));

					}

				});

		vibrator = (Vibrator) getSystemService(LeituraActivity.this.VIBRATOR_SERVICE);

		final ViewFlipper vf = (ViewFlipper) findViewById(R.id.vf_leitura);

		// back
		ImageButton btBack = (ImageButton) findViewById(R.id.btBack);

		btBack.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (capCount > 1) {
					vibrator.vibrate(300);

					vf.setInAnimation(LeituraActivity.this,
							R.anim.push_right_in);
					vf.setOutAnimation(LeituraActivity.this,
							R.anim.push_right_out);
					vf.showPrevious();

					// pesquisar os dados novos no banco
					List listaConL = db.buscarLivroCap(livroId, --capCount);
					ArrayAdapter adapter = (new ArrayAdapter(
							LeituraActivity.this, R.layout.mylist, listaConL));
					adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_2);
					lvConsulta.setAdapter(adapter);
					tvCap.setText(String.valueOf(capCount));

				} else {

					Toast toast = Toast.makeText(getApplicationContext(),
							getString(R.string.first_cap), Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		// next
		ImageButton btNext = (ImageButton) findViewById(R.id.btNext);
		btNext.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				int lastVer = Integer.parseInt(String.valueOf(db
						.CountVer(livroId)));
				if (capCount < lastVer) {
					vibrator.vibrate(300);

					vf.setInAnimation(LeituraActivity.this, R.anim.push_left_in);
					vf.setOutAnimation(LeituraActivity.this,
							R.anim.push_left_out);
					vf.showNext();
					// pesquisar os dados novos no banco
					List listaConL = db.buscarLivroCap(livroId, ++capCount);
					ArrayAdapter adapter = (new ArrayAdapter(
							LeituraActivity.this, R.layout.mylist, listaConL));
					adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_2);
					lvConsulta.setAdapter(adapter);
					tvCap.setText(String.valueOf(capCount));

				} else {
					Toast toast = Toast.makeText(getApplicationContext(),
							getString(R.string.last_cap), Toast.LENGTH_SHORT);
					toast.show();
				}
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

}