package cadastradores;

import models.Curriculo;
import models.Curso;
import repository.AppRepository;
import utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CadastradorCurso extends Cadastrador<Curso> {
    private static Cadastrador<Curso> instance;

    public CadastradorCurso(List<Curso> db) {
        super(db);
    }

    @Override
    protected Curso cadastrar() {
        Scanner sc = new Scanner(System.in);

        int codigo = Utils.findNextCodigo(AppRepository.getInstance().getCursos(), Curso::getCodCurso);
        System.out.println("Nome do curso: ");
        String nome = sc.next();
        Optional<Curso.TIP_CURSO> tipoDoCurso = Optional.empty();
        do {
            System.out.println("Tipo de curso: ");
            Arrays.stream(Curso.TIP_CURSO.values()).forEach(tipCurso -> System.out.printf("%d - %s", tipCurso.getId(), tipCurso.getNome()));
            tipoDoCurso = Curso.TIP_CURSO.of(sc.nextInt());
            if (tipoDoCurso.isEmpty()) System.out.println("Opção inválida");
        } while (tipoDoCurso.isPresent());

        return new Curso(codigo, nome, tipoDoCurso.orElse(Curso.TIP_CURSO.GRADUACAO));
    }

    public static Cadastrador<Curso> getInstance() {
        if (instance == null) {
            instance = new CadastradorCurso(AppRepository.getInstance().getCursos());
        }
        return instance;
    }
}
