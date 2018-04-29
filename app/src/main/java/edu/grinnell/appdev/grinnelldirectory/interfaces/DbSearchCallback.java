package edu.grinnell.appdev.grinnelldirectory.interfaces;

import edu.grinnell.appdev.grinnelldirectory.models.Person;
import java.util.List;

public interface DbSearchCallback {

    void onSuccess(List<Person> people);

    void onServerError(int code);

    void onNetworkError(String errorMessage);

}
