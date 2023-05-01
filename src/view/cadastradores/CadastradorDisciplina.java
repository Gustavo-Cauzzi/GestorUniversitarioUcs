package view.cadastradores;

import models.Disciplina;
import repository.AppRepository;
import utils.Utils;
import view.relacionadores.RelacionadorMatriculaDisciplina;

import java.util.List;
import java.util.Scanner;

public class CadastradorDisciplina extends Cadastrador<Disciplina> {
    private static Cadastrador<Disciplina> instance;

    public CadastradorDisciplina(List<Disciplina> db) {
        super(db);
    }

    @Override
    protected Disciplina cadastrar() {
        Scanner sc = new Scanner(System.in);

        int codigo = Utils.findNextCodigo(AppRepository.getInstance().getDisciplinas(), Disciplina::getCodDisciplina);
        System.out.println("Digite o nome da disciplina:");
        String nome = sc.next();
        return new Disciplina(codigo, nome);
    }

    @Override
    protected void afterCadastro(Disciplina disciplina) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deseja cadastrar alunos dentro da discplina?");
        System.out.println("0 - Não");
        System.out.println("1 - Sim");
        int action = Integer.parseInt(scanner.nextLine());
        while (action == 1) {
            RelacionadorMatriculaDisciplina.getInstance().exec(null, disciplina.getCodDisciplina());
            System.out.println("Deseja cadastrar um novo aluno da discplina?");
            System.out.println("0 - Não");
            System.out.println("1 - Sim");
            action = Integer.parseInt(scanner.nextLine());
        }
    }

    public static Cadastrador<Disciplina> getInstance() {
        if (instance == null) {
            instance = new CadastradorDisciplina(AppRepository.getInstance().getDisciplinas());
        }
        return instance;
    }
}
