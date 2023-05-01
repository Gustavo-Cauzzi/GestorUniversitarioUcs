package view;

import repository.AppRepository;
import view.base.Screen;

import java.util.List;
import java.util.Scanner;

public class ConsultasView implements Screen {

    private static ConsultasView instance;

    @Override
    public void invoke () {
        int action;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1 - Alunos");
            System.out.println("2 - Atividades complementares");
            System.out.println("3 - Currículos");
            System.out.println("4 - Cursos");
            System.out.println("5 - Disciplina");
            System.out.println("---------");
            System.out.println("0 - Sair");

            action = Integer.parseInt(scanner.nextLine());

            if (action == 1) {
                this.printList(AppRepository.getInstance().getAlunos());
            } else if (action == 2) {
                this.printList(AppRepository.getInstance().getAtividadeComplementares());
            } else if (action == 3) {
                this.printList(AppRepository.getInstance().getCurriculos());
            } else if (action == 4) {
                this.printList(AppRepository.getInstance().getCursos());
            } else if (action == 5) {
                this.printList(AppRepository.getInstance().getDisciplinas());
            } else if (action != 0) {
                System.out.println("Opção inválida");
            }
        } while (action != 0);
    }

    private <T> void printList(List<T> elements) {
        if (elements.isEmpty()) {
            System.out.println("Nanhum registro cadastrado.");
            return;
        }
        elements.forEach(System.out::println);
    }

    public static ConsultasView getInstance() {
        if (instance == null) {
            instance = new ConsultasView();
        }
        return instance;
    }

}
