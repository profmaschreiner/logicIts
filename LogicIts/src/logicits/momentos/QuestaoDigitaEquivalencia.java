/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.momentos;

import logicits.arvoreLex.Arvore;
import logicits.equivalencia.ListaRegras;
import logicits.javacclist.Sintatico;

/**
 *
 * @author fabio
 */
public class QuestaoDigitaEquivalencia {

    DigitaEquivalencia questaoOBJ;
    private String questao = "";
    private boolean correta = false;

    public QuestaoDigitaEquivalencia(String exp) {

        this.questaoOBJ = new DigitaEquivalencia(exp);
        this.questao = questaoOBJ.toString();
        
        System.out.println(this.questaoOBJ.getResposta().size());
    }

    @Override
    public String toString() {
        return this.questao;
    }

    public String resposta(String resUsr) {
        Sintatico sintResUsr = new Sintatico(resUsr);
        String retorno = "";
        if (!"Erro".equals(sintResUsr.lista.get(0))) {
            Arvore respostaDoUsuario = new Arvore(sintResUsr.lista, false);
            for (int i = 0; i < this.questaoOBJ.getResposta().size(); i++) {
                if (this.questaoOBJ.getResposta().get(i).getArvoreEqui().equalsRestrito(respostaDoUsuario)) {
                    this.correta = true;
                }
            }

            if (correta) {
                retorno = "Parabéns, você aplicou a regra corretamente!";
                for (int i = 0; i < this.questaoOBJ.getResposta().size(); i++) {
                    if (!this.questaoOBJ.getResposta().get(i).getArvoreEqui().equalsRestrito(respostaDoUsuario)) {
                        retorno = retorno + "\nOutra resposta correta seria" + this.questaoOBJ.getResposta().get(i).getArvoreEqui();
                    }
                }
            } else {
                retorno = "Resposta errada, você não aplicou a regra corretamente! "
                        + "\na(s) resposta(s) correta(s) é(são): ";
                for (int i = 0; i < this.questaoOBJ.getResposta().size(); i++) {
                    retorno = retorno + "\n" + this.questaoOBJ.getResposta().get(i).getArvoreEqui();
                }
                for (int i = 0; i < this.questaoOBJ.getOutrasEquivalencias().size(); i++) {
                    if (this.questaoOBJ.getOutrasEquivalencias().get(i).getArvoreEqui().equalsRestrito(respostaDoUsuario)) {
                        retorno = retorno
                                + "\nSua resposta é equivalente através da regra "
                                + new ListaRegras().getRegra(
                                        this.questaoOBJ.getOutrasEquivalencias().get(i).getRegra()
                                );
                    }
                }
            }
        } else {
            this.correta = false;
            retorno = "Expressão inválida\n";
            for (int i = 0; i < sintResUsr.lista.size(); i++) {
                retorno = retorno + sintResUsr.lista.get(i) + "\n";
            }
        }
        return retorno;
    }

    public boolean isCorreta() {
        return correta;
    }

}
