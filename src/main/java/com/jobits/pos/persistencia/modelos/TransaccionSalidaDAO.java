/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.persistencia.modelos;


import com.jobits.pos.persistencia.Transaccion;
import com.jobits.pos.persistencia.TransaccionEntrada;
import com.jobits.pos.persistencia.TransaccionSalida;
/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class TransaccionSalidaDAO extends AbstractModel<TransaccionSalida> {

    private static TransaccionSalidaDAO INSTANCE = null;

    private TransaccionSalidaDAO() {
        super(TransaccionSalida.class);
    }

    public static TransaccionSalidaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransaccionSalidaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    
   
    
}
