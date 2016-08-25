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
        Regra reg = Regra.ID;                                  //define a regra a ser utilizada por este metodo
        if (!orig.raizEhOper()) {//se a raiz é uma variavel
            Arvore novaE = new Arvore("&", false);//gera arvore AND
            novaE.setDir(orig);
            novaE.setEsq(orig);
            gerar(novaE, reg);
            Arvore novaOU = new Arvore("|", false);//gera Arvore OR
            novaOU.setDir(orig);
            novaOU.setEsq(orig);
            gerar(novaOU, reg);
        } else if (("&".equals(orig.getInfo()) || "|".equals(orig.getInfo()))//se raiz AND ou OR
                && orig.getDir().equals(orig.getEsq())) {                    // e DIR = ESQ
            Arvore nova = new Arvore(orig.getEsq()); //gera equivalencia SIMPLIFICADA
            gerar(nova, reg);
        }

    }

    private void com() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.COM;             //define a regra a ser utilizada por este metodo
        if ("&".equals(orig.getInfo()) || "|".equals(orig.getInfo())) { // se raiz da arvore é AND ou OR
            Arvore nova = new Arvore(orig);
            Arvore aux = new Arvore(nova.getDir());  //realiza a comutação invertendo os filhos
            nova.setDir(nova.getEsq());
            nova.setEsq(aux);
            gerar(nova, reg);
        }

    }

    private void assoc() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.ASSOC;
        if ("&".equals(orig.getInfo())) {                  //se raiz é AND                                    
            if ("&".equals(orig.getDir().getInfo()) // dir tambem
                    && !orig.getEsq().raizEhOper()) {      // esq é variavel
                Arvore nova = new Arvore(orig.getDir());
                Arvore aux = new Arvore(orig);               // aplica rotação 
                aux.setDir(nova.getEsq());                   // a esquerda (igual AVL)
                nova.setEsq(aux);
                gerar(nova, reg);
            } else if ("&".equals(orig.getEsq().getInfo()) // esq tambem
                    && !orig.getDir().raizEhOper()) {       // dir é variavel
                Arvore nova = new Arvore(orig.getEsq());
                Arvore aux = new Arvore(orig);               // aplica rotação 
                aux.setEsq(nova.getDir());                   // a direita (igual AVL)
                nova.setDir(aux);
                gerar(nova, reg);
            }
        } else if ("|".equals(orig.getInfo())) {             //analogo ao AND porem com OR
            if ("|".equals(orig.getDir().getInfo())
                    && !orig.getEsq().raizEhOper()) {
                Arvore nova = new Arvore(orig.getDir());
                Arvore aux = new Arvore(orig);               // aplica rotação 
                aux.setDir(nova.getEsq());                   // a esquerda (igual AVL)
                nova.setEsq(aux);
                gerar(nova, reg);
            } else if ("|".equals(orig.getEsq().getInfo())
                    && !orig.getDir().raizEhOper()) {
                Arvore nova = new Arvore(orig.getEsq());
                Arvore aux = new Arvore(orig);               // aplica rotação 
                aux.setEsq(nova.getDir());                   // a direita (igual AVL)
                nova.setDir(aux);
                gerar(nova, reg);
            }
        }

    }

    private void dist() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.DIST;

        if ("&".equals(orig.getInfo())) {                  //se raiz é AND                                    
            if ("|".equals(orig.getDir().getInfo()) // dir OR
                    && !orig.getEsq().raizEhOper()) {      // esq é variavel
                Arvore nova = new Arvore(orig);
                nova.setInfo("|");
                nova.getEsq().setInfo("&");  // realiza mutações para distributiva
                nova.getDir().setInfo("&");
                nova.getEsq().setEsq(orig.getEsq());   //troca de ponteiros
                nova.getEsq().setDir(orig.getDir().getEsq()); // e distribuição 
                nova.getDir().setEsq(orig.getEsq());
                gerar(nova, reg);

            } else if ("|".equals(orig.getEsq().getInfo()) // esq OR
                    && !orig.getDir().raizEhOper()) {       // dir é variavel
                Arvore nova = new Arvore(orig);
                nova.setInfo("|");
                nova.getEsq().setInfo("&");  // realiza mutações para distributiva
                nova.getDir().setInfo("&");
                nova.getDir().setDir(orig.getDir());   //troca de ponteiros
                nova.getDir().setEsq(orig.getEsq().getDir()); // e distribuição 
                nova.getEsq().setDir(orig.getDir());
                gerar(nova, reg);
            }
        } else if ("|".equals(orig.getInfo())) {             //analogo ao AND/OR porem com OR/AND
            if ("&".equals(orig.getDir().getInfo())
                    && !orig.getEsq().raizEhOper()) {
                Arvore nova = new Arvore(orig);
                nova.setInfo("&");
                nova.getEsq().setInfo("|");  // realiza mutações para distributiva
                nova.getDir().setInfo("|");
                nova.getEsq().setEsq(orig.getEsq());   //troca de ponteiros
                nova.getEsq().setDir(orig.getDir().getEsq()); // e distribuição 
                nova.getDir().setEsq(orig.getEsq());
                gerar(nova, reg);
            } else if ("&".equals(orig.getEsq().getInfo())
                    && !orig.getDir().raizEhOper()) {
                Arvore nova = new Arvore(orig);
                nova.setInfo("$");
                nova.getEsq().setInfo("|");  // realiza mutações para distributiva
                nova.getDir().setInfo("|");
                nova.getDir().setDir(orig.getDir());   //troca de ponteiros
                nova.getDir().setEsq(orig.getEsq().getDir()); // e distribuição 
                nova.getEsq().setDir(orig.getDir());
                gerar(nova, reg);
            }
        }
        if ("&".equals(orig.getInfo())) {                  //se raiz é AND                                    
            if ("|".equals(orig.getDir().getInfo()) // dir OR
                    && "|".equals(orig.getEsq().getInfo())) {      // esq é OR

            } else if ("|".equals(orig.getEsq().getInfo()) // esq OR
                    && "|".equals(orig.getDir().getInfo())) {       // dir é OR

            }
        } else if ("|".equals(orig.getInfo())) {             //analogo ao AND/OR porem com OR/AND
            if ("&".equals(orig.getDir().getInfo())
                    && "&".equals(orig.getDir().getInfo())) {

            } else if ("&".equals(orig.getEsq().getInfo())
                    && "&".equals(orig.getDir().getInfo())) {

            }
        }

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
