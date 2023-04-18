package models;

public class Curso {
    public enum TIP_CURSO {
        GRADUACAO(1),
        POS(2),
        MESTRADO(3),
        EXTENSAO(4),
        TECNOLOGO(5);

        private final Integer id;

        TIP_CURSO(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
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
                ", tioCurso=" + tioCurso +
                '}';
    }
}
