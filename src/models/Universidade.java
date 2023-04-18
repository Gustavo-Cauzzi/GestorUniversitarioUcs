package models;

import java.util.List;

public class Universidade {
    private Integer idUniversidade;
    private String desUniversidade;

    private List<MatriculaDisciplina> matriculaDisciplinas;
    private List<MatriculaCurriculo> matriculaCurriculos;
    private List<Curso> cursos;
    private List<Curriculo> curriculos;
    private List<Aluno> alunos;

    public Universidade(Integer idUniversidade, String desUniversidade, List<MatriculaDisciplina> matriculaDisciplinas, List<MatriculaCurriculo> matriculaCursos, List<Curso> cursos, List<Curriculo> curriculos, List<Aluno> alunos) {
        this.idUniversidade = idUniversidade;
        this.desUniversidade = desUniversidade;
        this.matriculaDisciplinas = matriculaDisciplinas;
        this.matriculaCurriculos = matriculaCursos;
        this.cursos = cursos;
        this.curriculos = curriculos;
        this.alunos = alunos;
    }

    public Universidade(Integer idUniversidade, String desUniversidade) {
        this.idUniversidade = idUniversidade;
        this.desUniversidade = desUniversidade;
    }

    public Universidade() {
        // Empty
    }

    public Integer getIdUniversidade() {
        return idUniversidade;
    }

    public void setIdUniversidade(Integer idUniversidade) {
        this.idUniversidade = idUniversidade;
    }

    public String getDesUniversidade() {
        return desUniversidade;
    }

    public void setDesUniversidade(String desUniversidade) {
        this.desUniversidade = desUniversidade;
    }

    public List<MatriculaDisciplina> getMatriculaDisciplinas() {
        return matriculaDisciplinas;
    }

    public void setMatriculaDisciplinas(List<MatriculaDisciplina> matriculaDisciplinas) {
        this.matriculaDisciplinas = matriculaDisciplinas;
    }

    public List<MatriculaCurriculo> getMatriculaCurriculos() {
        return matriculaCurriculos;
    }

    public void setMatriculaCurriculos(List<MatriculaCurriculo> matriculaCurriculos) {
        this.matriculaCurriculos = matriculaCurriculos;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Curriculo> getCurriculos() {
        return curriculos;
    }

    public void setCurriculos(List<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Universidade{\n" +
                "idUniversidade=" + idUniversidade +
                ",\n desUniversidade='" + desUniversidade + '\'' +
                ",\n matriculaDisciplinas=" + matriculaDisciplinas +
                ",\n matriculaCurriculos=" + matriculaCurriculos +
                ",\n cursos=" + cursos +
                ",\n curriculos=" + curriculos +
                ",\n alunos=" + alunos +
                '}';
    }
}
