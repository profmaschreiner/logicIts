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
        if (arvore.getDir() != null) {
            this.setDir(arvore.getDir());
        }
        if (arvore.getEsq() != null) {
            this.setEsq(arvore.getEsq());
        }
        this.setInfo(arvore.getInfo());
        this.negacao = arvore.isNegacao();
    }

    public void setDir(Arvore dir) {
        if (dir == null) {
            this.dir = null;
        } else if (this.dir == null) {
            this.dir = new Arvore(dir);
        } else {
            this.dir.setInfo(dir.getInfo());
            this.negacao = dir.isNegacao();
            if (dir.getDir() != null) {
                this.getDir().setDir(dir.getDir());
            } else {
                this.getDir().setDir(null);
            }
            if (dir.getEsq() != null) {
                this.getDir().setEsq(dir.getEsq());
            } else {
                this.getDir().setEsq(null);
            }
        }
    }

    public void setEsq(Arvore esq) {
        if (esq == null) {
            this.esq = null;
        } else if (this.esq == null) {
            this.esq = new Arvore(esq);
        } else {
            this.esq.setInfo(esq.getInfo());
            this.negacao = esq.isNegacao();
            if (esq.getDir() != null) {
                this.getEsq().setDir(esq.getDir());
            } else {
                this.getEsq().setDir(null);
            }
            if (esq.getEsq() != null) {
                this.getEsq().setEsq(esq.getEsq());
            } else {
                this.getEsq().setEsq(null);
            }
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
        System.out.print(this.toString());
//        if (this.info == null) {
//            return;
//        }
//
//        if (this.esq != null) {
//            this.esq.percorrerInOrder();
//        }
//        if (this.negacao) {
//            System.out.print("~");
//        }
//        System.out.print(this.info);
//        if (this.dir != null) {
//            this.dir.percorrerInOrder();
//        }
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
//        if (this.info == null) {
//            return null;
//        }
        if (ehOper(this.info)) {
            exp = " (";
        }
        if (this.negacao) {
            exp = exp + " ~ ";
        }
        if (this.esq != null) {
            exp = exp + this.esq.getEXP();
        }
        exp = exp + this.info+" ";
        if (this.dir != null) {
            exp = exp + this.dir.getEXP();
        }
        if (ehOper(this.info)) {
            exp = exp+ ") ";
        }
        return exp;

    }

    @Override
    public String toString() {
        return this.getEXP(); //To change body of generated methods, choose Tools | Templates.
    }

}
