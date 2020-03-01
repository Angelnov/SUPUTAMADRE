/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog8;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Angelau
 */
public class PROG8 {
    
    public static boolean checkpatron(String cadena, String patron){
        Pattern cdni =Pattern.compile(patron);
        Matcher mdni =cdni.matcher(cadena);
        if(mdni.matches()){
            return true;
        }
            return false;
    }

   
    public static void main(String[] args) {
        Document doc;
        Scanner sc = new Scanner(System.in);
        String prueba = "53180501Q, Jose, Castiñeira, +(34)986242201, 616197360, 986242201, 555555555, +5438888888888, PRUEBA@prueba.es";
        ArrayList<String> datos=new ArrayList<String>();
        TreeSet<String> telefonos=new TreeSet<String>(new Comparador());
        HashSet<String> email=new HashSet<String>();
        
        
        
        String[] partes=prueba.split(",");
        
        if(checkpatron(partes[0].trim(), "[XYZxyz]?([0-9]{7,8})[A-Z]")){
         datos.add(partes[0].trim());
         
         datos.add(partes[1].trim());
         
         datos.add(partes[2].trim());         
        }
        
        //TELEFONOS
        for(int i=3; i<partes.length;i++){
        if(checkpatron(partes[i].trim(),"\\+?\\(?[0-9]*\\)?([0-9]+)")){
          telefonos.add(partes[i].trim().replace("(","").replace(")","")); 
          
        }
       
    }System.out.print(telefonos);
        
        //CORREOS
        for(int i=3;i<partes.length;i++){
            if(checkpatron(partes[i].trim(),"[a-zA-Z]+\\@[a-zA-Z]+\\.[a-z]+")){
          email.add(partes[i].trim().toLowerCase()); 
          System.out.print(email);
        }
        }
        
        doc=DOMUtil.crearDOMVacio("Cliente");
        
        
        Element xdni = doc.createElement("id");
        Text textdni = doc.createTextNode(datos.get(0));
        xdni.appendChild(textdni);
        doc.getDocumentElement().appendChild(xdni);
        
        Element xapellidos = doc.createElement("Apellidos");
        Text textapellidos = doc.createTextNode(datos.get(2));
        xapellidos.appendChild(textapellidos);
        doc.getDocumentElement().appendChild(xapellidos);
        
        Element xnombre = doc.createElement("Nombre");
        Text textnombre = doc.createTextNode(datos.get(1));
        xnombre.appendChild(textnombre);
        doc.getDocumentElement().appendChild(xnombre);
        
        
        Element axtelefono = doc.createElement("Telefonos");
        
        for(String t: telefonos){
            
        Element xtelefono = doc.createElement("Telefono");
        Text texttelefono = doc.createTextNode(t);
        xtelefono.appendChild(texttelefono);
        axtelefono.appendChild(xtelefono);
        }
        axtelefono.setAttribute("total",Integer.toString(telefonos.size()));
        doc.getDocumentElement().appendChild(axtelefono);
        
        Element axmail = doc.createElement("Mails");
        
        for(String m: email){
            Element xmail = doc.createElement("Mail");
            Text textmail = doc.createTextNode(m);
            xmail.appendChild(textmail);
            axmail.appendChild(xmail);
        }
        doc.getDocumentElement().appendChild(axmail);
        
        
        System.out.println("Introduzca ruta");
                String ruta = sc.nextLine();
                DOMUtil.DOM2XML(doc,ruta);
                
                System.out.println("Archivo creado correctamente");
    }
    
}
