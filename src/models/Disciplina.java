package models;

import java.util.Objects;

public class Disciplina {
    private Integer codDisciplina;
    private String desDisciplina;

    public Disciplina(Integer codDisciplina, String desDisciplina) {
        this.codDisciplina = codDisciplina;
        this.desDisciplina = desDisciplina;
    }

    public Disciplina() {
        // Empty
    }

    public Integer getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(Integer codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public String getDesDisciplina() {
        return desDisciplina;
    }

    public void setDesDisciplina(String desDisciplina) {
        this.desDisciplina = desDisciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return Objects.equals(codDisciplina, that.codDisciplina) && Objects.equals(desDisciplina, that.desDisciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codDisciplina, desDisciplina);
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "codDisciplina=" + codDisciplina +
                ", desDisciplina='" + desDisciplina + '\'' +
                '}';
    }
}
