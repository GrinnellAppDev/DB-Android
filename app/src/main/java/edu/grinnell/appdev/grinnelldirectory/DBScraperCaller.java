package edu.grinnell.appdev.grinnelldirectory;


import android.content.Context;
import android.net.Uri;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.interfaces.APICallerInterface;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchCaller;

public class DBScraperCaller implements SearchCaller {

    private static final String BASE_URL = "https://itwebapps.grinnell.edu/classic/asp/campusdirectory/GCdefault.asp?transmit=true&blackboardref=true";

    private Context context;
    private APICallerInterface apiInterface;

    public DBScraperCaller(Context context, APICallerInterface apiInterface) {
        this.context = context;
        this.apiInterface = apiInterface;
    }

    //@Override
    //public void simpleSearch(List<String> fields) {
    //    Uri.Builder builder = Uri.parse(BASE_URL).buildUpon();
    //    for (int i = 0; i < 4; i++) { // simple search looks at the first four fields
    //        builder.appendQueryParameter(QUERY_KEYS[i], fields.get(i));
    //    }
    //    String uri = builder.build().toString();
    //    new DBScraper(context, apiInterface).execute(uri);
    //}
    //
    //@Override
    //public void advancedSearch(List<String> fields) {
    //    Uri.Builder builder = Uri.parse(BASE_URL).buildUpon();
    //    for (int i = 0; i < Math.min(QUERY_KEYS.length, fields.size()); i++) {
    //        builder.appendQueryParameter(QUERY_KEYS[i], fields.get(i));
    //    }
    //    String uri = builder.build().toString();
    //    new DBScraper(context, apiInterface).execute(uri);
    //}

    @Override
    public void simpleSearch(String lastName, String firstName, String major, String classYear) {

    }

    @Override public void advancedSearch(String lastName, String firstName, String userName,
        String campusPhone, String campusAddress, String homeAddress, String classYear,
        String facStaffOffice, String major, String concentration, String sgaPosition,
        String onHiatus) {

    }
}
