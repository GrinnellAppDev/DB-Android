package edu.grinnell.appdev.grinnelldirectory.interfaces;

/**
 * Interface for classes that execute simple and advanced search.
 */

public interface SearchCaller {

    void simpleSearch(
        String lastName,
        String firstName,
        String major,
        String classYear
    );

    void advancedSearch(
        String lastName,
        String firstName,
        String userName,
        String campusPhone,
        String campusAddress,
        String homeAddress,
        String classYear,
        String facStaffOffice,
        String major,
        String concentration,
        String sgaPosition,
        String onHiatus
    );

}
