package models;

public class AtividadeComplementar {
    private Integer codAtividade;
    private String desAtividade;
    private Float qtdHoras;

    public AtividadeComplementar(Integer codAtividade, String desAtividade, Float qtdHoras) {
        this.codAtividade = codAtividade;
        this.desAtividade = desAtividade;
        this.qtdHoras = qtdHoras;
    }

    public Integer getCodAtividade() {
        return codAtividade;
    }

    public void setCodAtividade(Integer codAtividade) {
        this.codAtividade = codAtividade;
    }

    public String getDesAtividade() {
        return desAtividade;
    }

    public void setDesAtividade(String desAtividade) {
        this.desAtividade = desAtividade;
    }

    public Float getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(Float qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

    @Override
    public String toString() {
        return "AtividadeComplementar{" +
                "codAtividade=" + codAtividade +
                ", desAtividade='" + desAtividade + '\'' +
                ", qtdHoras=" + qtdHoras +
                '}';
    }
}
