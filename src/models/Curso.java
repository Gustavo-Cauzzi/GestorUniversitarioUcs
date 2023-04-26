package models;

import java.util.Arrays;
import java.util.Optional;

public class Curso {
    public enum TIP_CURSO {
        GRADUACAO(1, "Graduação"),
        POS(2, "Pós"),
        MESTRADO(3, "Mestrado"),
        EXTENSAO(4, "Extensão"),
        TECNOLOGO(5, "Tecnólogo");

        private final Integer id;
        private final String nome;

        TIP_CURSO(Integer id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Integer getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public static Optional<TIP_CURSO> of(int t) {
            return Arrays.stream(TIP_CURSO.values()).filter(o -> o.id.equals(t)).findFirst();
        }
    }

    private Integer codCurso;
    private String desCurso;
    private TIP_CURSO tioCurso;

    public Curso(Integer codCurso, String desCurso, TIP_CURSO tipCurso) {
        this.codCurso = codCurso;
        this.desCurso = desCurso;
        this.tioCurso = tipCurso;
    }

    public Integer getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(Integer codCurso) {
        this.codCurso = codCurso;
    }

    public String getDesCurso() {
        return desCurso;
    }

    public void setDesCurso(String desCurso) {
        this.desCurso = desCurso;
    }

    public TIP_CURSO getTioCurso() {
        return tioCurso;
    }

    public void setTioCurso(TIP_CURSO tioCurso) {
        this.tioCurso = tioCurso;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codCurso=" + codCurso +
                ", desCurso='" + desCurso + '\'' +
                ", tioCurso=" + tioCurso.getNome() +
                '}';
    }
}
