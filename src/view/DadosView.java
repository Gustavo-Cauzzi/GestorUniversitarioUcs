package view;

import dtos.UniversidadeDTO;
import models.Curriculo;
import models.Disciplina;
import models.MatriculaCurriculo;
import models.MatriculaDisciplina;
import repository.AppRepository;
import view.base.Screen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DadosView implements Screen {
    private static DadosView instance;

    @Override
    public void invoke() {
        Scanner sc = new Scanner(System.in);

        Integer opt;
        do {
            System.out.println("Desja relatório acerca de qual informação?");
            System.out.println("1 - Top 10 disciplinas com mais alunos.");
            System.out.println("2 - Disciplinas de cada currículo.");
            System.out.println("-------------------------");
            System.out.println("0 - Sair");
            opt = Integer.valueOf(sc.next());

            UniversidadeDTO universidade = AppRepository.getInstance().getUniversidades().get(0);

            if (Integer.valueOf(1).equals(opt)) {
                topDisciplinas(universidade.getMatriculaDisciplinas());
            } else if (Integer.valueOf(2).equals(opt)) {
                System.out.println("Escolha um dos currículos disponíveis:");
                universidade.getCurriculos().forEach(c -> System.out.println("-" + c.getDesCurriculo()));

                String desCurriculo = sc.next();
                describeCurriculo(desCurriculo, universidade.getCurriculos(), universidade.getMatriculaCurriculos());
            } else if (!Integer.valueOf(0).equals(opt)) {
                System.out.println("Opção inválida!");
            }
        } while (!Integer.valueOf(0).equals(opt));
    }

    private  void describeCurriculo(String desCu, List<Curriculo> listCu, List<MatriculaCurriculo> listMatCu) {
        Curriculo curriculo = listCu.stream().filter(cur -> desCu.equals(cur.getDesCurriculo())).findAny().orElse(null);
        if (curriculo != null) {
            System.out.println("Disciplinas do Currículo:");
            curriculo.getLstDisciplinas().forEach(d -> System.out.println("-" + d.getDesDisciplina()));
            long count = listMatCu.stream().filter(m -> desCu.equals(m.getCurriculo().getDesCurriculo())).count();
            System.out.println("Número de matriculandos no Currículo " + desCu + ": " + count);
        } else {
            System.out.println("Não existe esse currículo.");
        }
    }

    private void topDisciplinas(List<MatriculaDisciplina> matDis) {
        Map<Disciplina, Integer> countMatDis = new HashMap<>();
        matDis.forEach(d -> countMatDis.put(d.getDisciplina(), countMatDis.get(d.getDisciplina()) != null ? countMatDis.get(d.getDisciplina()) + 1 : 1));
        List<Map.Entry<Disciplina, Integer>> list = countMatDis.entrySet().stream().toList();

        list = list.stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue())).collect(Collectors.toList());

        System.out.println("Top 10 disciplinas mais populares:");
        list.stream().limit(10).forEach(d -> System.out.println(d.getKey().getDesDisciplina() + " - " + d.getValue()));
    }

    public static DadosView getInstance() {
        if (instance == null) {
            instance = new DadosView();
        }
        return instance;
    }
}
