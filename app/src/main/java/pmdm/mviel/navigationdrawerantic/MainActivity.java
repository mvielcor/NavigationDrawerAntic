package pmdm.mviel.navigationdrawerantic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_antic);

        // Instanciem el DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.elDrawlerLayout);
        mGridViewDrawer = (GridView) findViewById(R.id.grid_Opcions_Nav_Drawer);

        // Creem un arrayList d'objectes DrawerItem
        for (int i = 0; i < mTEXTOS_OPCIONS.length; i++) {
            LlistaItems.add(new DrawerItem(mTEXTOS_OPCIONS[i], mICONS[i]));
        }
        // Establim l'Adaptador del GridView
        mGridViewDrawer.setAdapter(new DrawerGridAdapter(this, LlistaItems));

        // Afegim un Listener per a que reaccione a la nostra selecció
        mGridViewDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // Accedim al Linear Layout definit en item_gridview_drawerlayout.xml
                LinearLayout ll = (LinearLayout) v;
                //accedim a l'Element TextView que ocupa la segona posició
                TextView tv = (TextView) ll.getChildAt(1);
                //Mostrem un Toast amb el text i la posició que ocupa l'ítem selecionat dins del gridview
                Toast.makeText(getApplicationContext(), tv.getText() + " (" + position + ")", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Mostrem la icona de navegació a l'ActionBar
        getActionBar().setDisplayHomeAsUpEnabled(true);
        // Activem l'icona de navegació de l'ActionBar per a que puga mostrar i ocultar el navigationDrawer
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // Ací posarem el codi que vulguem que s'execute quan el navigationDrawer esta obert
                //Per fer una prova, li direm que mostre el text "Obrint Drawer"
                getActionBar().setTitle(R.string.openDrawer);
                //Sincronitzem (important)
                mDrawerToggle.syncState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Ací posarem el codi que vulguem que s'execute quan el navigationDrawer esta obert
                //Per fer una prova, li direm que mostre el text "Tancant Drawer"
                getActionBar().setTitle(R.string.closeDrawer);
                //Sincronitzem (important)
                mDrawerToggle.syncState();
            }
        };

        // Li asociem com a Listener, el lístener del ActionBar
        mDrawerLayout.setDrawerListener(mDrawerToggle); // Listener que escolta quan s'obri o tanca
        // el NavigationDrawer des del toggle de l'ActionBar
        mDrawerToggle.syncState();               // Finalment, sincronitzem l'estat del toggle.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Gestionem els Clicks de l'actionBar ací. L'Action bar gestionarà els clicks de la icona
        // Home/Up tal i com s'ha especificat al fitxer AndroidManifest.xml
        int id = item.getItemId();

        //Si s'ha polsat L'icona de l'actionBar, tornarem true, per indicar que hem gestionat l'event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //Si s'ha polsat el text de configuratió, tornarem true per indicar que hem gestionat l'event
        if (id == R.id.action_settings) {
            return true;
        }
        //En qualsevol altre cas, tornarem super.onOptionsItemSelected(item) per passar l'event a
        // la classe pare, i que el gestione ella.
        return super.onOptionsItemSelected(item);
    }

}
