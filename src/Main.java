import cadastradores.*;
import dtos.UniversidadeDTO;
import models.*;
import repository.AppRepository;
import utils.DataGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static utils.DataGenerator.getRandomOf;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        UniversidadeDTO universidade = generateUniversidade();
        AppRepository.getInstance().readFromUniversity(universidade);
        int action;

        do {
            System.out.println("Sistema de gestão universitária");
            System.out.println("1 - Consultar");
            System.out.println("2 - Cadastrar");
            System.out.println("-----------------");
            System.out.println("0 - Sair");

            action = sc.nextInt();

            if (action == 1) {
                invokeConsultas();
            } else if (action == 2) {
                invokeCadastros();
            } else {
                System.out.println("Opção inválida");
            }
        } while (action != 0);
    }

    private static void invokeConsultas () {
        int action;
        do {
            System.out.println("1 - Alunos");
            System.out.println("2 - Atividades complementares");
            System.out.println("3 - Currículos");
            System.out.println("4 - Cursos");
            System.out.println("5 - Disciplina");
            System.out.println("---------");
            System.out.println("0 - Sair");

            action = sc.nextInt();

            if (action == 1) {
                printList(AppRepository.getInstance().getAlunos());
            } else if (action == 2) {
                printList(AppRepository.getInstance().getAtividadeComplementares());
            } else if (action == 3) {
                printList(AppRepository.getInstance().getCurriculos());
            } else if (action == 4) {
                printList(AppRepository.getInstance().getCursos());
            } else if (action == 5) {
                printList(AppRepository.getInstance().getDisciplinas());
            } else {
                System.out.println("Opção inválida");
            }
        } while (action != 0);
    }

    private static <T> void printList (List<T> elements) {
        if (elements.isEmpty()) {
            System.out.println("Nanhum registro cadastrado.");
            return;
        }
        elements.forEach(System.out::println);
    }

    private static void invokeCadastros() {
        int action;
        do {
            System.out.println("Selecione o que você quer cadastrar:");
            System.out.println("1 - Aluno");
            System.out.println("2 - Atividade complementar");
            System.out.println("3 - Currículo");
            System.out.println("4 - Curso");
            System.out.println("5 - Disciplina");
            System.out.println("----------------");
            System.out.println("0 - Sair");

            action = sc.nextInt();

            if (action == 1) {
                CadastradorAluno.getInstance().exec();
            } else if (action == 2) {
                CadastradorAtividadeComplementar.getInstance().exec();
            } else if (action == 3) {
                CadastradorCurriculo.getInstance().exec();
            } else if (action == 4) {
                CadastradorCurso.getInstance().exec();
            } else if (action == 5) {
                CadastradorDisciplina.getInstance().exec();
            }else {
                System.out.println("Opção inválida");
            }
        } while(action != 0);
    }


    private static UniversidadeDTO generateUniversidade() {
        UniversidadeDTO uni = new UniversidadeDTO(1, "Universidade de Caxias do Sul");

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