package models;

public class AtividadeComplementar {
    private Integer codAtividade;
    private String desAtividade;

    public AtividadeComplementar(Integer codAtividade, String desAtividade) {
        this.codAtividade = codAtividade;
        this.desAtividade = desAtividade;
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

    @Override
    public String toString() {
        return "AtividadeComplementar{" +
                "codAtividade=" + codAtividade +
                ", desAtividade='" + desAtividade + '\'' +
                '}';
    }
}
