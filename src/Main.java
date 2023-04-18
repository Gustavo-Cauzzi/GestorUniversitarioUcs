import models.*;
import utils.DataGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static utils.DataGenerator.getRandomOf;

public class Main {
    public static void main(String[] args) {

        Universidade universidade = generateUniversidade();

        System.out.println("Hello world! \n" +universidade);
    }

    private static Universidade generateUniversidade() {
        Universidade uni = new Universidade(1, "Universidade de Caxias do Sul");

        Curriculo r = new Curriculo(1, "R", DataGenerator.generateDisciplinas(40));
        Curriculo r2 = new Curriculo(2, "R2", DataGenerator.generateDisciplinas(20));

        Curso ciencia = new Curso(1, "Ciência da computação", Curso.TIP_CURSO.GRADUACAO);
        Curso ads = new Curso(2, "ANálise e desenvolvimento de sistemas", Curso.TIP_CURSO.TECNOLOGO);

        uni.setCursos(List.of(ciencia, ads));
        uni.setCurriculos(List.of(r, r2));

        List<Aluno> alunos = DataGenerator.generateAlunos(100);

        List<MatriculaCurriculo> matriculaCurriculos = new ArrayList<>();
        List<MatriculaDisciplina> matriculaDisciplinas = new ArrayList<>();

        alunos.forEach(aluno -> {
            Curriculo randomCurriculo = DataGenerator.getRandomOf(uni.getCurriculos());
            matriculaCurriculos.add(
                new MatriculaCurriculo(aluno, randomCurriculo, Math.random() * 100 < 5)
            );
            IntStream.of(1, (int) (Math.random() * 15))
                .boxed()
                .map((id) -> getRandomOf(randomCurriculo.getLstDisciplinas()))
                .collect(Collectors.toSet())
                .forEach(disciplina -> matriculaDisciplinas.add(new MatriculaDisciplina(disciplina, aluno)));
        });

        uni.setMatriculaCurriculos(matriculaCurriculos);
        uni.setMatriculaDisciplinas(matriculaDisciplinas);

        uni.setAlunos(alunos);

        return uni;
    }

}