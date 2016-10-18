/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.momentos;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import logicits.equivalencia.Equivalencia;
import logicits.equivalencia.ListaRegras;

/**
 *
 * @author fabio
 */
public class QuestaoRegraLogica {

    private String questao = "";
    private ListaRegras lr = new ListaRegras();
    private RegraLogica questaoOBJ;

    public QuestaoRegraLogica(String exp) {
        this.questaoOBJ = new RegraLogica(exp);
        this.questao = this.questaoOBJ.toString();
        for (int i = 0; i < lr.getLista().size(); i++) {
            this.questao = this.questao + "\n" + i + " - " + lr.getLista().get(i);
        }
    }

    @Override
    public String toString() {
        return this.questao;
    }

    public String resposta(String resUsrSTR) {
        String retorno = "";
        int resUsr = Integer.parseInt(resUsrSTR);
        if (lr.getLista().get(resUsr).equals(lr.getRegra(this.questaoOBJ.getReg()))) {
            retorno = "\n\nSua Resposta está correta";
        } else {
            retorno = "\n\nResposta errada, a regra aplicada foi " 
                    + lr.getRegra(questaoOBJ.getReg());
            
        }
        List<Equivalencia> l = this.questaoOBJ.getEquivalencias();
        for (int i = 0; i < l.size(); i++) {
            if (lr.getLista().get(resUsr).equals(lr.getRegra(l.get(i).getRegra()))) {
                retorno = retorno 
                        + "\nAo aplicar " 
                        + lr.getRegra(l.get(i).getRegra()) 
                        + " temos " 
                        + l.get(i).getArvoreEqui();
            }
        }
        return retorno;
    }
}
