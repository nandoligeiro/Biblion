/*
 * Copyright (C) 2011 Wglxy.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.biblion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public abstract class DashboardActivity extends Activity 
{

	

protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_default);
}
    


protected void onDestroy ()
{
   super.onDestroy ();
}



protected void onPause ()
{
   super.onPause ();
}



protected void onRestart ()
{
   super.onRestart ();
}



protected void onResume ()
{
   super.onResume ();

}

protected void onStart ()
{
   super.onStart ();
}



protected void onStop ()
{
   super.onStop ();
}



public void onClickHome (View v)
{
    goHome (this);
}



public void onClickSearch (View v)
{
    startActivity (new Intent(getApplicationContext(), SearchActivity.class));
}



public void onClickAbout (View v)
{
    startActivity (new Intent(getApplicationContext(), AboutActivity.class));
}

public void onClickLeitura (View v)
{
    startActivity (new Intent(getApplicationContext(), LeituraActivity.class));
}


public void goHome(Context context) 
{
    final Intent intent = new Intent(context, HomeActivity.class);
    intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
    context.startActivity (intent);
}



	


public void setTitleFromActivityLabel (int textViewId)
{
    TextView tv = (TextView) findViewById (textViewId);
    if (tv != null) tv.setText (getTitle ());
} 



public void toast (String msg)
{
    Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
} 


public void trace (String msg) 
{
    Log.d("Biblion", msg);
    toast (msg);
}

} 
