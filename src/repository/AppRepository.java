package repository;

import dtos.UniversidadeDTO;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppRepository {
    private static AppRepository instance;
    private final List<UniversidadeDTO> universidades = new ArrayList<>();
    private final List<Aluno> alunos = new ArrayList<>();
    private final List<Curriculo> curriculos = new ArrayList<>();
    private final List<AtividadeComplementar> atividadeComplementars = new ArrayList<>();
    private final List<Disciplina> disciplinas = new ArrayList<>();
    private final List<MatriculaCurriculo> matriculaCurriculos = new ArrayList<>();
    private final List<MatriculaDisciplina> matriculaDisciplinas = new ArrayList<>();
    private final List<Curso> cursos = new ArrayList<>();

    public AppRepository() {
    }

    public void readFromUniversity (UniversidadeDTO universidade) {
        this.alunos.addAll(universidade.getAlunos());
        this.universidades.add(universidade);
        this.atividadeComplementars.addAll(universidade.getAlunos().stream().map(Aluno::getAtividadeComplementares).flatMap(List::stream).toList());
        this.matriculaCurriculos.addAll(universidade.getMatriculaCurriculos());
        this.cursos.addAll(universidade.getCursos());
        this.curriculos.addAll(universidade.getCurriculos());
        this.matriculaDisciplinas.addAll(universidade.getMatriculaDisciplinas());
        this.disciplinas.addAll(universidade.getCurriculos().stream().map(Curriculo::getLstDisciplinas).flatMap(List::stream).toList());
    }

    public List<UniversidadeDTO> getUniversidades() {
        return universidades;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Curriculo> getCurriculos() {
        return curriculos;
    }

    public List<AtividadeComplementar> getAtividadeComplementares() {
        return atividadeComplementars;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<MatriculaCurriculo> getMatriculaCurriculos() {
        return matriculaCurriculos;
    }

    public List<MatriculaDisciplina> getMatriculaDisciplinas() {
        return matriculaDisciplinas;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public static AppRepository getInstance() {
        if (instance == null) {
            instance = new AppRepository();
        }
        return instance;
    }
}
