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

    boolean negacao = false;
    String info;
    Arvore dir;
    Arvore esq;

    public Arvore(List<String> exp, boolean negacao) {
        this.negacao = negacao;
        this.insere(exp,negacao);
    }

    public Arvore(String info, boolean negacao) {
        this.negacao = negacao;
        this.info = info;
    }

    private void insere(List<String> exp, boolean negacao) {
        expressao(exp, negacao);
    }

    private void expressao(List<String> exp, boolean negacao) {
        this.termo(exp);
        if (exp.size() > 0 && ehOper(exp.get(0))) {
            this.negacao = negacao;
            this.info = exp.get(0);
            exp.remove(0);
            dir = new Arvore(exp, false);
        }
    }

    private void termo(List<String> exp) {
        boolean negLocal = false;
        if (exp.size() > 0) {
            negLocal = ehNegacao(exp.get(0));
        }
        if (negLocal) {
            exp.remove(exp.get(0));
        }
        if ("(".equals(exp.get(0))) {
            exp.remove(0);
            if (exp.size() != (exp.indexOf(")")+1) ) {
                this.esq = new Arvore(exp, negLocal);
            }else{
                this.expressao(exp, negLocal);
            }
            exp.remove(0);

        } else {
            this.variavel(exp, negLocal);
        }
    }

    private void variavel(List<String> exp, boolean negacao) {
        if (exp.size() > 1 && ehOper(exp.get(1))) {
            esq = new Arvore(exp.get(0), negacao);
            exp.remove(0);
        } else if (exp.size() > 0) {
            this.negacao = negacao;
            this.info = exp.get(0);
            exp.remove(0);
        }
    }

    public boolean ehOper(String token) {
        if ("&".equals(token) || "|".equals(token)
                || "<->".equals(token) || "->".equals(token)) {
            return true;
        }
        return false;
    }

    public boolean ehNegacao(String token) {
        if ("~".equals(token)) {
            return true;
        }
        return false;
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
