package edu.grinnell.appdev.grinnelldirectory.interfaces;

import edu.grinnell.appdev.grinnelldirectory.models.DBRespoonse;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import java.util.List;
import okhttp3.ResponseBody;

/**
 * Callback for searches.
 * Classes need to implement this interface in order to receive results/errors from searches.
 */

public interface DbSearchCallback {

    void onSuccess(DBRespoonse respoonse);

    void onServerError(int code, ResponseBody error);

    void onNetworkError(String errorMessage);

}
