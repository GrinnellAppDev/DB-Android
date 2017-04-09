package edu.grinnell.appdev.grinnelldirectory.interfaces;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.models.Person;


public interface APICallerInterface {

    void onSearchSuccess(List<Person> people);

    void authenticateUserCallSuccess(boolean success, Person person);

    void onServerFailure(String fail_message);

    void onNetworkingError(String fail_message);
}
