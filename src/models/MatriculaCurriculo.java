package models;

public class MatriculaCurriculo {
    private Aluno aluno;
    private Curriculo curriculo;
    private boolean indDesistencia;

    public MatriculaCurriculo(Aluno aluno, Curriculo curso, boolean indDesistencia) {
        this.aluno = aluno;
        this.curriculo = curso;
        this.indDesistencia = indDesistencia;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public boolean isIndDesistencia() {
        return indDesistencia;
    }

    public void setIndDesistencia(boolean indDesistencia) {
        this.indDesistencia = indDesistencia;
    }

    @Override
    public String toString() {
        return "MatriculaCurriculo{" +
                "aluno=" + aluno +
                ", curriculo=" + curriculo +
                ", indDesistencia=" + indDesistencia +
                '}';
    }
}
