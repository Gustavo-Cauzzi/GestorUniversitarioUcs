package view;

import view.cadastradores.*;
import view.base.Screen;

import java.util.Scanner;

public class CadastrosView implements Screen {

    private static CadastrosView instance;

    @Override
    public void invoke() {
        int action;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Selecione o que você quer cadastrar:");
            System.out.println("1 - Aluno");
            System.out.println("2 - Atividade complementar");
            System.out.println("3 - Currículo");
            System.out.println("4 - Curso");
            System.out.println("5 - Disciplina");
            System.out.println("----------------");
            System.out.println("0 - Sair");

            action = Integer.parseInt(scanner.nextLine());

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
            } else if (action != 0) {
                System.out.println("Opção inválida");
            }
        } while (action != 0);
    }

    public static CadastrosView getInstance() {
        if (instance == null) {
            instance = new CadastrosView();
        }
        return instance;
    }
}
