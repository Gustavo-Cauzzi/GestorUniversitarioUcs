package repository;

import java.util.List;

public abstract class RepositoryLink<T> {
    protected List<T> db;

    public RepositoryLink(List<T> db) {
        this.db = db;
    }
}
