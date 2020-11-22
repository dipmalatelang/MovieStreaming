package com.netflix.app.home.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.netflix.app.R;
import com.netflix.app.category.Category_Fragment;
import com.netflix.app.databinding.HActivityHomeBinding;
import com.netflix.app.drawer.PrimiumPlan_Activity;
import com.netflix.app.drawer.PrivacyPolicy_Activity;
import com.netflix.app.drawer.Profile_Activity;
import com.netflix.app.drawer.RedeemGiftVoucher_Activity;
import com.netflix.app.drawer.ReferEarn_Activity;
import com.netflix.app.favorites.Favorites_Fragment;

import com.netflix.app.gallary.Gallary_Fragment;

import com.netflix.app.home.adapter.MovieAdapter;
import com.netflix.app.home.model.AllCategory;
import com.netflix.app.utlis.SharedPrefs;
import com.netflix.app.videos.AllVideos_Fragment;

import static com.netflix.app.home.ui.PlayMovieActivity.LAST_MINUTE_VIDEO_PLAYED;
import static com.netflix.app.home.ui.PlayMovieActivity.LAST_VIDEO_PLAYED;

public class Home_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    HActivityHomeBinding binding;
    long mLastPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this,R.layout.h_activity_home_);

        getSupportFragmentManager().beginTransaction().replace(R.id.rl_fragment_container,new HomeFragment()).commit();
        SharedPrefs.getInstance().getLastActivePlayed(LAST_VIDEO_PLAYED,false);
        SharedPrefs.getInstance().getlastPositionVideo(LAST_MINUTE_VIDEO_PLAYED,mLastPosition);
        Log.d("TAG", "onCreate: "+SharedPrefs.getInstance().getLastActivePlayed(LAST_VIDEO_PLAYED,false));
        Log.d("TAG", "onCreateplayvideo: "+SharedPrefs.getInstance().getlastPositionVideo(LAST_MINUTE_VIDEO_PLAYED,mLastPosition));

        /*TODO Create initoolbar for  custom toolbar*/
        initoolbar();
        /*TODO Create bottomMenu for  bottom menu bar*/
        bottomMenu();



    }


    void initoolbar(){

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerlayout,binding.toolbar,R.string.open, R.string.close);
        binding.drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.designnavigationview.setNavigationItemSelectedListener(this);

    }
    private void bottomMenu() {
        binding.chipBottomNavigationview.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.b_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.b_category:
                        fragment = new Category_Fragment();
                        break;
                    case R.id.b_allVideo:
                        fragment = new AllVideos_Fragment();
                        break;
                    case R.id.b_favorites:
                        fragment = new Favorites_Fragment();
                        break;
                    case R.id.b_gallary:
                        fragment = new Gallary_Fragment();
                        break;
                    default:
                        return ;



                }
                getSupportFragmentManager().beginTransaction().replace(R.id.rl_fragment_container,fragment).commit();

            }

        });
    }

    /*TODO Create onNavigationItemSelected for  navigation item */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.m_home:
                Intent homeintent = new Intent(this,Home_Activity.class);
                startActivity(homeintent);

                break;

            case R.id.m_profile:
                Intent intent = new Intent(this, Profile_Activity.class);
                startActivity(intent);
                Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.m_premiumPlans:
                Intent intentpre = new Intent(this, PrimiumPlan_Activity.class);
                startActivity(intentpre);
                Toast.makeText(this, "Prminum plan", Toast.LENGTH_SHORT).show();
                break;

            case R.id.m_redeemGiftVoucher:
                Intent i = new Intent(this, RedeemGiftVoucher_Activity.class);
                startActivity(i);
                Toast.makeText(this, "redeem", Toast.LENGTH_SHORT).show();
                break;
            case R.id.m_privacyPolicy:
                Intent p = new Intent(this, PrivacyPolicy_Activity.class);
                startActivity(p);
                Toast.makeText(this, "privacy policy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.m_referEarn:
                Intent refintent = new Intent(this, ReferEarn_Activity.class);
                startActivity(refintent);
                break;
            case R.id.m_RateApp:
                Intent intentrate = new Intent(android.content.Intent.ACTION_VIEW);
                intentrate.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.amazon.avod.thirdpartyclient"));
                startActivity(intentrate);
                break;
            case R.id.m_shareApp:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
            case R.id.m_logout:
                finish();
                break;


        }
        binding.drawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        MovieAdapter adapter ;
        return false;
    }
}
