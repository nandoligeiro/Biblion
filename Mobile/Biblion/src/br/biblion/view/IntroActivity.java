package br.biblion.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import br.biblion.R;

public class IntroActivity extends DashboardActivity {

	private final int DURACAO_DA_TELA = 2000;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_intro);

		new Handler().postDelayed(new Runnable() {
			public void run() {

				Intent minhaAcao = new Intent(IntroActivity.this,
						HomeActivity.class);

				IntroActivity.this.startActivity(minhaAcao);

				IntroActivity.this.finish();
			}
		}, DURACAO_DA_TELA);

	}
}
