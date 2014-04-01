package br.biblion.view;



import android.os.Bundle;
import android.widget.TextView;
import br.biblion.R;

public class AboutActivity extends DashboardActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_about);
		// setTitleFromActivityLabel (Sobre);
		TextView sobre = (TextView) findViewById(R.id.title_text);
		sobre.setText("Sobre");
		
		TextView tvAbout = (TextView) findViewById(R.id.tvAbout);

				
		
	}

}
