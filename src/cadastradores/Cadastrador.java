package cadastradores;

import java.util.List;

public abstract class Cadastrador<T> {
    List<T> db;

    public void exec () {
        T element = this.cadastrar();
        this.db.add(element);
    };

    protected T cadastrar() {
        return null;
    };

    public Cadastrador(List<T> db) {
        this.db = db;
    }

    public static Cadastrador getInstance() {
        return null;
    }
}
