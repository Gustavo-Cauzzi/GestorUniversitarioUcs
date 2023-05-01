package view.cadastradores;

import models.Curriculo;
import repository.AppRepository;
import utils.Utils;
import view.relacionadores.RelacionadorMatriculaCurriculo;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CadastradorCurriculo extends Cadastrador<Curriculo> {
    private static Cadastrador<Curriculo> instance;

    public CadastradorCurriculo(List<Curriculo> db) {
        super(db);
    }

    @Override
    protected Curriculo cadastrar() {
        Scanner sc = new Scanner(System.in);
        int codigo = Utils.findNextCodigo(AppRepository.getInstance().getCurriculos(), Curriculo::getCodCurriculo);
        System.out.println("Digite o nome do currículo");
        String nome = sc.next();
        return new Curriculo(codigo, nome, Collections.emptyList());
    }

    @Override
    protected void afterCadastro(Curriculo curriculo) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deseja cadastrar alunos dentro do currículo?");
        System.out.println("0 - Não");
        System.out.println("1 - Sim");
        int action = Integer.parseInt(scanner.nextLine());
        while (action == 1) {
            RelacionadorMatriculaCurriculo.getInstance().exec(null, curriculo.getCodCurriculo());
            System.out.println("Deseja cadastrar um novo aluno no currículo?");
            System.out.println("0 - Não");
            System.out.println("1 - Sim");
            action = Integer.parseInt(scanner.nextLine());
        }
    }

    public static Cadastrador<Curriculo> getInstance() {
        if (instance == null) {
            instance = new CadastradorCurriculo(AppRepository.getInstance().getCurriculos());
        }
        return instance;
    }
}
