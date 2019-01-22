package minhaturma.ufrpe.br.minhaturma;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import minhaturma.ufrpe.br.minhaturma.assignments.AssignmentsFragment;
import minhaturma.ufrpe.br.minhaturma.auth.AuthService;
import minhaturma.ufrpe.br.minhaturma.commons.MTFragment;
import minhaturma.ufrpe.br.minhaturma.confidence.ConfidencesFragment;
import minhaturma.ufrpe.br.minhaturma.messages.MessagesFragment;
import minhaturma.ufrpe.br.minhaturma.news.NewsFragment;
import minhaturma.ufrpe.br.minhaturma.presences.PresenceFragment;
import minhaturma.ufrpe.br.minhaturma.quizzes.QuizFragment;
import minhaturma.ufrpe.br.minhaturma.students.LoginActivity;
import minhaturma.ufrpe.br.minhaturma.students.ProfileFragment;

public class
MainActivity extends AppCompatActivity
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

        navigationView.setCheckedItem(R.id.news);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, NewsFragment.getInstance());
        ft.commit();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.refresh) {

            Fragment visibleFragment = getVisibleFragment();

            if (visibleFragment != null && visibleFragment instanceof MTFragment) {
                ((MTFragment) visibleFragment).onRefresh();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (id == R.id.news) {
            ft.replace(R.id.fragment_container, NewsFragment.getInstance());
        } else if (id == R.id.presence) {
            ft.replace(R.id.fragment_container, PresenceFragment.getInstance());
            setTitle(PresenceFragment.getInstance().getTitle());
        } else if (id == R.id.quiz) {
            ft.replace(R.id.fragment_container, QuizFragment.getInstance());
            setTitle(QuizFragment.getInstance().getTitle());
        } else if (id == R.id.confidence) {
            ft.replace(R.id.fragment_container, ConfidencesFragment.getInstance());
            setTitle(ConfidencesFragment.getInstance().getTitle());
        } else if (id == R.id.messages) {
            ft.replace(R.id.fragment_container, MessagesFragment.getInstance(), MessagesFragment.TAG);
            setTitle(MessagesFragment.getInstance().getTitle());
        } else if (id == R.id.profile) {
            ft.replace(R.id.fragment_container, ProfileFragment.getInstance(), ProfileFragment.TAG);
            setTitle(ProfileFragment.getInstance().getTitle());
        } else if (id == R.id.logout) {
            logout();
        }

        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Fragment getVisibleFragment(){
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }

    private void logout() {
        AuthService service = AuthService.getInstance();
        service.logout();

        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
}
