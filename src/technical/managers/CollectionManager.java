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

    public String presentView(){
        if (collection.isEmpty()) return "Коллекция пуста";
        String res = "Текущая коллекция фильмов:\n";

        for (Movie i : collection){
            res = res + " - " + i.toString() + "\n";
        }

        return res;
    }

    @Override
    public String toString() {
        return "collection=" + collection +
                '}';
    }
}
