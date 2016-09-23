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

    public List<Equivalencia> getLista() {
        return lista;
    }

    public static List<String> getExpOriginal() {
        return expOriginal;
    }

    public static Arvore getArvoreOriginal() {
        return arvoreOriginal;
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
            idConjDisj(orig, reg);
        } else if (("^".equals(orig.getInfo()) || "v".equals(orig.getInfo()))//se raiz AND ou OR
                && orig.getDir().equals(orig.getEsq())) {                    // e DIR = ESQ
            Arvore nova = new Arvore(orig.getEsq()); //gera equivalencia SIMPLIFICADA
            gerar(nova, reg);
        }

    }

    private void com() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.COM;             //define a regra a ser utilizada por este metodo
        if (("^".equals(orig.getInfo()) || "v".equals(orig.getInfo())) // se raiz da arvore é AND ou OR
                && !orig.getDir().equals(orig.getEsq())) { // e ela nao é espelhada
            Arvore nova = new Arvore(orig);
            if (orig.getEsq() != null) {
                nova.setDir(orig.getEsq());//realiza a comutação invertendo os filhos de lado
            }
            if (orig.getDir() != null) {
                nova.setEsq(orig.getDir());//realiza a comutação invertendo os filhos de lado
            }
            gerar(nova, reg);
        }
    }

    private void assoc() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.ASSOC;
        if ("^".equals(orig.getInfo())) {                  //se raiz é AND                                    
            assocAND(orig, reg);
        } else if ("v".equals(orig.getInfo())) {             //se a raiz é OR
            assocOR(orig, reg);
        }

    }

    private void dist() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.DIST;

        if ("^".equals(orig.getInfo())) {                  //se raiz é AND                                    
            distAND(orig, reg);
        } else if ("v".equals(orig.getInfo())) {             //analogo ao AND/OR porem com OR/AND
            distOR(orig, reg);
        }
    }

    private void dn() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.DN;

    }

    private void dm() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.DM;
        if (orig.isNegacao()) {
            if ("^".equals(orig.getInfo())) {
                dmDisj(orig, reg);
            }
            if ("v".equals(orig.getInfo())) {
                dmConj(orig, reg);
            }
        }

    }

    private void cond() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.COND;
        if ("->".equals(orig.getInfo())) {
            Arvore nova = new Arvore(orig);
            nova.setInfo("v");
            nova.getEsq().negarArvore();
            gerar(nova, reg);
        }
    }

    private void bicond() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.BICOND;
        if ("<->".equals(orig.getInfo())) {
            bicondImplica(orig, reg);
            bicoxndDisjun(orig, reg);
        }
    }

    private void cp() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.CP;
        if ("->".equals(orig.getInfo())) {
            Arvore nova = new Arvore(orig);
            nova.setDir(orig.getEsq());
            nova.setEsq(orig.getDir());
            nova.getEsq().negarArvore();
            nova.getDir().negarArvore();
            gerar(nova, reg);
        }
    }

    private void ei() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.EI;
        if ("^".equals(orig.getInfo()) && "->".equals(orig.getDir().getInfo())) {
            Arvore nova = new Arvore(orig);
            nova.setInfo("->");
            gerar(nova, reg);
        }
        if ("^".equals(orig.getInfo()) && "->".equals(orig.getEsq().getInfo())) {
            Arvore nova = new Arvore(orig);
            nova.setInfo("->");
            nova.setDir(orig.getEsq());
            nova.setEsq(orig.getDir());
            gerar(nova, reg);
        }
    }

    private void idConjDisj(Arvore orig, Regra reg) {
        Arvore novaE = new Arvore("^", false);//gera arvore AND
        novaE.setDir(orig);
        novaE.setEsq(orig);
        gerar(novaE, reg);
        Arvore novaOU = new Arvore("v", false);//gera Arvore OR
        novaOU.setDir(orig);
        novaOU.setEsq(orig);
        gerar(novaOU, reg);
    }

    private void assocAND(Arvore orig, Regra reg) {
        if ("^".equals(orig.getDir().getInfo()) // dir tambem
                && !orig.getEsq().raizEhOper()) {      // esq é variavel
            Arvore nova = new Arvore(orig.getDir());
            Arvore aux = new Arvore(orig);               // aplica rotação 
            aux.setDir(nova.getEsq());                   // a esquerda (igual AVL)
            nova.setEsq(aux);
            gerar(nova, reg);
        } else if ("^".equals(orig.getEsq().getInfo()) // esq tambem
                && !orig.getDir().raizEhOper()) {       // dir é variavel
            Arvore nova = new Arvore(orig.getEsq());
            Arvore aux = new Arvore(orig);               // aplica rotação 
            aux.setEsq(nova.getDir());                   // a direita (igual AVL)
            nova.setDir(aux);
            gerar(nova, reg);
        }
    }

    private void assocOR(Arvore orig, Regra reg) {
        if ("v".equals(orig.getDir().getInfo())
                && !orig.getEsq().raizEhOper()) {
            Arvore nova = new Arvore(orig.getDir());
            Arvore aux = new Arvore(orig);               // aplica rotação 
            aux.setDir(nova.getEsq());                   // a esquerda (igual AVL)
            nova.setEsq(aux);
            gerar(nova, reg);
        } else if ("v".equals(orig.getEsq().getInfo())
                && !orig.getDir().raizEhOper()) {
            Arvore nova = new Arvore(orig.getEsq());
            Arvore aux = new Arvore(orig);               // aplica rotação 
            aux.setEsq(nova.getDir());                   // a direita (igual AVL)
            nova.setDir(aux);
            gerar(nova, reg);
        }
    }

    private void distAND(Arvore orig, Regra reg) {
        
        if ("v".equals(orig.getDir().getInfo()) // dir OR
                && !orig.getEsq().raizEhOper()) {      // esq é variavel
            Arvore nova = new Arvore(orig);
            nova.setInfo("v");
            nova.getEsq().setInfo("^");  // realiza mutações para distributiva
            nova.getDir().setInfo("^");
            nova.getEsq().setEsq(orig.getEsq());   //troca de ponteiros
            nova.getEsq().setDir(orig.getDir().getEsq()); // e distribuição 
            nova.getDir().setEsq(orig.getEsq());
            gerar(nova, reg);

        } else if ("v".equals(orig.getEsq().getInfo()) // esq OR
                && !orig.getDir().raizEhOper()) {       // dir é variavel
            Arvore nova = new Arvore(orig);
            nova.setInfo("v");
            nova.getEsq().setInfo("^");  // realiza mutações para distributiva
            nova.getDir().setInfo("^");
            nova.getDir().setDir(orig.getDir());   //troca de ponteiros
            nova.getDir().setEsq(orig.getEsq().getDir()); // e distribuição 
            nova.getEsq().setDir(orig.getDir());
            gerar(nova, reg);
        }

        if ("v".equals(orig.getDir().getInfo()) // dir OR
                && "v".equals(orig.getEsq().getInfo())) {      // esq é OR
            if (orig.getDir().getDir().equals(orig.getEsq().getDir())) {//DIR.DIR = ESQ.DIR
                Arvore nova = new Arvore(orig);
                nova.setInfo("v");
                nova.getDir().setInfo("^");
                nova.setEsq(orig.getDir().getDir());
                nova.getDir().setDir(orig.getEsq().getEsq());
                gerar(nova, reg);
            } else if (orig.getDir().getDir().equals(orig.getEsq().getEsq())) {//DIR.DIR = ESQ.ESQ
                Arvore nova = new Arvore(orig);
                nova.setInfo("v");
                nova.getDir().setInfo("^");
                nova.setEsq(orig.getDir().getDir());
                nova.getDir().setDir(orig.getEsq().getDir());
                gerar(nova, reg);
            }
            if (orig.getDir().getEsq().equals(orig.getEsq().getDir())) {//DIR.ESQ = ESQ.DIR
                Arvore nova = new Arvore(orig);
                nova.setInfo("v");
                nova.getDir().setInfo("^");
                nova.setEsq(orig.getDir().getEsq());
                nova.getDir().setEsq(orig.getEsq().getEsq());
                gerar(nova, reg);
            } else if (orig.getDir().getEsq().equals(orig.getEsq().getEsq())) {//DIR.ESQ = ESQ.ESQ
                Arvore nova = new Arvore(orig);
                nova.setInfo("v");
                nova.getDir().setInfo("^");
                nova.setEsq(orig.getDir().getEsq());
                nova.getDir().setEsq(orig.getEsq().getDir());
                gerar(nova, reg);
            }
        }
    }

    private void distOR(Arvore orig, Regra reg) {
        if ("^".equals(orig.getDir().getInfo())
                && !orig.getEsq().raizEhOper()) {
            Arvore nova = new Arvore(orig);
            nova.setInfo("^");
            nova.getEsq().setInfo("v");  // realiza mutações para distributiva
            nova.getDir().setInfo("v");
            nova.getEsq().setEsq(orig.getEsq());   //troca de ponteiros
            nova.getEsq().setDir(orig.getDir().getEsq()); // e distribuição 
            nova.getDir().setEsq(orig.getEsq());
            gerar(nova, reg);
        } else if ("^".equals(orig.getEsq().getInfo())
                && !orig.getDir().raizEhOper()) {
            Arvore nova = new Arvore(orig);
            nova.setInfo("^");
            nova.getEsq().setInfo("v");  // realiza mutações para distributiva
            nova.getDir().setInfo("v");
            nova.getDir().setDir(orig.getDir());   //troca de ponteiros
            nova.getDir().setEsq(orig.getEsq().getDir()); // e distribuição 
            nova.getEsq().setDir(orig.getDir());
            gerar(nova, reg);
        }

        if ("^".equals(orig.getDir().getInfo()) // dir AND
                && "^".equals(orig.getEsq().getInfo())) {      // esq é AND
            if (orig.getDir().getDir().equals(orig.getEsq().getDir())) {//DIR.DIR = ESQ.DIR
                Arvore nova = new Arvore(orig);
                nova.setInfo("^");
                nova.getDir().setInfo("v");
                nova.setEsq(orig.getDir().getDir());
                nova.getDir().setDir(orig.getEsq().getEsq());
                gerar(nova, reg);
            } else if (orig.getDir().getDir().equals(orig.getEsq().getEsq())) {//DIR.DIR = ESQ.ESQ
                Arvore nova = new Arvore(orig);
                nova.setInfo("^");
                nova.getDir().setInfo("v");
                nova.setEsq(orig.getDir().getDir());
                nova.getDir().setDir(orig.getEsq().getDir());
                gerar(nova, reg);
            }
            if (orig.getDir().getEsq().equals(orig.getEsq().getDir())) {//DIR.ESQ = ESQ.DIR
                Arvore nova = new Arvore(orig);
                nova.setInfo("^");
                nova.getDir().setInfo("v");
                nova.setEsq(orig.getDir().getEsq());
                nova.getDir().setEsq(orig.getEsq().getEsq());
                gerar(nova, reg);
            } else if (orig.getDir().getEsq().equals(orig.getEsq().getEsq())) {//DIR.ESQ = ESQ.ESQ
                Arvore nova = new Arvore(orig);
                nova.setInfo("^");
                nova.getDir().setInfo("v");
                nova.setEsq(orig.getDir().getEsq());
                nova.getDir().setEsq(orig.getEsq().getDir());
                gerar(nova, reg);
            }
        }
    }

    private void dmDisj(Arvore orig, Regra reg) {
        Arvore nova = new Arvore(orig);
        nova.setInfo("v");
        nova.negarArvore();
        nova.getDir().negarArvore();
        nova.getEsq().negarArvore();
        gerar(nova, reg);
    }

    private void dmConj(Arvore orig, Regra reg) {
        Arvore nova = new Arvore(orig);
        nova.setInfo("^");
        nova.negarArvore();
        nova.getDir().negarArvore();
        nova.getEsq().negarArvore();
        gerar(nova, reg);
    }

    private void bicondImplica(Arvore orig, Regra reg) {
        Arvore nova1 = new Arvore(orig);
        nova1.setInfo("^");
        Arvore nova1Esq = new Arvore(orig);
        nova1Esq.setInfo("->");
        nova1.setEsq(nova1Esq);
        Arvore nova1Dir = new Arvore(orig);
        nova1Dir.setInfo("->");
        nova1Dir.setEsq(orig.getDir());
        nova1Dir.setDir(orig.getEsq());
        nova1.setDir(nova1Dir);
        gerar(nova1, reg);
    }

    private void bicoxndDisjun(Arvore orig, Regra reg) {
        Arvore nova2 = new Arvore(orig);
        nova2.setInfo("v");
        Arvore nova2Esq = new Arvore(orig);
        nova2Esq.setInfo("^");
        nova2.setEsq(nova2Esq);
        Arvore nova2Dir = new Arvore(orig);
        nova2Dir.setInfo("^");
        nova2Dir.getDir().negarArvore();
        nova2Dir.getEsq().negarArvore();
        nova2.setDir(nova2Dir);
        gerar(nova2, reg);
    }

}
