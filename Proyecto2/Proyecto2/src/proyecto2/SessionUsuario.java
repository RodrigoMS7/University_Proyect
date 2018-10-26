/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.util.HashMap;

/**
 *
 * @author Escinf
 */
public class SessionUsuario {
    HashMap<String,Object> attributes;

    public SessionUsuario() {
        attributes = new HashMap();
    }
    
    public Object getAttribute(String attribute){
        return attributes.get(attribute);
    }
    
    public void setAttibute(String attribute, Object value){
        attributes.put(attribute, value);
    }
    
    public void removeAttribute(String attribute){
        attributes.remove(attribute);
    }
}
