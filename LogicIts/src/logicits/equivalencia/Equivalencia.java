/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.equivalencia;

import java.util.ArrayList;
import java.util.List;
import logicits.arvoreLex.Arvore;

/**
 *
 * @author fabio
 */
public class Equivalencia {

    private List<String> expOriginal = new ArrayList();
    private Arvore arvoreOriginal;
    private Arvore arvoreEqui;
    private Regra regra;

    public Equivalencia(List<String> expOriginal, Arvore arvoreOriginal,
            Arvore arvoreEqui, Regra regra) {
        this.expOriginal = expOriginal;
        this.arvoreOriginal = arvoreOriginal;
        this.arvoreEqui = arvoreEqui;
        this.regra = regra;
    }

    public List<String> getExpOriginal() {
        return expOriginal;
    }

    public Arvore getArvoreOriginal() {
        return arvoreOriginal;
    }

    public Arvore getArvoreEqui() {
        return arvoreEqui;
    }

    public Regra getRegra() {
        return regra;
    }

    @Override
    public String toString() {
        String equivalencia = "Expressão original " + this.arvoreOriginal
                + " Expressão equivalente " + this.arvoreEqui
                + " Através da regra " + new ListaRegras().getRegra(this.regra);

        return equivalencia; //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toStringSemRegra() {
        String equivalencia = "Expressão original " + this.arvoreOriginal
                + " Expressão equivalente " + this.arvoreEqui;

        return equivalencia; //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toStringEqui() {
        return this.arvoreEqui.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public void imprime() {
        System.out.println(this.toString());
//        System.out.print(this.regra);
//        this.getArvoreEqui().imprime();
//        System.out.println("");
    }

}
