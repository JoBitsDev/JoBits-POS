package GUI.Views.venta;


import GUI.Views.AbstractView;
import GUI.Views.View;
import java.util.Date;
import java.util.List;
import restManager.controller.AbstractDetailController;
import restManager.controller.venta.ResumenVentaController;
import restManager.persistencia.Cocina;
import restManager.persistencia.Venta;
import restManager.persistencia.jpa.staticContent;
import restManager.persistencia.models.CocinaDAO;
import restManager.resources.R;

public class Resumen extends AbstractView{

    private final Date fechaFinal;
    

    public Resumen(AbstractView parent, AbstractDetailController<Venta> controller,Date fechaFinal) {
        super(View.DialogType.FULL_SCREEN,controller, parent);
        this.fechaFinal = fechaFinal;
        initComponents();
        CrearFrame();
        pack();

    }

    private void CrearFrame() {
        String  hVentas,
                hGastos,
                cDate,
                nombreMenu = R.restName;
        
         if (getController().getInstance().getFecha().getDate() == fechaFinal.getDate() &&
                 getController().getInstance().getFecha().getMonth() == fechaFinal.getMonth()) {
               cDate = R.DATE_FORMAT.format(getController().getInstance().getFecha()) 
                       + "(" + nombreMenu + ")";
               hVentas = ("Ventas del dia " + cDate);
               hGastos = ("Gastos por productos del dia " + cDate);
               

            } else {
                cDate = R.DATE_FORMAT.format(getController().getInstance().getFecha()) +
                        " al " + R.DATE_FORMAT.format(fechaFinal) + "(" + nombreMenu + ")";
                hVentas = ("Resumen de ventas del " + cDate);
                hGastos = ("Resumen de gastos del " + cDate);
            }

        jTabbedPane1.addTab("Resumen Total ", new Resumenes(getController().getInstance(),fechaFinal,hVentas,hGastos));
        List<Cocina> cocinas = CocinaDAO.getInstance().findAll();
        for (int i = 0; i < cocinas.size(); i++) {
            jTabbedPane1.addTab(cocinas.get(i).getNombreCocina(), 
                    new Resumenes(getController().getInstance(), cocinas.get(i),fechaFinal,
                            "Ventas de " + cocinas.get(i).getNombreCocina()+ " "+cDate,
                            "Gastos por producto "+cocinas.get(i).getNombreCocina()+" "+ cDate ));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Resumen Del Dia");
        setResizable(false);
        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateView() {
        
    }

    @Override
    public ResumenVentaController getController() {
        return (ResumenVentaController)super.getController(); //To change body of generated methods, choose Tools | Templates.
    }
    
    


}
