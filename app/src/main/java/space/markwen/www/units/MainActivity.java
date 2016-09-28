package space.markwen.www.units;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Show content_length at start
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, new LengthActivity())
                .commit();

        // Select content_length at start in side drawer
        navigationView.setCheckedItem(R.id.nav_length);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks
        int id = item.getItemId();

        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_area) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new AreaActivity())
                    .commit();
        } else if (id == R.id.nav_fuel) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new FuelActivity())
                    .commit();
        } else if (id == R.id.nav_length) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new LengthActivity())
                    .commit();
//        } else if (id == R.id.nav_programming) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.content_frame, new ProgrammingActivity())
//                    .commit();
        } else if (id == R.id.nav_speed) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new SpeedActivity())
                    .commit();
        } else if (id == R.id.nav_temperature) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new TemperatureActivity())
                    .commit();
        } else if (id == R.id.nav_weight) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new WeightActivity())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
