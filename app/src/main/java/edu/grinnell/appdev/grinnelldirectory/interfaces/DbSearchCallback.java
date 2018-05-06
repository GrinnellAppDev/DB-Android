package edu.grinnell.appdev.grinnelldirectory.interfaces;

import edu.grinnell.appdev.grinnelldirectory.models.Person;
import java.util.List;
import okhttp3.ResponseBody;

public interface DbSearchCallback {

    void onSuccess(List<Person> people);

    void onServerError(int code, ResponseBody error);

    void onNetworkError(String errorMessage);

}
