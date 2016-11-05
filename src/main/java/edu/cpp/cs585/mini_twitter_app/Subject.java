package edu.cpp.cs585.mini_twitter_app;

/**
 * Subject interface for Observer pattern.
 * @author delin
 *
 */

public interface Subject {

    public void attach(Observer observer);
    public void notifyObservers();

}
