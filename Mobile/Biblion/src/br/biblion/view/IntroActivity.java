package br.biblion.view;




import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import br.biblion.R;

public class IntroActivity extends DashboardActivity{
	
	// tempo de duração da tela (3000 milisegundos é equivalente a 3 segundos)
    private final int DURACAO_DA_TELA = 2000;
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        this.setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       */
        // seta o layout dessa atividade
        setContentView(R.layout.activity_intro);
       
        //o postDelayed faz com que a nossa atividade principal seja startada depois do intervalo especificado na constante (DURACAO_DA_TELA)
        new Handler().postDelayed(new Runnable(){          
            public void run() {
                //criamos um intent para nossa classe Principal
                Intent minhaAcao = new Intent(IntroActivity.this,HomeActivity.class);
                //startamos uma atividade passando o intent criado
                IntroActivity.this.startActivity(minhaAcao);
                //finaliza
                IntroActivity.this.finish();
            }
        },DURACAO_DA_TELA); 

    }
}
