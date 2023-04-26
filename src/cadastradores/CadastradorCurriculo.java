package cadastradores;

import models.Curriculo;
import repository.AppRepository;
import utils.Utils;

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

    public static Cadastrador<Curriculo> getInstance() {
        if (instance == null) {
            instance = new CadastradorCurriculo(AppRepository.getInstance().getCurriculos());
        }
        return instance;
    }
}
