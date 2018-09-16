package edu.grinnell.appdev.grinnelldirectory.models;

import java.util.List;

/**
 * Wraps a list of Person objects.
 * This is required for Gson to deserialize a list of Person objects from json.
 * This class is only used for parsing local dummy data.
 */

public class Persons {

    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
