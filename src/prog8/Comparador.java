/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog8;

import java.util.Comparator;

/**
 *
 * @author Angelau
 */
public class Comparador implements Comparator<String> {
    private String telefono;
    
    
    public Comparador(){
        
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
   
   public int compare (String telefonoA, String telefonoB){
      if((telefonoA.length()==9)&&(telefonoB.length()==9)){
          return telefonoB.compareTo(telefonoA);
      }else{
       return telefonoB.compareTo(telefonoA);
   }
   }
   
   
   
}