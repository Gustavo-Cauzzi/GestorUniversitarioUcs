package view.relacionadores;

import models.Aluno;
import models.Curriculo;
import models.MatriculaCurriculo;
import repository.AppRepository;
import view.relacionadores.base.SimpleRelacionadorViewBase;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RelacionadorMatriculaCurriculo extends SimpleRelacionadorViewBase<MatriculaCurriculo> {
    public static RelacionadorMatriculaCurriculo instance;

    public RelacionadorMatriculaCurriculo(List<MatriculaCurriculo> db) {
        super(db);
    }

    /*@Override
    protected MatriculaCurriculo relacionar(Integer codAluno, Integer codCurriculo) {
        Optional<Object[]> partialMatriculaCurriculo = super.autoRelacionar(codAluno, codCurriculo, MatriculaCurriculo.class);
        System.out.println(partialMatriculaCurriculo);
        if (partialMatriculaCurriculo.isPresent()) {
            Object[] objects = partialMatriculaCurriculo.get();
            return new MatriculaCurriculo((Aluno) objects[0], (Curriculo) objects[1], false);
        }
        return null;
    }*/

    @Override
    protected MatriculaCurriculo relacionar(final Integer codAluno, final Integer codCurriculo) {
        Optional<Aluno> aluno = super.askForEntity(Aluno.class, AppRepository.getInstance().getAlunos(), codAluno);
        Optional<Curriculo> curriculo = super.askForEntity(Curriculo.class, AppRepository.getInstance().getCurriculos(), codCurriculo);

        if (aluno.isEmpty() || curriculo.isEmpty()) {
            System.err.println("Não foi possível relacionar aluno e currículo");
            return null;
        }

        System.out.println("Houve uma desistência? (s/n)");
        Scanner scanner = new Scanner(System.in);
        String awnser = scanner.nextLine();
        boolean desistencia = "s".equalsIgnoreCase(awnser);
        return new MatriculaCurriculo(aluno.get(), curriculo.get(), desistencia);
    }

    public static RelacionadorMatriculaCurriculo getInstance() {
        if (instance == null) {
            instance = new RelacionadorMatriculaCurriculo(AppRepository.getInstance().getMatriculaCurriculos());
        }
        return instance;
    }
}
