package view;

import view.base.Screen;

import java.util.Scanner;

public class MainView implements Screen {

    private static MainView instance;

    @Override
    public void invoke() {
        int action;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("-------------------------------");
            System.out.println("Sistema de gestão universitária");
            System.out.println("-------------------------------");
            System.out.println("1 - Consultar");
            System.out.println("2 - Cadastrar");
            System.out.println("3 - Relacionar");
            System.out.println("4 - Relatórios");
            System.out.println("-----------------");
            System.out.println("0 - Sair");

            action = Integer.parseInt(scanner.nextLine());

            if (action == 1) {
                ConsultasView.getInstance().invoke();
            } else if (action == 2) {
                CadastrosView.getInstance().invoke();
            } else if (action == 3) {
                RelacionadorView.getInstance().invoke();
            } else if (action == 4) {
                DadosView.getInstance().invoke();
            } else if (action != 0) {
                System.out.println("Opção inválida");
            }
        } while (action != 0);
        scanner.close();
    }

    public static MainView getInstance() {
        if (instance == null) {
            instance = new MainView();
        }
        return instance;
    }
}
