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
        } else if (("^".equals(orig.getProposicao()) || "v".equals(orig.getProposicao()))//se raiz AND ou OR
                && orig.getDir().equals(orig.getEsq())) {                    // e DIR = ESQ
            Arvore nova = new Arvore(orig.getEsq()); //gera equivalencia SIMPLIFICADA
            gerar(nova, reg);
        }

    }

    private void com() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.COM;             //define a regra a ser utilizada por este metodo
        if (("^".equals(orig.getProposicao()) || "v".equals(orig.getProposicao())) // se raiz da arvore é AND ou OR
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
        if ("^".equals(orig.getProposicao())) {                  //se raiz é AND                                    
            assocAND(orig, reg);
        } else if ("v".equals(orig.getProposicao())) {             //se a raiz é OR
            assocOR(orig, reg);
        }

    }

    private void dist() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.DIST;

        if ("^".equals(orig.getProposicao())) {                  //se raiz é AND                                    
            distAND(orig, reg);
        } else if ("v".equals(orig.getProposicao())) {             //analogo ao AND/OR porem com OR/AND
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
            if ("^".equals(orig.getProposicao())) {
                dmDisj(orig, reg);
            }
            if ("v".equals(orig.getProposicao())) {
                dmConj(orig, reg);
            }
        }

    }

    private void cond() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.COND;
        if ("->".equals(orig.getProposicao())) {
            Arvore nova = new Arvore(orig);
            nova.setProposicao("v");
            nova.getEsq().negarArvore();
            gerar(nova, reg);
        }
    }

    private void bicond() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.BICOND;
        if ("<->".equals(orig.getProposicao())) {
            bicondImplica(orig, reg);
            bicondDisjun(orig, reg);
        }
    }

    private void cp() {
        Arvore orig = GeradorDeEquivalencia.arvoreOriginal;
        Regra reg = Regra.CP;
        if ("->".equals(orig.getProposicao())) {
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
        if ("^".equals(orig.getProposicao()) && "->".equals(orig.getDir().getProposicao())) {
            Arvore nova = new Arvore(orig);
            nova.setProposicao("->");
            gerar(nova, reg);
        }
        if ("^".equals(orig.getProposicao()) && "->".equals(orig.getEsq().getProposicao())) {
            Arvore nova = new Arvore(orig);
            nova.setProposicao("->");
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
        if ("^".equals(orig.getDir().getProposicao()) // dir tambem
                && !orig.getEsq().raizEhOper()) {      // esq é variavel
            Arvore nova = new Arvore(orig.getDir());
            Arvore aux = new Arvore(orig);               // aplica rotação 
            aux.setDir(nova.getEsq());                   // a esquerda (igual AVL)
            nova.setEsq(aux);
            gerar(nova, reg);
        } else if ("^".equals(orig.getEsq().getProposicao()) // esq tambem
                && !orig.getDir().raizEhOper()) {       // dir é variavel
            Arvore nova = new Arvore(orig.getEsq());
            Arvore aux = new Arvore(orig);               // aplica rotação 
            aux.setEsq(nova.getDir());                   // a direita (igual AVL)
            nova.setDir(aux);
            gerar(nova, reg);
        }
    }

    private void assocOR(Arvore orig, Regra reg) {
        if ("v".equals(orig.getDir().getProposicao())
                && !orig.getEsq().raizEhOper()) {
            Arvore nova = new Arvore(orig.getDir());
            Arvore aux = new Arvore(orig);               // aplica rotação 
            aux.setDir(nova.getEsq());                   // a esquerda (igual AVL)
            nova.setEsq(aux);
            gerar(nova, reg);
        } else if ("v".equals(orig.getEsq().getProposicao())
                && !orig.getDir().raizEhOper()) {
            Arvore nova = new Arvore(orig.getEsq());
            Arvore aux = new Arvore(orig);               // aplica rotação 
            aux.setEsq(nova.getDir());                   // a direita (igual AVL)
            nova.setDir(aux);
            gerar(nova, reg);
        }
    }

    private void distAND(Arvore orig, Regra reg) {
        
        if ("v".equals(orig.getDir().getProposicao()) // dir OR
                && !orig.getEsq().raizEhOper()) {      // esq é variavel
            Arvore nova = new Arvore(orig);
            nova.setProposicao("v");
            nova.getEsq().setProposicao("^");  // realiza mutações para distributiva
            nova.getDir().setProposicao("^");
            nova.getEsq().setEsq(orig.getEsq());   //troca de ponteiros
            nova.getEsq().setDir(orig.getDir().getEsq()); // e distribuição 
            nova.getDir().setEsq(orig.getEsq());
            gerar(nova, reg);

        } else if ("v".equals(orig.getEsq().getProposicao()) // esq OR
                && !orig.getDir().raizEhOper()) {       // dir é variavel
            Arvore nova = new Arvore(orig);
            nova.setProposicao("v");
            nova.getEsq().setProposicao("^");  // realiza mutações para distributiva
            nova.getDir().setProposicao("^");
            nova.getDir().setDir(orig.getDir());   //troca de ponteiros
            nova.getDir().setEsq(orig.getEsq().getDir()); // e distribuição 
            nova.getEsq().setDir(orig.getDir());
            gerar(nova, reg);
        }

        if ("v".equals(orig.getDir().getProposicao()) // dir OR
                && "v".equals(orig.getEsq().getProposicao())) {      // esq é OR
            if (orig.getDir().getDir().equals(orig.getEsq().getDir())) {//DIR.DIR = ESQ.DIR
                Arvore nova = new Arvore(orig);
                nova.setProposicao("v");
                nova.getDir().setProposicao("^");
                nova.setEsq(orig.getDir().getDir());
                nova.getDir().setDir(orig.getEsq().getEsq());
                gerar(nova, reg);
            } else if (orig.getDir().getDir().equals(orig.getEsq().getEsq())) {//DIR.DIR = ESQ.ESQ
                Arvore nova = new Arvore(orig);
                nova.setProposicao("v");
                nova.getDir().setProposicao("^");
                nova.setEsq(orig.getDir().getDir());
                nova.getDir().setDir(orig.getEsq().getDir());
                gerar(nova, reg);
            }
            if (orig.getDir().getEsq().equals(orig.getEsq().getDir())) {//DIR.ESQ = ESQ.DIR
                Arvore nova = new Arvore(orig);
                nova.setProposicao("v");
                nova.getDir().setProposicao("^");
                nova.setEsq(orig.getDir().getEsq());
                nova.getDir().setEsq(orig.getEsq().getEsq());
                gerar(nova, reg);
            } else if (orig.getDir().getEsq().equals(orig.getEsq().getEsq())) {//DIR.ESQ = ESQ.ESQ
                Arvore nova = new Arvore(orig);
                nova.setProposicao("v");
                nova.getDir().setProposicao("^");
                nova.setEsq(orig.getDir().getEsq());
                nova.getDir().setEsq(orig.getEsq().getDir());
                gerar(nova, reg);
            }
        }
    }

    private void distOR(Arvore orig, Regra reg) {
        if ("^".equals(orig.getDir().getProposicao())
                && !orig.getEsq().raizEhOper()) {
            Arvore nova = new Arvore(orig);
            nova.setProposicao("^");
            nova.getEsq().setProposicao("v");  // realiza mutações para distributiva
            nova.getDir().setProposicao("v");
            nova.getEsq().setEsq(orig.getEsq());   //troca de ponteiros
            nova.getEsq().setDir(orig.getDir().getEsq()); // e distribuição 
            nova.getDir().setEsq(orig.getEsq());
            gerar(nova, reg);
        } else if ("^".equals(orig.getEsq().getProposicao())
                && !orig.getDir().raizEhOper()) {
            Arvore nova = new Arvore(orig);
            nova.setProposicao("^");
            nova.getEsq().setProposicao("v");  // realiza mutações para distributiva
            nova.getDir().setProposicao("v");
            nova.getDir().setDir(orig.getDir());   //troca de ponteiros
            nova.getDir().setEsq(orig.getEsq().getDir()); // e distribuição 
            nova.getEsq().setDir(orig.getDir());
            gerar(nova, reg);
        }

        if ("^".equals(orig.getDir().getProposicao()) // dir AND
                && "^".equals(orig.getEsq().getProposicao())) {      // esq é AND
            if (orig.getDir().getDir().equals(orig.getEsq().getDir())) {//DIR.DIR = ESQ.DIR
                Arvore nova = new Arvore(orig);
                nova.setProposicao("^");
                nova.getDir().setProposicao("v");
                nova.setEsq(orig.getDir().getDir());
                nova.getDir().setDir(orig.getEsq().getEsq());
                gerar(nova, reg);
            } else if (orig.getDir().getDir().equals(orig.getEsq().getEsq())) {//DIR.DIR = ESQ.ESQ
                Arvore nova = new Arvore(orig);
                nova.setProposicao("^");
                nova.getDir().setProposicao("v");
                nova.setEsq(orig.getDir().getDir());
                nova.getDir().setDir(orig.getEsq().getDir());
                gerar(nova, reg);
            }
            if (orig.getDir().getEsq().equals(orig.getEsq().getDir())) {//DIR.ESQ = ESQ.DIR
                Arvore nova = new Arvore(orig);
                nova.setProposicao("^");
                nova.getDir().setProposicao("v");
                nova.setEsq(orig.getDir().getEsq());
                nova.getDir().setEsq(orig.getEsq().getEsq());
                gerar(nova, reg);
            } else if (orig.getDir().getEsq().equals(orig.getEsq().getEsq())) {//DIR.ESQ = ESQ.ESQ
                Arvore nova = new Arvore(orig);
                nova.setProposicao("^");
                nova.getDir().setProposicao("v");
                nova.setEsq(orig.getDir().getEsq());
                nova.getDir().setEsq(orig.getEsq().getDir());
                gerar(nova, reg);
            }
        }
    }

    private void dmDisj(Arvore orig, Regra reg) {
        Arvore nova = new Arvore(orig);
        nova.setProposicao("v");
        nova.negarArvore();
        nova.getDir().negarArvore();
        nova.getEsq().negarArvore();
        gerar(nova, reg);
    }

    private void dmConj(Arvore orig, Regra reg) {
        Arvore nova = new Arvore(orig);
        nova.setProposicao("^");
        nova.negarArvore();
        nova.getDir().negarArvore();
        nova.getEsq().negarArvore();
        gerar(nova, reg);
    }

    private void bicondImplica(Arvore orig, Regra reg) {
        Arvore nova1 = new Arvore(orig);
        nova1.setProposicao("^");
        Arvore nova1Esq = new Arvore(orig);
        nova1Esq.setProposicao("->");
        nova1.setEsq(nova1Esq);
        Arvore nova1Dir = new Arvore(orig);
        nova1Dir.setProposicao("->");
        nova1Dir.setEsq(orig.getDir());
        nova1Dir.setDir(orig.getEsq());
        nova1.setDir(nova1Dir);
        gerar(nova1, reg);
    }

    private void bicondDisjun(Arvore orig, Regra reg) {
        Arvore nova2 = new Arvore(orig);
        nova2.setProposicao("v");
        Arvore nova2Esq = new Arvore(orig);
        nova2Esq.setProposicao("^");
        nova2.setEsq(nova2Esq);
        Arvore nova2Dir = new Arvore(orig);
        nova2Dir.setProposicao("^");
        nova2Dir.getDir().negarArvore();
        nova2Dir.getEsq().negarArvore();
        nova2.setDir(nova2Dir);
        gerar(nova2, reg);
    }

}
