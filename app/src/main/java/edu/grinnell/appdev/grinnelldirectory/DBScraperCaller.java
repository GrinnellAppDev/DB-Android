package edu.grinnell.appdev.grinnelldirectory;


import android.content.Context;
import android.net.Uri;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.interfaces.NetworkAPI;

public class DBScraperCaller implements NetworkAPI{

    private static final String BASE_URL = "https://itwebapps.grinnell.edu/classic/asp/campusdirectory/GCdefault.asp?transmit=true&blackboardref=true&";
    private static final String[] QUERY_KEYS = {
            "FirstName",
            "LastName",
            "Major",
            "Gyear",
            "conc",
            "SGA",
            "email",
            "campusphonenumber",
            "Hiatus",
            "Homequery",
            "Department",
            "campusquery",
            "",
            ""
            };

    private Context context;
    private APICallerInterface apiInterface;

    public DBScraperCaller (Context context, APICallerInterface apiInterface) {
        this.context = context;
        this. apiInterface = apiInterface;
    }

    @Override
    public void simpleSearch(List<String> fields) {
        Uri.Builder builder = Uri.parse(BASE_URL).buildUpon();
        for (int i = 0; i < 4; i++) { // simple search looks at the first four fields
            builder.appendQueryParameter(QUERY_KEYS[i], fields.get(i));
        }
        String uri = builder.build().toString();
        new DBScraper(context, apiInterface).execute(uri);
    }

    @Override
    public void advancedSearch(List<String> fields) {
        Uri.Builder builder = Uri.parse(BASE_URL).buildUpon();
        for (int i = 0; i < QUERY_KEYS.length; i++) {
            builder.appendQueryParameter(QUERY_KEYS[i], fields.get(i));
        }
        String uri = builder.build().toString();
        new DBScraper(context, apiInterface).execute(uri);
    }
}
