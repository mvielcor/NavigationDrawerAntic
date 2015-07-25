package pmdm.mviel.navigationdrawerantic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    //Declarem els textos del GridView per a mostrar-los en un Navigation Drawer
    //Els textos i les icones a mostrar ho guardem en un array, per a construir el GridView des
    // d'un ArrayAdapter (ja que és la manera més ràpida de crear un GridView)

    String mTEXTOS_OPCIONS[] = {"Inici","Events","Mail","Botiga","Escola","Inici","Events","Mail","Botiga","Escola"};
    int mICONS[] = {R.mipmap.ic_home_black_24dp,R.mipmap.ic_event_black_24dp,R.mipmap.ic_mail_black_24dp,
            R.mipmap.ic_shopping_cart_black_24dp,R.mipmap.ic_school_black_24dp,R.mipmap.ic_home_black_24dp,
            R.mipmap.ic_event_black_24dp,R.mipmap.ic_mail_black_24dp,R.mipmap.ic_shopping_cart_black_24dp,
            R.mipmap.ic_school_black_24dp};

    DrawerLayout mDrawerLayout;
    GridView mGridViewDrawer;
    ArrayList<DrawerItem> LlistaItems= new ArrayList<DrawerItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_antic);

        // Instanciem el DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.elDrawlerLayout);
        mGridViewDrawer = (GridView) findViewById(R.id.grid_Opcions_Nav_Drawer);

        // Creem un arrayList d'objectes DrawerItem
        for(int i=0;i<mTEXTOS_OPCIONS.length;i++){
            LlistaItems.add(new DrawerItem(mTEXTOS_OPCIONS[i],mICONS[i]));
        }
        // Establim l'Adaptador del GridView
        mGridViewDrawer.setAdapter(new DrawerGridAdapter(this,LlistaItems));

/*        mGridViewDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
