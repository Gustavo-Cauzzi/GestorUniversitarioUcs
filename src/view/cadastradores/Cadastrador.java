package view.cadastradores;

import repository.RepositoryLink;

import java.util.List;

public abstract class Cadastrador<T> extends RepositoryLink<T> {
    public Cadastrador(List<T> db) {
        super(db);
    }

    public void exec () {
        T element = this.cadastrar();
        this.db.add(element);
        this.afterCadastro(element);
    }

    protected T cadastrar() {
        return null;
    }

    protected void afterCadastro(T t) {
        // Vazio
    }

}
