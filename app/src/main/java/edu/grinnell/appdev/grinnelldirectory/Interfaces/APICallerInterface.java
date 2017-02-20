package edu.grinnell.appdev.grinnelldirectory.Interfaces;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Model.Person;

/**
 * Created by nicholasroberson on 2/15/17.
 */

public interface APICallerInterface {

    List<Person> simpleSearchCall();

    List<Person> advancedSearchCall();

    List<Person> authenticateUserCall();
}
