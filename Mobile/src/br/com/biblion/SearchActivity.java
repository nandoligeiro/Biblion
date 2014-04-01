

package br.com.biblion;

import android.os.Bundle;

import com.br.biblion.R;




public class SearchActivity extends DashboardActivity 
{


protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    setContentView (R.layout.activity_search);
    setTitleFromActivityLabel (R.id.title_text);
}
    
} 
