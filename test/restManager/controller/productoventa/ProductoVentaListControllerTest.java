/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.controller.productoventa;

import org.junit.Test;
import restManager.persistencia.models.PersonalDAO;
import restManager.resources.R;

/**
 *
 * @author Jorge
 */
public class ProductoVentaListControllerTest {
    
    public ProductoVentaListControllerTest() {
    }
    
@Test
    public void testInstance(){
        R.loggedUser = PersonalDAO.getInstance().find("admin");
        ProductoVentaListController instance = new ProductoVentaListController(null);
        
    }
}
