package br.com.biblion;

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

import com.br.biblion.R;



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

		
		//Açao Compartilhar
		lvConsulta
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView parent, View arg1,
							int posL, long arg3) {
						// TODO Auto-generated method stub
						
						//PackageManager pm = getPackageManager();
						Intent sendIntent = new Intent(Intent.ACTION_SEND);
						sendIntent.setType("text/*");
						//sendIntent.addCategory(Intent.CATEGORY_DEFAULT);
						
						 sendIntent
							.putExtra(intent.EXTRA_SUBJECT, " By Biblion");
					 
				
					sendIntent.putExtra(Intent.EXTRA_TEXT, (String)livroC + " " + capCount  + ", " +
							(CharSequence)	 parent.getItemAtPosition(posL) + " \n" + "http://is.gd/DsXExU" );
					
				
					
						startActivity(Intent.createChooser(sendIntent,
								"Compartilhe:"));
						

					}

					
				});

		/*Intent intent1 = new Intent(getApplicationContext(), Menu.class);

		intent1.putExtra("livroId", livroId);
		intent1.putExtra("capC", (CharSequence) capC);
		*/
		vibrator = (Vibrator)getSystemService(LeituraActivity.this.VIBRATOR_SERVICE);

		final ViewFlipper vf = (ViewFlipper) findViewById(R.id.vf_leitura);
		
		//Botão back
		ImageButton btBack = (ImageButton) findViewById(R.id.btBack);
		
		btBack.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				
				if(capCount > 1){
					vibrator.vibrate(300);
				//List listaCap = null;
				// Set an animation from res/anim: I pick push left out
				vf.setInAnimation(LeituraActivity.this, R.anim.push_right_in);
				vf.setOutAnimation(LeituraActivity.this, R.anim.push_right_out);
				vf.showPrevious();
				// pesquisar os dados novos no banco
				List listaConL = db.buscarLivroCap(livroId, --capCount);
				ArrayAdapter adapter = (new ArrayAdapter(LeituraActivity.this,
						R.layout.mylist, listaConL));
				 adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_2);
				lvConsulta.setAdapter(adapter);
				tvCap.setText(String.valueOf(capCount));
				
				}else{
					
					Toast toast = Toast.makeText(getApplicationContext(), "Este é o Primeiro Capítulo", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		
		//Botão next
		ImageButton btNext = (ImageButton) findViewById(R.id.btNext);
		btNext.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				
				//int qntVer = db.CountVer(livroId);
				int lastVer = Integer.parseInt(String.valueOf(db.CountVer(livroId)));
				if (capCount < lastVer) {
					vibrator.vibrate(300);
				// Set an animation from res/anim: I pick push left out
				vf.setInAnimation(LeituraActivity.this, R.anim.push_left_in);
				vf.setOutAnimation(LeituraActivity.this, R.anim.push_left_out);
				vf.showNext();
				// pesquisar os dados novos no banco
				List listaConL = db.buscarLivroCap(livroId, ++capCount);
				ArrayAdapter adapter = (new ArrayAdapter(LeituraActivity.this,
						R.layout.mylist, listaConL));
				 adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_2);
				lvConsulta.setAdapter(adapter);
				tvCap.setText(String.valueOf(capCount));

				}else{
					Toast toast = Toast.makeText(getApplicationContext(), "Este é o Último Capítulo", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

	}

	
	
	
	/*@Override
	  protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    
	    outState.putInt(CAP_KEY, capCount);
	  }
	 
	private void saveState(Bundle State) {
		// TODO Auto-generated method stub
		State.getInt(CAP_KEY);
		
	}
*/
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