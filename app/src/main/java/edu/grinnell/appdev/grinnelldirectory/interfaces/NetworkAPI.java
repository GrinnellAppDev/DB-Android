package edu.grinnell.appdev.grinnelldirectory.interfaces;

import java.util.List;

public interface NetworkAPI {

    // Just for Simple Search
    int FIRST_NAME_FIELD = 0;
    int LAST_NAME_FIELD = 1;
    int MAJOR_FIELD = 2;
    int CLASS_YEAR_FIELD = 3;
    // Additional constants for Advanced Search
    int CONCENTRATION_FIELD = 4;
    int SGA_FIELD = 5;
    int USERNAME_FIELD = 6;
    int CAMPUS_PHONE_FIELD = 7;
    int HIATUS_FIELD = 8;
    int HOME_ADDRESS_FIELD = 9;
    int FAC_STAFF_OFFICE_FIELD = 10;
    int CAMPUS_ADDRESS_FIELD = 11;
    int BUILDING_DORM_FIELD = 12;
    int POSITION_DESCRIPTION_FIELD = 13;

    void simpleSearch(List<String> fields);

    void advancedSearch(List<String> fields);

}
