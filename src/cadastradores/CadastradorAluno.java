package cadastradores;

import models.Aluno;
import repository.AppRepository;
import utils.Utils;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CadastradorAluno extends Cadastrador<Aluno> {
    public static Cadastrador<Aluno> instance;

    public CadastradorAluno(List<Aluno> db) {
        super(db);
    }


    @Override
    public Aluno cadastrar() {
        Scanner sc = new Scanner(System.in);
        int codigo = Utils.findNextCodigo(AppRepository.getInstance().getAlunos(), Aluno::getCodAluno);
        System.out.println("Digite o nome do aluno");
        String name = sc.next();
        return new Aluno(codigo, name, Collections.emptyList());
    }

    public static Cadastrador<Aluno> getInstance() {
        if (instance == null) {
            instance = new CadastradorAluno(AppRepository.getInstance().getAlunos());
        }
        return instance;
    }
}
