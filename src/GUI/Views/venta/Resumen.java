package GUI.Views.venta;



import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import restManager.persistencia.Cocina;
import restManager.persistencia.Venta;
import restManager.persistencia.jpa.staticContent;
import restManager.resources.R;

public class Resumen extends javax.swing.JDialog{

    private Venta dia;
    private Date fechaFinal;
    

    public Resumen(JDialog parent, boolean modal, Venta dia, Date fechaFinal) {
        super(parent, modal);
        this.dia = dia;
        this.fechaFinal = fechaFinal;
        initComponents();
        CrearFrame();
        pack();
        setVisible(true);

    }

    private void CrearFrame() {
        String  hVentas,
                hGastos,
                cDate,
                nombreMenu = staticContent.cartaJPA.findCarta("Mnu-1").getNombreCarta();
        
         if (dia.getFecha().getDate() == fechaFinal.getDate() &&
                 dia.getFecha().getMonth() == fechaFinal.getMonth()) {
               cDate = R.DATE_FORMAT.format(dia.getFecha()) 
                       + "(" + nombreMenu + ")";
               hVentas = ("Ventas del dia " + cDate);
               hGastos = ("Gastos por productos del dia " + cDate);
               

            } else {
                cDate = R.DATE_FORMAT.format(dia.getFecha()) +
                        " al " + R.DATE_FORMAT.format(fechaFinal) + "(" + nombreMenu + ")";
                hVentas = ("Resumen de ventas del " + cDate);
                hGastos = ("Resumen de gastos del " + cDate);
            }

        
        jTabbedPane1.addTab("Resumen Total ", new Resumenes(dia,fechaFinal,hVentas,hGastos));
        List<Cocina> cocinas = staticContent.cocinaJPA.findCocinaEntities();
        for (int i = 0; i < cocinas.size(); i++) {
            jTabbedPane1.addTab(cocinas.get(i).getNombreCocina(), 
                    new Resumenes(dia, cocinas.get(i),fechaFinal,
                            "Ventas de " + cocinas.get(i).getNombreCocina()+ " "+cDate,
                            "Gastos por producto "+cocinas.get(i).getNombreCocina()+" "+ cDate ));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new org.edisoncor.gui.tabbedPane.TabbedPaneHeader();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Resumen Del Dia");
        setResizable(false);
        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.tabbedPane.TabbedPaneHeader jTabbedPane1;
    // End of variables declaration//GEN-END:variables

}
