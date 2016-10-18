/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.arvoreLex;

import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class Arvore implements Cloneable {

    private boolean negacao = false;
    private String proposicao;
    private Arvore dir;
    private Arvore esq;

    public Arvore(List<String> exp, boolean negacao) {
        this.negacao = negacao;
        //this.insere(exp);        
        Arvore a = new Arvore(biimplicacao(exp, negacao));
        if (a.getDir() != null) {
            this.setDir(a.getDir());
        }
        if (a.getEsq() != null) {
            this.setEsq(a.getEsq());
        }
        this.setProposicao(a.getProposicao());
        this.negacao = a.isNegacao();
    }

    public Arvore(String info, boolean negacao) {
        this.negacao = negacao;
        this.proposicao = info;
    }

    public Arvore(Arvore arvore) {
        try {
            Arvore aux = arvore.clone();
            this.proposicao = aux.getProposicao();
            this.negacao = aux.isNegacao();
            if (aux.getDir() != null) {
                this.dir = aux.getDir().clone();
            }
            if (aux.getEsq() != null) {
                this.esq = aux.getEsq().clone();
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Arvore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Arvore() {
    }

    @Override
    protected Arvore clone() throws CloneNotSupportedException {
        return (Arvore) super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDir(Arvore dir) {
        try {
            this.dir = dir.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Arvore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setEsq(Arvore esq) {
        try {
            this.esq = esq.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Arvore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setProposicao(String proposicao) {
        this.proposicao = proposicao;
    }

    //arvores com rais = ^ v <-> podem ser invertidas
    public boolean equals(Arvore a) {
        if (this.getProposicao().equals(a.getProposicao()) && this.isNegacao() == a.isNegacao()) {
            if ("^".equals(a.getProposicao()) || "v".equals(a.getProposicao()) || "<->".equals(a.getProposicao())) {
                return (a.getDir().equals(this.getDir()) && a.getEsq().equals(this.getEsq()))
                        || (a.getDir().equals(this.getEsq()) && a.getEsq().equals(this.getDir()));
            } else if (this.getDir() == null && a.getDir() == null) {
                if (this.getEsq() == null && a.getEsq() == null) {
                    return true;
                }
                if (this.getEsq() == null || a.getEsq() == null) {
                    return false;
                }
                return this.getEsq().equals(a.getEsq());
            }
            if (this.getDir() == null || a.getDir() == null) {
                return false;
            }
            return this.getDir().equals(a.getDir());

        }
        return false;
    }

    //não deixa que arvores invertidas serem iguais
    public boolean equalsRestrito(Arvore a) {
        if (this.getProposicao().equals(a.getProposicao()) && this.isNegacao() == a.isNegacao()) {
            if ("<->".equals(a.getProposicao())) {
                return (a.getDir().equals(this.getDir()) && a.getEsq().equals(this.getEsq()))
                        || (a.getDir().equals(this.getEsq()) && a.getEsq().equals(this.getDir()));
            } else if (this.getDir() == null && a.getDir() == null) {
                if (this.getEsq() == null && a.getEsq() == null) {
                    return true;
                }
                if (this.getEsq() == null || a.getEsq() == null) {
                    return false;
                }
                return this.getEsq().equals(a.getEsq());
            }
            if (this.getDir() == null || a.getDir() == null) {
                return false;
            }
            return this.getDir().equals(a.getDir());

        }
        return false;
    }

    private Arvore biimplicacao(List<String> exp, boolean negacao) {
        Arvore a = implicacao(exp, negacao);
        if (exp.size() > 0 && "<->".equals(exp.get(0))) {
            Arvore aux = new Arvore(exp.get(0), negacao);
            aux.setEsq(new Arvore(a));
            exp.remove(0);
            aux.setDir(new Arvore(biimplicacao(exp, false)));
            return aux;
        }
        return a;
    }

    private Arvore implicacao(List<String> exp, boolean negacao) {
        Arvore a = disjuncao(exp, negacao);
        if (exp.size() > 0 && "->".equals(exp.get(0))) {
            Arvore aux = new Arvore(exp.get(0), negacao);
            aux.setEsq(new Arvore(a));
            exp.remove(0);
            aux.setDir(new Arvore(implicacao(exp, false)));
            return aux;
        }
        return a;
    }

    private Arvore disjuncao(List<String> exp, boolean negacao) {
        Arvore a = conjuncao(exp, negacao);
        if (exp.size() > 0 && "v".equals(exp.get(0))) {
            Arvore aux = new Arvore(exp.get(0), negacao);
            aux.setEsq(new Arvore(a));
            exp.remove(0);
            aux.setDir(new Arvore(disjuncao(exp, false)));
            return aux;
        }
        return a;
    }

    private Arvore conjuncao(List<String> exp, boolean negacao) {
        Arvore a = termo(exp, negacao);
        if (exp.size() > 0 && "^".equals(exp.get(0))) {
            Arvore aux = new Arvore(exp.get(0), negacao);
            aux.setEsq(new Arvore(a));
            exp.remove(0);
            aux.setDir(new Arvore(conjuncao(exp, false)));
            return aux;
        }
        return a;
    }

    private Arvore termo(List<String> exp, boolean negacao) {
        boolean negLocal = negacao;
        if (exp.get(0).contains("~")) {
            if (ehNegacao(exp.get(0))) {
                negLocal = !negLocal;
            }
            exp.remove(exp.get(0));
        }
        if ("(".equals(exp.get(0))) {
            exp.remove(0);
            Arvore a = new Arvore(biimplicacao(exp, false));
            if (negLocal) {
                a.negarArvore();
            }
            exp.remove(0);
            return a;
        }
        return variavel(exp, negLocal);
    }

    private Arvore variavel(List<String> exp, boolean negacao) {
        Arvore a = new Arvore(exp.get(0), negacao);
        exp.remove(0);
        return a;
    }

    private boolean ehOper(String token) {
        if ("^".equals(token) || "v".equals(token)
                || "<->".equals(token) || "->".equals(token)) {
            return true;
        }
        return false;
    }

    public boolean raizEhOper() {
        if ("^".equals(this.proposicao) || "v".equals(this.proposicao)
                || "<->".equals(this.proposicao) || "->".equals(this.proposicao)) {
            return true;

        }
        return false;
    }

    private boolean ehNegacao(String token) {
        boolean neg = false;
        while (!token.isEmpty() && token.indexOf("~") == 0) {
            //System.out.println("negou");
            token = token.substring(1, token.length());
            neg = !neg;
        }
        return neg;
    }

    public boolean isNegacao() {
        return this.negacao;
    }

    public String getProposicao() {
        return this.proposicao;
    }

    public Arvore getDir() {
        return this.dir;
    }

    public Arvore getEsq() {
        return this.esq;
    }

    public void imprime() {
        System.out.print("\nPre Ordem: ");
        this.percorrerPreOrder();
        System.out.print("\nIn Ordem: ");
        this.percorrerInOrder();
        System.out.print("\nPos Ordem: ");
        this.percorrerPosOrder();
        System.out.println("");

    }

    public void percorrerInOrder() {
        //System.out.print(this.toString());
        if (this.proposicao == null) {
            return;
        }

        if (this.esq != null) {
            this.esq.percorrerInOrder();
        }
        if (this.negacao) {
            System.out.print("~");
        }
        System.out.print(this.proposicao);
        if (this.dir != null) {
            this.dir.percorrerInOrder();
        }
    }

    public void percorrerPreOrder() {
        if (this.proposicao == null) {
            return;
        }
        if (this.negacao) {
            System.out.print("~");
        }
        System.out.print(this.proposicao);
        if (this.esq != null) {
            this.esq.percorrerPreOrder();
        }

        if (this.dir != null) {
            this.dir.percorrerPreOrder();
        }
    }

    public void percorrerPosOrder() {
        if (this.proposicao == null) {
            return;
        }

        if (this.esq != null) {
            this.esq.percorrerPosOrder();
        }

        if (this.dir != null) {
            this.dir.percorrerPosOrder();
        }

        if (this.negacao) {
            System.out.print("~");
        }
        System.out.print(this.proposicao);
    }

    private String getEXP() {
        String exp = "";
        if (this.negacao) {
            exp = exp + " ~";
            //System.out.println("~");
        }
//        if (this.proposicao == null) {
//            return null;
//        }else{
//            System.out.println(this.proposicao);
//        }

        if (ehOper(this.proposicao)) {
            exp = exp + "(";
        }

        if (this.esq != null) {
            exp = exp + this.esq.getEXP();
        }
        exp = exp + " " + this.proposicao;
        if (this.dir != null) {
            exp = exp + this.dir.getEXP();
        }
        if (ehOper(this.proposicao)) {
            exp = exp + ")";
        }
        return exp;

    }

    @Override
    public String toString() {
        return this.getEXP(); //To change body of generated methods, choose Tools | Templates.
    }

    public void negarArvore() {
        this.negacao = !this.negacao;
    }

}
