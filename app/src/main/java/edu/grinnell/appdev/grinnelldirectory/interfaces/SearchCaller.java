package edu.grinnell.appdev.grinnelldirectory.interfaces;

/**
 * Interface for classes that execute simple and advanced search.
 */

public interface SearchCaller {

    void simpleSearch(
        String lastName,
        String firstName,
        String major,
        String classYear,
        String cookie
    );

    void advancedSearch(
        String lastName,
        String firstName,
        String userName,
        String phone,
        String address,
        String classYear,
        String major,
        String sga,
        String cookie
    );

}
