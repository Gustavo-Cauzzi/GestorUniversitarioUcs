package models;

import java.util.List;

public class Curriculo {
    private Integer codCurriculo;
    private String desCurriculo;
    private List<Disciplina> lstDisciplinas;

    public Curriculo(Integer codCurriculo, String desCurriculo, List<Disciplina> lstDisciplinas) {
        this.codCurriculo = codCurriculo;
        this.desCurriculo = desCurriculo;
        this.lstDisciplinas = lstDisciplinas;
    }

    public Integer getCodCurriculo() {
        return codCurriculo;
    }

    public void setCodCurriculo(Integer codCurriculo) {
        this.codCurriculo = codCurriculo;
    }

    public String getDesCurriculo() {
        return desCurriculo;
    }

    public void setDesCurriculo(String desCurriculo) {
        this.desCurriculo = desCurriculo;
    }

    public List<Disciplina> getLstDisciplinas() {
        return lstDisciplinas;
    }

    public void setLstDisciplinas(List<Disciplina> lstDisciplinas) {
        this.lstDisciplinas = lstDisciplinas;
    }

    @Override
    public String toString() {
        return "Curriculo{" +
                "codCurriculo=" + codCurriculo +
                ", desCurriculo='" + desCurriculo + '\'' +
                ", lstDisciplinas=" + lstDisciplinas +
                '}';
    }
}
