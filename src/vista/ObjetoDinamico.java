/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.List;


/**
 *
 * @author mnu24
 */
public class ObjetoDinamico {
    
    private String a = null;
    private String b = null;
    private String c = null;
    private String d = null;
    private String e = null;
    private String f = null;
    private String g = null;
    private String h = null;
    private String i = null;
    private String j = null;
    private String k = null;
    private String l = null;
    private String m = null;
    private String n = null;
    private String o = null;
    private String p = null;
    private String q = null;
    private String r = null;
    private String s = null;
    
    
    public ObjetoDinamico(List<String> columnas, List<String> registro){
        List<String> misColumnas = columnas;
        
        
        if(misColumnas.size()>=1){
            this.a = registro.get(0);
        }
        if(misColumnas.size()>1){
            this.b = registro.get(1);
        }
        if(misColumnas.size()>2){
            this.c = registro.get(2);
        }
        if(misColumnas.size()>3){
            this.d = registro.get(3);
        }
        if(misColumnas.size()>4){
            this.e = registro.get(4);
        }
        if(misColumnas.size()>5){
            this.f = registro.get(5);
        }
        if(misColumnas.size()>6){
            this.g = registro.get(6);
        }
        if(misColumnas.size()>7){
            this.h = registro.get(7);
        }
        if(misColumnas.size()>8){
            this.i = registro.get(8);
        }
        if(misColumnas.size()>9){
            this.j = registro.get(9);
        }
        if(misColumnas.size()>10){
            this.k = registro.get(10);
        }
        if(misColumnas.size()>11){
            this.l = registro.get(11);
        }
        if(misColumnas.size()>12){
            this.m = registro.get(12);
        }
        if(misColumnas.size()>13){
            this.n = registro.get(13);
        }
        if(misColumnas.size()>14){
            this.o = registro.get(14);
        }
        if(misColumnas.size()>15){
            this.p = registro.get(15);
        }
        if(misColumnas.size()>16){
            this.q = registro.get(16);
        }
        if(misColumnas.size()>17){
            this.r = registro.get(17);
        }
        if(misColumnas.size()>18){
            this.s = registro.get(18);
        }
        
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String j) {
        this.j = j;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
    
    public String getDato(int t){
        String dato = "";
        
        if(t==0){
            dato = this.a;
        }
        if(t==1){
            dato = this.b;
        }
        if(t==2){
            dato = this.c;
        }
        if(t==3){
            dato = this.d;
        }
        if(t==4){
            dato = this.e;
        }
        if(t==5){
            dato = this.f;
        }
        if(t==6){
            dato = this.g;
        }
        if(t==7){
            dato = this.h;
        }
        if(t==8){
            dato = this.i;
        }
        if(t==9){
            dato = this.j;
        }
        if(t==10){
            dato = this.k;
        }
        if(t==11){
            dato = this.l;
        }
        if(t==12){
            dato = this.m;
        }
        if(t==13){
            dato = this.n;
        }
        if(t==14){
            dato = this.o;
        }
        if(t==15){
            dato = this.p;
        }
        if(t==16){
            dato = this.q;
        }
        if(t==17){
            dato = this.r;
        }
        if(t==18){
            dato = this.s;
        }
        
        return dato;
    }
    
    
    

    @Override
    public String toString() {
        return (this.a + ","+ this.b + "," + this.c + ","+ this.d + "," + this.e + ","+ this.f + "," + this.g + ","+ this.h + "," + this.i + ","+ this.j + "," + this.k + ","+ this.l + "," + this.m + ","+ this.n + "," + this.o + "," + this.p + "," + this.q + "," + this.r + "," + this.s);
    }
    
    
    
}
