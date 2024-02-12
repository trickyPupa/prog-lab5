package technical.managers;

import necessary.Movie;

import java.util.Vector;

public class CollectionManager {
    private Vector<Movie> collection;

    public CollectionManager(){
        collection = new Vector<>();
    }

    public void add(Movie element){
        collection.add(element);
    }
}
