/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restManager.persistencia.models;

import restManager.persistencia.Transaccion;

/**
 * FirstDream
 * @author Jorge
 * 
 */
public class TransaccionDAO  extends AbstractModel<Transaccion>{

    public TransaccionDAO() {
        super(Transaccion.class);
    }
    
    public enum TIpoModelo {
        ENTRADA("Entrada"),
        SALIDA("Salida"),
        MERMA("Merma");
        
        private final String valor;

        private TIpoModelo(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }
   
        
        
    }

}
