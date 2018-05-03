package calssdep;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yi je suis liyi
 */
public class Champ {
    private String attribut;
    private String type;    
    
    public Champ(String att, String type){
    	this.attribut = att;
    	this.type = type;
    
    }
    
    public String getAttribut(){
        return this.attribut;
    }
    
    public String getType() {
        return this.type;
    }
}
