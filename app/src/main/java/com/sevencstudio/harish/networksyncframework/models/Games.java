package com.sevencstudio.harish.networksyncframework.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by harish on 11/01/16.
 */
public class Games extends RealmObject {
    private int count;
    private String next;
    private String previous;


    public RealmList<Results> getResults() {
        return results;
    }

    public void setResults(RealmList<Results> results) {
        this.results = results;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private RealmList<Results> results;
}
