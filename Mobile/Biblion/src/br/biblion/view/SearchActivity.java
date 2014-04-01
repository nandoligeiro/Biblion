

package br.biblion.view;

import android.os.Bundle;

import br.biblion.R;




public class SearchActivity extends DashboardActivity 
{


protected void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    setContentView (R.layout.activity_search);
    setTitleFromActivityLabel (R.id.title_text);
}
    
} 
