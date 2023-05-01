package view.cadastradores;

import models.AtividadeComplementar;
import repository.AppRepository;
import utils.Utils;

import java.util.List;
import java.util.Scanner;

public class CadastradorAtividadeComplementar extends Cadastrador<AtividadeComplementar> {
    private static Cadastrador<AtividadeComplementar> instance;


    public CadastradorAtividadeComplementar(List<AtividadeComplementar> db) {
        super(db);
    }

    @Override
    protected AtividadeComplementar cadastrar() {
        Scanner sc = new Scanner(System.in);
        int codigo = Utils.findNextCodigo(AppRepository.getInstance().getAtividadeComplementares(), AtividadeComplementar::getCodAtividade);
        System.out.println("Digite o nome da atividade complementar:");
        String nome = sc.next();
        System.out.println("Digite a quantidade de horas");
        Float qtdHoras = sc.nextFloat();
        return new AtividadeComplementar(codigo, nome, qtdHoras);
    }

    public static Cadastrador<AtividadeComplementar> getInstance() {
        if (instance == null) {
            instance = new CadastradorAtividadeComplementar(AppRepository.getInstance().getAtividadeComplementares());
        }
        return instance;
    }

}
