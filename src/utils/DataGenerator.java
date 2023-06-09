package utils;

import dtos.UniversidadeDTO;
import models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {
    private static int nextAlunoId = 0;
    private static int nextDisciplinaId = 0;
    private static final List<String> nomes = List.of(
        "Miguel", "Sophia", "Davi", "Alice", "Arthur", "Julia", "Pedro", "Isabella", "Gabriel", "Manuela", "Bernardo", "Laura", "Lucas", "Luiza", "Matheus", "Valentina", "Rafael", "Giovanna", "Heitor", "Maria Eduarda", "Enzo", "Helena", "Guilherme", "Beatriz", "Nicolas", "Maria Luiza", "Lorenzo", "Lara", "Gustavo", "Mariana", "Felipe", "Nicole", "Samuel", "Rafaela", "João Pedro", "Heloísa", "aniel", "Isadora", "Vitor", "Lívia", "Leonardo", "Maria Clara", "Henrique", "Ana Clara", "Theo", "Lorena", "Murilo", "Gabriela", "Eduardo", "Yasmin", "Pedro Henrique", "Isabelly", "Pietro", "Sarah", "Cauã", "Ana Julia", "Isaac", "Letícia", "Caio", "Ana Luiza", "Vinicius", "Melissa", "Benjamin", "Marina", "João", "Clara", "Lucca", "Cecília", "João Miguel", "Esther", "Bryan", "Emanuelly", "Joaquim", "Rebeca", "João Vitor", "Ana Beatriz", "Thiago", "Lavínia", "Antônio", "Vitória", "Davi Lucas", "Bianca", "Francisco", "Catarina", "Enzo Gabriel", "Larissa", "Bruno", "Maria Fernanda", "Emanuel", "Fernanda", "João Gabriel", "Amanda", "Ian", "Alícia", "Davi Luiz", "Carolina", "Rodrigo", "Agatha", "Otávio", "Gabrielly"
    );
    private static final List<String> nomesDeDisciplinas = List.of(
        "Álgebra", "Conjuntos", "Lógica matemática", "Teoria dos números", "Álgebra comutativa", "Geometria algébrica", "Análise", "Análise complexa", "Análise funcional", "Equações", "Equações diferenciais ordinárias", "Equações diferenciais parciais", "Geometria", "Topologia", "Geometria diferencial", "Topologia algébrica", "Teoria das singularidades", "Teoria das catástrofes", "Teoria das folheações", "Matemática aplicada", "Física matemática", "Análise numérica", "Matemática discreta", "combinatória", "processos estocásticos", "Teoremas de limite", "Fundamentos da estatística", "Regressão", "Correlação", "Análise de dados", "Teoria da computação", "Computabilidade", "Modelos de computação", "Linguagem formais", "Autômatos", "Análise de algoritmos", "complexidade de computação", "Lógicas", "semântica de programas", "Matemática da computação", "Matemática simbólica", "simulação", "Linguagens de programação", "Engenharia de software", "Banco de dados", "Sistemas de informação", "Sistema de computação", "Hardware", "Software básico", "Teleinformática", "Meio interestelar", "Nebulosa", "Galáxias", "Aglomerados de galáxias", "Quasares", "Cosmologia", "Física solar", "Movimento da Terra", "Sistema planetário", "Astronomia ótica", "Radioastronomia", "Física clássica", "Física quântica", "Mecânica", "Relatividade", "Gravitação", "Física estatística", "Termodinâmica", "Metrologia", "Eletricidade", "magnetismo", "Ótica", "Acústica", "Reologia", "Dinâmica dos fluidos", "Física nuclear", "raios cósmicos", "Física atômica", "Estrutura nuclear", "Desintegração nuclear", "radioatividade", "Reações nucleares", "Física molecular", "Estrutura eletrônica de átomos e moléculas", "Espectros atômicos", "Cinética", "propriedades físicas dos gases", "Física de plasmas", "Física da matéria condensada", "Cristalografia", "Supercondutividade", "Materiais dielétricos", "Magnetismo", "Química orgânica", "Estrutura química", "Estereoquímica", "Síntese orgânica", "Físico-química orgânica", "Fotoquímica", "Polímeros", "Coloides", "Química inorgânica", "Não-metais", "organometálicos", "Fotoquímica", "Físico-química", "Cinética química", "Catálise", "Eletroquímica", "Espectroscopia", "Química nuclear", "Radioquímica", "Química teórica", "Termodinâmica química", "Química analítica", "Separação", "Gravimetria", "Titimetria", "química ambiental", "Geologia", "Mineralogia", "Petrologia", "Geoquímica", "Geotectônica", "Geocronologia", "Cartografia geológica", "Metalogenia", "Hidrogeologia", "Sedimentologia", "Estratigrafia", "Geologia ambiental", "Geofísica", "Geomagnetismo", "Sismologia", "fluxo térmico", "Geotermia", "Sensoriamento remoto", "Aeronomia", "Gravimetria", "Meteorologia", "Meteorologia sinótica", "Meteorologia física", "atmosfera", "Climatologia", "Micrometeorologia", "Geodésia", "Fotogrametria", "Cartografia", "Geografia física", "Geomorfologia", "Climatologia geográfica", "Pedologia", "Hidrogeografia", "Geoecologia", "Oceanografia física", "Origem das massas de água", "Propriedades químicas da água do mar", "Genética", "Genética quantitativa", "Morfologia", "Citologia", "Embriologia", "Histologia", "Anatomia", "Anatomia humana", "Anatomia animal", "Fisiologia", "Neurofisiologia", "Fisiologia renal", "Cinesiologia", "Bioquímica", "Proteínas", "Lipídeos", "Biologia molecular", "Enzimologia", "Biofísica molecular", "Biofísica celular", "Farmacocinética", "Etnofarmacologia", "Toxicologia", "Imunoquímica", "Imunogenética", "Virologia", "Bacteriologia", "Micologia", "Protozooses", "Helmintoses", "Ectoparasitoses", "Ecossistemas", "Ecologia", "Oceanografia", "Paleobotânica", "Morfologia vegetal", "Anatomia vegetal", "Palinologia", "Fisiologia vegetal", "Fitogeografia", "Paleozoologia", "Comportamento animal", "Taxonomia", "Clínica Médica", "Angiologia", "Dermatologia", "Alergologia", "Cancerologia", "Hematologia", "Endocrinologia", "Neurologia", "Pediatria", "Cardiologia", "Gastroenterologia", "Pneumologia", "Nefrologia", "Reumatologia", "Ginecologia", "Obstetrícia", "Fisiatria", "Oftalmologia", "Ortopedia", "Cirurgia Plástica", "Cirurgia Pediátrica", "Neurocirurgia", "Ortodontia", "Odontopediatria", "Periodontia", "Endodontia", "Radiologia", "Farmacognosia", "Bromatologia", "Dietética", "Desnutrição", "Saúde Coletiva", "Saúde Pública", "Medicina Preventiva", "Ciência do Solo", "Morfologia", "Fitopatologia", "Fitotecnia", "Manejo", "Agrometeorologia", "Silvicultura", "Dendrologia", "Florestamento", "Reflorestamento", "Dendrometria", "Hidrologia", "Irrigação", "Drenagem", "Medicina Veterinária", "Aquicultura", "Maricultura", "Carcinocultura", "Piscicultura", "Teoria do Direito", "Teoria Geral do Direito", "Teoria Geral do Processo", "História do Direito", "Filosofia do Direito", "Direito Público", "Direito Tributário", "Direito Penal", "Direito Processual Penal", "Direito Processual Civil", "Direito Constitucional", "Direito Administrativo", "Direito Internacional Público", "Direito Privado", "Direito Civil", "Direito Comercial", "Direito do Trabalho", "Direito Internacional Privado", "Administração de Empresas", "Mercadologia", "Administração Pública", "História do Pensamento Econômico", "Contabilidade Nacional", "Economia Matemática", "Inflação", "Economia Internacional", "Capital Humano", "Economia Regional", "Paisagismo", "Demografia", "Teoria da Comunicação", "História da Filosofia", "Metafísica", "Lógica", "Ética", "Epistemologia", "História antiga", "História medieval", "História moderna", "História contemporânea", "História da América", "História dos Estados Unidos", "História do Brasil", "História das Religiões", "Geografia Humana", "Geografia Urbana", "Geografia Econômica", "Geografia Política", "Regionalização", "Psicologia Experimental", "Psicobiologia", "Psicologia Social", "Psicologia Cognitiva", "Psicologia das Religiões", "Filosofia da Educação", "História da Educação", "Sociologia da Educação", "Psicologia Educacional", "Política Educacional", "Currículo", "Educação Especial", "Ciências das Religiões - licenciatura", "Teoria Política", "Estado", "Governo", "Políticas Públicas", "Política Internacional", "Relações Internacionais", "Ciências das Religiões - bacharel", "Teologia Moral", "Teologia Sistemática", "Teologia Pastoral", "Teoria Linguística", "Análise Linguística", "Fisiologia da Linguagem", "Linguística Histórica", "Sociolinguística", "Dialetologia", "Psicolinguística", "Linguística Aplicada", "Língua Portuguesa", "Línguas Estrangeiras Modernas", "Línguas Clássicas", "Línguas Indígenas", "Teoria Literária", "Literatura Brasileira", "Literaturas Vernáculas", "Literaturas Estrangeiras Modernas", "Literaturas Clássicas", "Literatura Comparada", "Fundamentos e Crítica das Artes", "Teoria da Arte", "História da Arte", "Crítica da Arte", "Artes Plásticas", "Pintura", "Desenho", "Gravura", "Escultura", "Cerâmica", "Tecelagem", "Regência", "Instrumentação Musical", "Composição Musical", "Canto", "Execução da Dança", "Coreografia", "Dramaturgia", "Direção Teatral", "Cenografia", "Interpretação Teatral", "Ópera", "Cinema", "Produção de Filmes", "Roteiro", "Direção Cinematográficos", "Técnicas de Registro", "Processamento de Filmes", "Interpretação Cinematográfica", "Artes do Vídeo"
    );

    public static <T> T getRandomOf (List<T> list) {
        return list.get((int) Math.floor(Math.random() * list.size()));
    }

    public static List<Aluno> generateAlunos (int quantity) {
        nextAlunoId += quantity;
        return IntStream.rangeClosed(nextAlunoId, nextAlunoId + quantity).boxed().map(id -> {
            Aluno aluno = new Aluno();
            aluno.setCodAluno(id);
            aluno.setDesNome(DataGenerator.getRandomOf(nomes));
            aluno.setAtividadeComplementares(Collections.emptyList());
            return aluno;
        }).toList();
    }

    public static List<Disciplina> generateDisciplinas (int quantity) {
        nextDisciplinaId += quantity;
        return IntStream.rangeClosed(nextDisciplinaId, nextDisciplinaId + quantity).boxed().map(id -> {
            Disciplina disciplina = new Disciplina();
            disciplina.setCodDisciplina(id);
            disciplina.setDesDisciplina(getRandomOf(nomesDeDisciplinas));
            return disciplina;
        }).toList();
    }

    public static UniversidadeDTO generateUniversidade() {
        UniversidadeDTO uni = new UniversidadeDTO(1, "Universidade de Caxias do Sul");

        Curriculo r = new Curriculo(1, "R", DataGenerator.generateDisciplinas(40));
        Curriculo r2 = new Curriculo(2, "R2", DataGenerator.generateDisciplinas(20));

        Curso ciencia = new Curso(1, "Ciência da computação", Curso.TIP_CURSO.GRADUACAO);
        Curso ads = new Curso(2, "ANálise e desenvolvimento de sistemas", Curso.TIP_CURSO.TECNOLOGO);

        uni.setCursos(List.of(ciencia, ads));
        uni.setCurriculos(List.of(r, r2));

        List<Aluno> alunos = DataGenerator.generateAlunos(100);

        List<MatriculaCurriculo> matriculaCurriculos = new ArrayList<>();
        List<MatriculaDisciplina> matriculaDisciplinas = new ArrayList<>();

        alunos.forEach(aluno -> {
            Curriculo randomCurriculo = DataGenerator.getRandomOf(uni.getCurriculos());
            matriculaCurriculos.add(new MatriculaCurriculo(aluno, randomCurriculo, Math.random() * 100 < 5));
            IntStream.of(1, (int) (Math.random() * 15)).boxed().map((id) -> getRandomOf(randomCurriculo.getLstDisciplinas())).collect(Collectors.toSet()).forEach(disciplina -> matriculaDisciplinas.add(new MatriculaDisciplina(disciplina, aluno)));
        });

        uni.setMatriculaCurriculos(matriculaCurriculos);
        uni.setMatriculaDisciplinas(matriculaDisciplinas);

        uni.setAlunos(alunos);

        return uni;
    }
}
