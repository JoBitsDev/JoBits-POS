/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.controller.areaventa;

import com.jobits.pos.domain.models.Area;
import com.jobits.pos.domain.models.Carta;
import java.awt.Container;
import java.util.List;

/**
 *
 * @author Home
 */
public interface AreaDetailService {

    //    @Override
    //    public void createUpdateInstance() {
    //        if (getView().validateData()) {
    //            switch (state) {
    //                case CREATING:
    //                    create(instance);
    //                    break;
    //                case EDITING:
    //
    //                    update(instance);
    //                    break;
    //            }
    //        } else {
    //            throw new ValidatingException();
    //        }
    //    }
    void constructView(Container parent);

    //    public AreaDetailController(Area instance, AbstractRepository<Area> model) {
    //        super(instance, AreaDAO.getInstance());
    //    }
    //
    //    public AreaDetailController(Window parent) {
    //        super(parent, AreaDAO.getInstance());
    //    }
    //
    //    public AreaDetailController(Area instance, Window parent) {
    //        super(instance, parent, AreaDAO.getInstance());
    //    }
    Area createNewInstance();

    List<Carta> getCartaList();

    //    @Override
    //    public AbstractDetailView<Area> getView() {
    //        return (AbstractDetailView<Area>) super.getView(); //To change body of generated methods, choose Tools | Templates.
    //    }
    boolean isCreatingMode();

    public void create(Area area);

    public void update(Area area);

}
