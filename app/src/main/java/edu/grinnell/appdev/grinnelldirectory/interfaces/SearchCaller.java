package edu.grinnell.appdev.grinnelldirectory.interfaces;

import edu.grinnell.appdev.grinnelldirectory.models.Query;

/**
 * Interface for classes that execute simple and advanced search.
 */

public interface SearchCaller {

    void search(Query query);

}
