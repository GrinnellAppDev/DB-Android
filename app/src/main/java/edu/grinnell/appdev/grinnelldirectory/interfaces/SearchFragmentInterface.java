package edu.grinnell.appdev.grinnelldirectory.interfaces;

/**
 * Makes sure that a fragment has a search method so that its parent activity can call it
 */

public interface SearchFragmentInterface {

    /**
     * Execute a simple or advanced search
     */
    void search();

}
