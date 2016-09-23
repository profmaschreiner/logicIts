/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicits.arvoreLex;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class Arvore implements Cloneable {

    private boolean negacao = false;
    private String info;
    private Arvore dir;
    private Arvore esq;

    public Arvore(List<String> exp, boolean negacao) {
        this.negacao = negacao;
        this.insere(exp);
    }

    public Arvore(String info, boolean negacao) {
        this.negacao = negacao;
        this.info = info;
    }

    public Arvore(Arvore arvore) {
        try {
            Arvore aux = arvore.clone();
            this.info = aux.getInfo();
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

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean equals(Arvore a) {
        if (this.getInfo().equals(a.getInfo())) {
            if (this.getDir() == null && a.getDir() == null) {
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

    private void insere(List<String> exp) {
        expressao(exp, false);
    }

    private void expressao(List<String> exp, boolean negacao) {
        this.termo(exp, false);
        if (exp.size() > 0 && ehOper(exp.get(0))) {
            this.esq = new Arvore(this);
            this.negacao = negacao;
            this.info = exp.get(0);
            exp.remove(0);
            dir = new Arvore(exp, false);
        } else if (negacao) {
            this.negacao = !this.negacao;
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
        if ("^".equals(token) || "v".equals(token)
                || "<->".equals(token) || "->".equals(token)) {
            return true;
        }
        return false;
    }

    public boolean raizEhOper() {
        if ("^".equals(this.info) || "v".equals(this.info)
                || "<->".equals(this.info) || "->".equals(this.info)) {
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

    public String getInfo() {
        return this.info;
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

    private String getEXP() {
        String exp = "";
        if (this.negacao) {
            exp = exp + " ~ ";
            //System.out.println("~");
        }
//        if (this.info == null) {
//            return null;
//        }else{
//            System.out.println(this.info);
//        }

        if (ehOper(this.info)) {
            exp = exp + " (";
        }

        if (this.esq != null) {
            exp = exp + this.esq.getEXP();
        }
        exp = exp + this.info + " ";
        if (this.dir != null) {
            exp = exp + this.dir.getEXP();
        }
        if (ehOper(this.info)) {
            exp = exp + ") ";
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
