package models;

import java.util.List;

public class Aluno {
    private Integer codAluno;
    private String desNome;
    private List<AtividadeComplementar> atividadeComplementares;

    public Aluno(Integer codAluno, String desNome, List<AtividadeComplementar> atividadeComplementares) {
        this.codAluno = codAluno;
        this.desNome = desNome;
        this.atividadeComplementares = atividadeComplementares;
    }

    public Aluno() {
        // Empty
    }

    public Integer getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(Integer codAluno) {
        this.codAluno = codAluno;
    }

    public String getDesNome() {
        return desNome;
    }

    public void setDesNome(String desNome) {
        this.desNome = desNome;
    }

    public List<AtividadeComplementar> getAtividadeComplementares() {
        return atividadeComplementares;
    }

    public void setAtividadeComplementares(List<AtividadeComplementar> atividadeComplementares) {
        this.atividadeComplementares = atividadeComplementares;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "codAluno=" + codAluno +
                ", desNome='" + desNome + '\'' +
                ", atividadeComplementares=" + atividadeComplementares +
                '}';
    }
}
