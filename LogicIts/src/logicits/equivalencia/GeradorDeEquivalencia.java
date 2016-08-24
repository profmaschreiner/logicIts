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
public class GeradorDeEquivalencia {

    private List<Equivalencia> lista;
    private static List<String> expOriginal;
    private static Arvore arvoreOriginal;

    public GeradorDeEquivalencia(List<String> expOriginal, Arvore arvoreOriginal) {
        GeradorDeEquivalencia.expOriginal = expOriginal;
        GeradorDeEquivalencia.arvoreOriginal = arvoreOriginal;
        lista = new ArrayList<>();
        geraEquivalencias();
    }

    public List<Equivalencia> getEquivalencias() {
        return this.lista;
    }

    private void gerar(Arvore novaArvore, Regra regra) {
        Equivalencia e = new Equivalencia(GeradorDeEquivalencia.expOriginal,
                GeradorDeEquivalencia.arvoreOriginal, novaArvore, regra);
        this.addEq(e);
    }

    private void addEq(Equivalencia e) {
        lista.add(e);
    }

    private void geraEquivalencias() {
        id();
        com();
        assoc();
        dist();
        dn();
        dm();
        cond();
        bicond();
        cp();
        ei();
    }

    private void id() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.ID;

    }

    private void com() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.COM;

    }

    private void assoc() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.ASSOC;
    }

    private void dist() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.DIST;

    }

    private void dn() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.DN;

    }

    private void dm() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.DM;

    }

    private void cond() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.COND;

    }

    private void bicond() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.BICOND;

    }

    private void cp() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.CP;
    }

    private void ei() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.EI;
    }

}
