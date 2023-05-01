package view.relacionadores;

import models.Aluno;
import models.Disciplina;
import models.MatriculaDisciplina;
import repository.AppRepository;
import view.relacionadores.base.SimpleRelacionadorViewBase;

import java.util.List;
import java.util.Optional;

public class RelacionadorMatriculaDisciplina extends SimpleRelacionadorViewBase<MatriculaDisciplina> {
    public static RelacionadorMatriculaDisciplina instance;

    public RelacionadorMatriculaDisciplina(List<MatriculaDisciplina> db) {
        super(db);
    }

    @Override
    protected MatriculaDisciplina relacionar(final Integer codAluno, final Integer codDisciplina) {
        Optional<Aluno> aluno = super.askForEntity(Aluno.class, AppRepository.getInstance().getAlunos(), codAluno);
        Optional<Disciplina> disciplina = super.askForEntity(Disciplina.class, AppRepository.getInstance().getDisciplinas(), codDisciplina);

        if (aluno.isEmpty() || disciplina.isEmpty()) {
            System.err.println("Não foi possível relacionar aluno e currículo");
            return null;
        }

        return new MatriculaDisciplina(disciplina.get(), aluno.get());
    }

    public static RelacionadorMatriculaDisciplina getInstance() {
        if (instance == null) {
            instance = new RelacionadorMatriculaDisciplina(AppRepository.getInstance().getMatriculaDisciplinas());
        }
        return instance;
    }
}
