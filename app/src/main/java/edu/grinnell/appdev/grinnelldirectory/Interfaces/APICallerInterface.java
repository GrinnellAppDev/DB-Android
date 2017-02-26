package edu.grinnell.appdev.grinnelldirectory.Interfaces;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Model.Person;

/**
 * Created by nicholasroberson on 2/15/17.
 */

public interface APICallerInterface {

    void onSearchSuccess(List<Person> people);

    void authenticateUserCallSuccess(List<Person> people);

    void onServerFailure(String fail_message);

    void onNetworkingError(String fail_message);
}
