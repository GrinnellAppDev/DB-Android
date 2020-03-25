package edu.grinnell.appdev.grinnelldirectory.interfaces;

import android.os.Bundle;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.models.Person;

/**
 * Old search callbacks
 */

@Deprecated
public interface APICallerInterface {

    void onResume(Bundle savedInstanceState);

    void onSearchSuccess(List<Person> people);

    void authenticateUserCallSuccess(boolean success, Person person);

    void onServerFailure(String fail_message);

    void onNetworkingError(String fail_message);
}
