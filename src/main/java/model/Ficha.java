/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public abstract class Ficha extends javax.swing.JFrame {

    Integer id;
    
    public Ficha (Integer id) {
     this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public abstract void consultar();
    public abstract void actualizar();
    
    
    
    
    
    
 }
