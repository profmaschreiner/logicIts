/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.arvoreLex;

import java.util.List;

/**
 *
 * @author fabio
 */
public class Arvore {

    private boolean negacao = false;
    private String info;
    private Arvore dir;
    private Arvore esq;

    public Arvore(List<String> exp, boolean negacao) {
        this.negacao = negacao;
        this.insere(exp, negacao);
    }

    public Arvore(String info, boolean negacao) {
        this.negacao = negacao;
        this.info = info;
    }

    public Arvore(Arvore arvore) {
        this.dir = arvore.getDir();
        this.esq = arvore.getEsq();
        this.info = arvore.getInfo();
        this.negacao = arvore.isNegacao();
    }

    public void setDir(Arvore dir) {
        this.dir = dir;
    }

    public void setEsq(Arvore esq) {
        this.esq = esq;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean equals(Arvore a) {
        if (this.getInfo() == a.getInfo() 
                && this.getDir().equals(a.getDir()) 
                && this.getEsq().equals(a.getEsq())) {
            return true;
        }
        return false;

    }

    private void insere(List<String> exp, boolean negacao) {
        expressao(exp, negacao);
    }

    private void expressao(List<String> exp, boolean negacao) {
        this.termo(exp, negacao);
        if (exp.size() > 0 && ehOper(exp.get(0))) {
            this.esq = new Arvore(this);
            this.negacao = negacao;
            this.info = exp.get(0);
            exp.remove(0);
            dir = new Arvore(exp, false);
        }
    }

    private void termo(List<String> exp, boolean negacao) {
        boolean negLocal = negacao;
        if (exp.size() > 0) {
            if (exp.get(0).contains("~")) {
                if (ehNegacao(exp.get(0))) {
                    negLocal = !negLocal;
                }
                exp.remove(exp.get(0));
            }
            if ("(".equals(exp.get(0))) {
                exp.remove(0);
                this.expressao(exp, negLocal);
                exp.remove(0);
            } else {
                this.variavel(exp, negLocal);
            }
        }
    }

    private void variavel(List<String> exp, boolean negacao) {
        this.negacao = negacao;
        this.info = exp.get(0);
        exp.remove(0);
    }

    private boolean ehOper(String token) {
        if ("&".equals(token) || "|".equals(token)
                || "<->".equals(token) || "->".equals(token)) {
            return true;
        }
        return false;
    }

    public boolean raizEhOper() {
        if ("&".equals(this.info) || "|".equals(this.info)
                || "<->".equals(this.info) || "->".equals(this.info)) {
            return true;
        }
        return false;
    }

    private boolean ehNegacao(String token) {
        boolean neg = false;
        while (!token.isEmpty() && token.indexOf("~") == 0) {
            System.out.println("negou");
            token = token.substring(1, token.length());
            neg = !neg;
        }
        return neg;
    }

    public boolean isNegacao() {
        return negacao;
    }

    public String getInfo() {
        return info;
    }

    public Arvore getDir() {
        return dir;
    }

    public Arvore getEsq() {
        return esq;
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
        if (this.info == null) {
            return;
        }

        if (this.esq != null) {
            this.esq.percorrerInOrder();
        }
        if (this.negacao) {
            System.out.print("~");
        }
        System.out.print(this.info);
        if (this.dir != null) {
            this.dir.percorrerInOrder();
        }
    }

    public void percorrerPreOrder() {
        if (this.info == null) {
            return;
        }
        if (this.negacao) {
            System.out.print("~");
        }
        System.out.print(this.info);
        if (this.esq != null) {
            this.esq.percorrerPreOrder();
        }

        if (this.dir != null) {
            this.dir.percorrerPreOrder();
        }
    }

    public void percorrerPosOrder() {
        if (this.info == null) {
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
        System.out.print(this.info);
    }

}
