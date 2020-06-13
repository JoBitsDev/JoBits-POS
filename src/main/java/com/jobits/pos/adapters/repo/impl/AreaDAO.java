/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.adapters.repo.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import com.jobits.pos.domain.models.Almacen;
import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.InsumoAlmacen;
import com.jobits.pos.domain.models.Mesa;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class AreaDAO extends AbstractRepository<Area> {

    private static AreaDAO INSTANCE = null;

    private AreaDAO() {
        super(Area.class);
    }

    public static AreaDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AreaDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }
//
//    @Override
//    public void edit(Area area) {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            Area persistentArea = em.find(Area.class, area.getCodArea());
//            List<Mesa> mesaListOld = persistentArea.getMesaList();
//            List<Mesa> mesaListNew = area.getMesaList();
//            List<Mesa> attachedMesaListNew = new ArrayList<>();
//            for (Mesa mesaListNewMesaToAttach : mesaListNew) {
//                mesaListNewMesaToAttach = em.getReference(mesaListNewMesaToAttach.getClass(), mesaListNewMesaToAttach.getCodMesa());
//                attachedMesaListNew.add(mesaListNewMesaToAttach);
//            }
//            mesaListNew = attachedMesaListNew;
//            area.setMesaList(mesaListNew);
//            area = em.merge(area);
//            for (Mesa mesaListOldMesa : mesaListOld) {
//                if (!mesaListNew.contains(mesaListOldMesa)) {
//                    mesaListOldMesa.setAreacodArea(null);
//                    mesaListOldMesa = em.merge(mesaListOldMesa);
//                }
//            }
//            for (Mesa mesaListNewMesa : mesaListNew) {
//                if (!mesaListOld.contains(mesaListNewMesa)) {
//                    Area oldAreacodAreaOfMesaListNewMesa = mesaListNewMesa.getAreacodArea();
//                    mesaListNewMesa.setAreacodArea(area);
//                    mesaListNewMesa = em.merge(mesaListNewMesa);
//                    if (oldAreacodAreaOfMesaListNewMesa != null && !oldAreacodAreaOfMesaListNewMesa.equals(area)) {
//                        oldAreacodAreaOfMesaListNewMesa.getMesaList().remove(mesaListNewMesa);
//                        oldAreacodAreaOfMesaListNewMesa = em.merge(oldAreacodAreaOfMesaListNewMesa);
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            String msg = ex.getLocalizedMessage();
//            if (msg == null || msg.length() == 0) {
//                String id = area.getCodArea();
//            
//            }
//            throw ex;
//        } 
//        
//    }

}
