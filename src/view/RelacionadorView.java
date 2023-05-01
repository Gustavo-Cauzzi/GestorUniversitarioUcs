package view;

import view.base.Screen;
import view.relacionadores.RelacionadorMatriculaCurriculo;
import view.relacionadores.RelacionadorMatriculaDisciplina;

import java.util.Scanner;

public class RelacionadorView implements Screen {

    private static RelacionadorView instance;

    @Override
    public void invoke () {
        int action;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1 - Alunos - Curriculos");
            System.out.println("2 - Alunos - Disciplinas");
            System.out.println("---------");
            System.out.println("0 - Sair");

            action = Integer.parseInt(scanner.nextLine());

            if (action == 1) {
                RelacionadorMatriculaCurriculo.getInstance().exec();
            } else if (action == 2) {
                RelacionadorMatriculaDisciplina.getInstance().exec();
            } else if (action != 0) {
                System.out.println("Opção inválida");
            }
        } while (action != 0);
    }

    public static RelacionadorView getInstance() {
        if (instance == null) {
            instance = new RelacionadorView();
        }
        return instance;
    }
}
