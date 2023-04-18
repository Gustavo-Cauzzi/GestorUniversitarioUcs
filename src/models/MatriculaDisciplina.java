package models;

public class MatriculaDisciplina {
    private Disciplina disciplina;
    private Aluno aluno;

    public MatriculaDisciplina(Disciplina disciplina, Aluno aluno) {
        this.disciplina = disciplina;
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "MatriculaDisciplina{" +
                "disciplina=" + disciplina +
                ", aluno=" + aluno +
                '}';
    }
}
