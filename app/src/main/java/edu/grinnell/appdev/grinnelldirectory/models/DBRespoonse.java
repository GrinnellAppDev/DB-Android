package edu.grinnell.appdev.grinnelldirectory.models;

import java.util.List;

public class DBRespoonse {
    private String errMessage;
    private int numberOfPeople;
    private int currentPage1;
    private int maximumPage;
    private int status;
    private List<Person> content;

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getCurrentPage1() {
        return currentPage1;
    }

    public void setCurrentPage1(int currentPage1) {
        this.currentPage1 = currentPage1;
    }

    public int getMaximumPage() {
        return maximumPage;
    }

    public void setMaximumPage(int maximumPage) {
        this.maximumPage = maximumPage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Person> getContent() {
        return content;
    }

    public void setContent(List<Person> content) {
        this.content = content;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
