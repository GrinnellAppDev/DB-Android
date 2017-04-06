package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import edu.grinnell.appdev.grinnelldirectory.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, SearchResultsActivity.class);
        startActivity(intent);
    }

}
