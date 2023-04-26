package cadastradores;

import models.Curso;
import models.Disciplina;
import repository.AppRepository;
import utils.Utils;

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

    public static Cadastrador<Disciplina> getInstance() {
        if (instance == null) {
            instance = new CadastradorDisciplina(AppRepository.getInstance().getDisciplinas());
        }
        return instance;
    }
}
