package com.floatingreels.sharpnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    //nodig om te kunnen navigeren dmv navhost uit UI
    private NavController navController;
    //nodig om navigatie-opties in te stellen in toolbar uit UI
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //verwijzing maken naar component in UI
        Toolbar toolbar = findViewById(R.id.toolbar);
        //compatibiliteit met actionbar uit vorige versies
        setSupportActionBar(toolbar);

        //navcontroller binnen huidige activity linken aan de navhost via ID
        navController = Navigation.findNavController(this, R.id.nav_host);
        //instellen van top-level in de navigatie
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.noteListFragment).build();
        //navcontroller en de appbarconfig aan elkaar koppelen
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }
     @Override
    public boolean onSupportNavigateUp() {
        //de terug-knop uit UI neemt functies over van de fysieke terug-knop van device
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu wordt opgeblazen door xml uit res\menu
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //welk menu-item is geselecteerd
        switch (item.getItemId()){
            //navigatie starten door menu-item in de nav controller aan te spreken
            //ID van fragment in main_nav moet overeenkomen met ID van component in main_menu
            case R.id.settingsFragment: NavigationUI.onNavDestinationSelected(item, navController);
        }
        return super.onOptionsItemSelected(item);
    }
}
