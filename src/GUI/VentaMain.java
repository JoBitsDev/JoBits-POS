/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.print.PrintException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import restManager.algoritmo.VentaB;
import restManager.persistencia.Cocina;

import restManager.persistencia.Control.VentaDAO;
import restManager.persistencia.IpvRegistro;
import restManager.persistencia.Mesa;
import restManager.persistencia.Orden;
import restManager.persistencia.Personal;
import restManager.persistencia.ProductoInsumo;
import restManager.persistencia.ProductovOrden;
import restManager.persistencia.Venta;
import restManager.persistencia.jpa.exceptions.IllegalOrphanException;
import restManager.persistencia.jpa.exceptions.NonexistentEntityException;
import restManager.persistencia.jpa.staticContent;
import restManager.printservice.Impresion;
import restManager.util.LoadingWindow;
import restManager.util.comun;

/**
 *
 * @author Jorge TODO aqui la manera en que se comunica con la BD la app esta
 * defectuosa
 */
public class VentaMain extends javax.swing.JDialog {

    //private List<Orden> ordenes = new ArrayList<>();
    private Venta v;
    private List<Personal> camarerosTrabajando = staticContent.personalJPA.findPersonalEntities();
    private Impresion imp = new Impresion(staticContent.cartaJPA.findCarta("Mnu-1"));
    private IPVEstado estadoIPVBarra;

    /**
     *
     * @param parent el frame del que deriva este dialog
     * @param modal si el dialogo esta todo el tiempo visible
     * @param ventas la instancia de venta
     *
     */
    private VentaMain(java.awt.Frame parent, boolean modal, Venta ventas) {
        super(parent, modal);
        initComponents();
        v = ventas;
        initDialog();
        UpdateDialog(v);

        setVisible(true);
        
    }

    private VentaMain(java.awt.Frame owner, boolean modal) {
        super(owner, modal);
        initComponents();

        try {
            v = new Venta(new Date());
            v.setOrdenList(new ArrayList<>());
            limpiarMesas();
            initDialog();
            UpdateDialog(v);
            staticContent.ventaJPA.create(v);

        } catch (NullPointerException e) {
            throw new AssertionError(e.getMessage(), e);
        } catch (Exception ex) {
            Logger.getLogger(VentaMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        setVisible(true);

    }

    /**
     * Constructor por defecto
     * <p>
     * Pre-Condiciones: - Se debe pasar como parametro una instancia de
     * {@link Venta} valida (con una fecha y un arreglo de ordenes no nulo) en
     * caso de no cumplir esta condicion el metodo se encargara de crear una
     * nueva instancia con la fecha actual del ordenador e inicializar el
     * arreglo de ordenes
     * <p>
     * @param owner
     * @param modal
     * @param ventas
     * @return VentaMain - una instancia valida de esta clase
     */
    public static VentaMain getInstance(java.awt.Container owner, boolean modal, Venta ventas) {
        if (ventas == null) {
            return new VentaMain((Frame) owner, modal);
        } else {
            return new VentaMain((Frame) owner, modal, ventas);
        }

    }

    /**
     * rellena el dialogo con la informacion de la venta pasada por parametro
     *
     * @param ventas la instancia de venta
     * @throws NullPointerException if the date is null or invalid
     */
    private void UpdateDialog(Venta ventas) throws NullPointerException {
        if (ventas.getFecha() == null) {
            //TODO: implementar un logger
            throw new AssertionError("The date of the sales is null");
        }
        if (ventas.getOrdenList() == null) {
            throw new AssertionError("ventas.getOrdenList() == null");
        }

        jDateChooser.setDate(ventas.getFecha());
        v = ventas;

        UpdateTableOrdenes(ventas);
        UpdateTableResumenVentas(ventas);
        UpdateTableResumenGastos(ventas);
        UpdateTableResumenVentasCamareros(ventas);
        UpdateTableResumenVentasCocinas(ventas);
        UpdateTablaIPVBarra(ventas);

        float ganancia = Float.parseFloat(jXLabelVALORVENTAS.getText().split(" ")[0])
                - Float.parseFloat(jXLabelVALORGASTOS.getText().split(" ")[0]);
        jXLabelValorGANANCIA.setText("" + String.format("%.2f", ganancia) + Main.moneda);

    }

    /**
     * Actualiza la tabla de las ordenes
     *
     * @param ventas
     */
    private void UpdateTableOrdenes(Venta ventas) {

        ArrayList[] rowData = new ArrayList[4];
        comun.initArray(rowData);

        List<Orden> ords;
        if (Main.logUser.getPuestoTrabajoList().get(0).getNivelAcceso() > 2) {
            ords = ventas.getOrdenList();
        } else {
        ords = VentaDAO.getOrdenesActivas(ventas);
        }
        
        for (Orden o : ords) {
            rowData[0].add(o.getCodOrden());
            rowData[2].add(o.getOrdenvalorMonetario());
            rowData[1].add(o.getMesacodMesa().getCodMesa());
            rowData[3].add(o.getHoraTerminada()!=null);
        }

        
        try {
            comun.UpdateTable(rowData, jXTableOrdenes);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Actualiza la tabla de los gastos de las ventas
     *
     * @param ventas
     */
    private void UpdateTableResumenVentas(Venta ventas) {

        comun.limpiarTabla(jXTableResumenVentas);
        VentaDAO.getResumenVentasOnTable(jXTableResumenVentas, v);
        VentaDAO.getValorTotalVentas(v);

        //actualizando el label de valor
        jXLabelValorTotalRecaudado.setText(
                VentaDAO.getValorTotalVentas(v) + Main.moneda);

        jXLabelVALORVENTAS.setText(jXLabelValorTotalRecaudado.getText());

    }

    /**
     * Actualiza la tabla de los gastos por insumo
     *
     * @param ventas
     */
    private void UpdateTableResumenGastos(Venta ventas) {

        comun.limpiarTabla(jXTableGastosPorProductos);
        VentaDAO.getResumenGastosOnTable(jXTableGastosPorProductos, v);

        //actualizando el label de gastos
        jXLabelValorGastosPorInsumo.setText(
                comun.calcularSumaTabla(jXTableGastosPorProductos, 4) + Main.moneda);
        jXLabelVALORGASTOS.setText(jXLabelValorGastosPorInsumo.getText());

    }

    /**
     * Actualiza la tabla de los camareros
     *
     * @param ventas
     */
    private void UpdateTableResumenVentasCamareros(Venta ventas) {
        comun.limpiarTabla(jXTableResumenCamareros);
        for (Personal x : camarerosTrabajando) {
            VentaDAO.getResumenVentasCamareroOnTable(jXTableResumenCamareros, v, x);
        }
    }

    /**
     * Actualiza la tabla de los resumenes por cocina
     *
     * @param ventas
     */
    private void UpdateTableResumenVentasCocinas(Venta ventas) {
        comun.limpiarTabla(jXTableResumenCocina);
        for (Cocina x : staticContent.cocinaJPA.findCocinaEntities()) {
            VentaDAO.getResumenVentasCocinaOnTable(jXTableResumenCocina, v, x);
        }
       
    }

    /**
     * Metodo para elminar un objeto de una tabla
     *
     * @param table - tabla que se va a evectuar la eliminacion
     * @param keyColumn - columna llave para eliminar (0 index)
     */
    private void eliminarItemFromTable(JTable table, int keyColumn) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            throw new NullPointerException("No hay ninguna fila seleccionada");
        }
        Object value = table.getValueAt(selectedRow, keyColumn);
        if (value == null) {
            throw new NullPointerException("El valor llave es nulo (Row:" + selectedRow + ")");
        }

        int s = JOptionPane.showConfirmDialog(null, "Desea eliminar el item " + value.toString());

        if (s == JOptionPane.YES_OPTION) {
            try {
                Orden ordenToDelete = staticContent.ordenJPA.findOrden(value.toString());

                for (ProductovOrden x : ordenToDelete.getProductovOrdenList()) {
                    staticContent.productovOrdenJpa.destroy(x.getProductovOrdenPK());
                }
                staticContent.ordenJPA.destroy(value.toString());
                JOptionPane.showMessageDialog(null, "Item eliminado exisosamente");
                refresh();
                return;
            } catch (IllegalOrphanException | NonexistentEntityException ex) {
                Logger.getLogger(VentaMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(null, "Operación cancelada");

    }

    /**
     * refresca el cache de la conexion haciendo posible la actualizacion en
     * tiempo real
     */
    private void clearConnCache() {
        staticContent.clearCache(Venta.class);
        staticContent.clearCache(Orden.class);
        staticContent.clearCache(ProductovOrden.class);
    }

    /**
     * refresca el dialogo
     */
    private void refresh() {
        clearConnCache();
        v = staticContent.ventaJPA.findVenta(v.getFecha());
        UpdateDialog(v);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRect1 = new org.edisoncor.gui.panel.PanelRect();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        tabbedPaneMain = new org.edisoncor.gui.tabbedPane.TabbedPaneHeader();
        panelRESUMEN = new org.edisoncor.gui.panel.PanelRect();
        panelINFOVENTAS = new org.edisoncor.gui.panel.PanelRoundTranslucido();
        jXLabelINFOTOTALVENTAS = new org.jdesktop.swingx.JXLabel();
        jXLabelVALORVENTAS = new org.jdesktop.swingx.JXLabel();
        panelINFOCGASTOS = new org.edisoncor.gui.panel.PanelRoundTranslucido();
        jXLabelINFOTOGASTOS = new org.jdesktop.swingx.JXLabel();
        jXLabelVALORGASTOS = new org.jdesktop.swingx.JXLabel();
        panelINFOCGANANCIA = new org.edisoncor.gui.panel.PanelRoundTranslucido();
        jXLabelINFOGANANCIA = new org.jdesktop.swingx.JXLabel();
        jXLabelValorGANANCIA = new org.jdesktop.swingx.JXLabel();
        panelINFOTRABAJADORES = new org.edisoncor.gui.panel.PanelRoundTranslucido();
        jXLabelINFORTRAB = new org.jdesktop.swingx.JXLabel();
        jXLabelValorTrab = new org.jdesktop.swingx.JXLabel();
        panelVentas = new org.edisoncor.gui.panel.PanelRect();
        jXLabelResumenVentas = new org.jdesktop.swingx.JXLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTableOrdenes = new org.jdesktop.swingx.JXTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTableResumenVentas = new org.jdesktop.swingx.JXTable();
        jXLabelOrden1 = new org.jdesktop.swingx.JXLabel();
        jXLabelTotalRecaudado = new org.jdesktop.swingx.JXLabel();
        jXLabelValorTotalRecaudado = new org.jdesktop.swingx.JXLabel();
        panelRect3 = new org.edisoncor.gui.panel.PanelRect();
        buttonEditOrden = new org.edisoncor.gui.button.ButtonTextDown();
        buttonDelOrden = new org.edisoncor.gui.button.ButtonTextDown();
        buttonAddOrden = new org.edisoncor.gui.button.ButtonTextDown();
        buttonCalcularCambio = new org.edisoncor.gui.button.ButtonTextDown();
        buttonImprimirZ = new org.edisoncor.gui.button.ButtonTextDown();
        panelResumenes = new org.edisoncor.gui.panel.PanelRect();
        jXLabelCamareros = new org.jdesktop.swingx.JXLabel();
        jXLabelCocinas = new org.jdesktop.swingx.JXLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jXTableResumenCamareros = new org.jdesktop.swingx.JXTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jXTableResumenCocina = new org.jdesktop.swingx.JXTable();
        buttonImprimirCocina = new org.edisoncor.gui.button.ButtonTextDown();
        buttonImprimirCamarero = new org.edisoncor.gui.button.ButtonTextDown();
        buttonImprimirTodoCamarero = new org.edisoncor.gui.button.ButtonTextDown();
        buttonImprimirTodoCocina = new org.edisoncor.gui.button.ButtonTextDown();
        buttonImprimirConsumoCasa = new org.edisoncor.gui.button.ButtonTextDown();
        panelGastoPorProducto = new org.edisoncor.gui.panel.PanelRect();
        jScrollPane3 = new javax.swing.JScrollPane();
        jXTableGastosPorProductos = new org.jdesktop.swingx.JXTable();
        jXLabelGastosPorInsumo = new org.jdesktop.swingx.JXLabel();
        jXLabelValorGastosPorInsumo = new org.jdesktop.swingx.JXLabel();
        jCheckBoxRedondearValores = new javax.swing.JCheckBox();
        panelTrabajadores = new org.edisoncor.gui.panel.PanelRect();
        buttonTerminarVentas = new org.edisoncor.gui.button.ButtonTextDown();
        buttonTransluceIcon1 = new org.edisoncor.gui.button.ButtonTransluceIcon();
        buttonImprimir = new org.edisoncor.gui.button.ButtonTextDown();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comenzar Día");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        panelRect1.setColorDeBorde(new java.awt.Color(255, 255, 0));
        panelRect1.setColorDeSegundoBorde(new java.awt.Color(32, 39, 55));

        tabbedPaneMain.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPaneMain.setMaximumSize(new java.awt.Dimension(920, 600));
        tabbedPaneMain.setPreferredSize(new java.awt.Dimension(920, 600));

        panelRESUMEN.setMaximumSize(null);
        panelRESUMEN.setLayout(new java.awt.GridLayout(2, 2, 20, 20));

        panelINFOVENTAS.setLayout(new java.awt.GridLayout(2, 0));

        jXLabelINFOTOTALVENTAS.setForeground(new java.awt.Color(255, 255, 0));
        jXLabelINFOTOTALVENTAS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelINFOTOTALVENTAS.setText("Venta");
        jXLabelINFOTOTALVENTAS.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        panelINFOVENTAS.add(jXLabelINFOTOTALVENTAS);

        jXLabelVALORVENTAS.setForeground(new java.awt.Color(255, 255, 255));
        jXLabelVALORVENTAS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelVALORVENTAS.setText("0.00 CUC");
        jXLabelVALORVENTAS.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        panelINFOVENTAS.add(jXLabelVALORVENTAS);

        panelRESUMEN.add(panelINFOVENTAS);

        panelINFOCGASTOS.setLayout(new java.awt.GridLayout(2, 0));

        jXLabelINFOTOGASTOS.setForeground(new java.awt.Color(255, 255, 0));
        jXLabelINFOTOGASTOS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelINFOTOGASTOS.setText("Gastos");
        jXLabelINFOTOGASTOS.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        panelINFOCGASTOS.add(jXLabelINFOTOGASTOS);

        jXLabelVALORGASTOS.setForeground(new java.awt.Color(255, 51, 0));
        jXLabelVALORGASTOS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelVALORGASTOS.setText("0.00 CUC");
        jXLabelVALORGASTOS.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        panelINFOCGASTOS.add(jXLabelVALORGASTOS);

        panelRESUMEN.add(panelINFOCGASTOS);

        panelINFOCGANANCIA.setLayout(new java.awt.GridLayout(2, 0));

        jXLabelINFOGANANCIA.setForeground(new java.awt.Color(255, 255, 0));
        jXLabelINFOGANANCIA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelINFOGANANCIA.setText("Ganancia");
        jXLabelINFOGANANCIA.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        panelINFOCGANANCIA.add(jXLabelINFOGANANCIA);

        jXLabelValorGANANCIA.setForeground(new java.awt.Color(0, 255, 0));
        jXLabelValorGANANCIA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelValorGANANCIA.setText("0.00 CUC");
        jXLabelValorGANANCIA.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        panelINFOCGANANCIA.add(jXLabelValorGANANCIA);

        panelRESUMEN.add(panelINFOCGANANCIA);

        panelINFOTRABAJADORES.setLayout(new java.awt.GridLayout(2, 0));

        jXLabelINFORTRAB.setForeground(new java.awt.Color(255, 255, 0));
        jXLabelINFORTRAB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelINFORTRAB.setText("Pago a Trabajadores");
        jXLabelINFORTRAB.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        panelINFOTRABAJADORES.add(jXLabelINFORTRAB);

        jXLabelValorTrab.setForeground(new java.awt.Color(255, 51, 51));
        jXLabelValorTrab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelValorTrab.setText("0.00 CUC");
        jXLabelValorTrab.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        panelINFOTRABAJADORES.add(jXLabelValorTrab);

        panelRESUMEN.add(panelINFOTRABAJADORES);

        tabbedPaneMain.addTab("Resumen", null, panelRESUMEN, "");

        jXLabelResumenVentas.setForeground(new java.awt.Color(255, 255, 255));
        jXLabelResumenVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelResumenVentas.setText("Resumen de Ventas");
        jXLabelResumenVentas.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jXTableOrdenes.setAutoCreateRowSorter(true);
        jXTableOrdenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Orden No", "Mesa", "Valor Total", "Cerrada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTableOrdenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXTableOrdenesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jXTableOrdenes);
        if (jXTableOrdenes.getColumnModel().getColumnCount() > 0) {
            jXTableOrdenes.getColumnModel().getColumn(3).setResizable(false);
        }

        jXTableResumenVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Producto", "Precio", "Cantidad", "Total Recaudado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTableResumenVentas.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jXTableResumenVentas);

        jXLabelOrden1.setForeground(new java.awt.Color(255, 255, 255));
        jXLabelOrden1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelOrden1.setText("Ordenes Activas");
        jXLabelOrden1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jXLabelTotalRecaudado.setForeground(new java.awt.Color(255, 255, 255));
        jXLabelTotalRecaudado.setText("Total Recaudado");
        jXLabelTotalRecaudado.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N

        jXLabelValorTotalRecaudado.setForeground(new java.awt.Color(255, 255, 255));
        jXLabelValorTotalRecaudado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jXLabelValorTotalRecaudado.setText("0.00 CUC");
        jXLabelValorTotalRecaudado.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N

        panelRect3.setAnchoDeBorde(0.0F);
        panelRect3.setColorDeBorde(new java.awt.Color(0, 0, 0));
        panelRect3.setColorDeSegundoBorde(new java.awt.Color(0, 0, 0));
        panelRect3.setColorPrimario(new java.awt.Color(0, 0, 0));

        buttonEditOrden.setText("Editar Orden");
        buttonEditOrden.setEnabled(Main.NIVEL_2);
        buttonEditOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditOrdenActionPerformed(evt);
            }
        });

        buttonDelOrden.setText("Eliminar Orden");
        buttonDelOrden.setEnabled(Main.NIVEL_3);
        buttonDelOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDelOrdenActionPerformed(evt);
            }
        });

        buttonAddOrden.setText("Añadir Orden");
        buttonAddOrden.setEnabled(Main.NIVEL_2);
        buttonAddOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddOrdenActionPerformed(evt);
            }
        });

        buttonCalcularCambio.setText("Calcular Cambio");
        buttonCalcularCambio.setEnabled(Main.NIVEL_2);
        buttonCalcularCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCalcularCambioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRect3Layout = new javax.swing.GroupLayout(panelRect3);
        panelRect3.setLayout(panelRect3Layout);
        panelRect3Layout.setHorizontalGroup(
            panelRect3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRect3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRect3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonEditOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCalcularCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRect3Layout.createSequentialGroup()
                        .addComponent(buttonAddOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(buttonDelOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelRect3Layout.setVerticalGroup(
            panelRect3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRect3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRect3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDelOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAddOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelRect3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEditOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCalcularCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        buttonImprimirZ.setText("Imprimir Z");
        buttonImprimirZ.setEnabled(Main.NIVEL_3);
        buttonImprimirZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirZActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelVentasLayout = new javax.swing.GroupLayout(panelVentas);
        panelVentas.setLayout(panelVentasLayout);
        panelVentasLayout.setHorizontalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(jXLabelOrden1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRect3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelVentasLayout.createSequentialGroup()
                                .addComponent(jXLabelTotalRecaudado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jXLabelValorTotalRecaudado, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jXLabelResumenVentas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonImprimirZ, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        panelVentasLayout.setVerticalGroup(
            panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVentasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabelOrden1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelResumenVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVentasLayout.createSequentialGroup()
                        .addGroup(panelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jXLabelValorTotalRecaudado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabelTotalRecaudado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonImprimirZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelRect3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        tabbedPaneMain.addTab("Ventas", panelVentas);

        jXLabelCamareros.setForeground(new java.awt.Color(255, 255, 255));
        jXLabelCamareros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelCamareros.setText("Camareros");
        jXLabelCamareros.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jXLabelCocinas.setForeground(new java.awt.Color(255, 255, 255));
        jXLabelCocinas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelCocinas.setText("Cocinas");
        jXLabelCocinas.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jXTableResumenCamareros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Nombre", "Total Vendido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jXTableResumenCamareros);

        jXTableResumenCocina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Total Vendido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jXTableResumenCocina);

        buttonImprimirCocina.setText("Imprimir");
        buttonImprimirCocina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirCocinaActionPerformed(evt);
            }
        });

        buttonImprimirCamarero.setText("Imprimir");
        buttonImprimirCamarero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirCamareroActionPerformed(evt);
            }
        });

        buttonImprimirTodoCamarero.setText(" Imprimir Todo");
        buttonImprimirTodoCamarero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirTodoCamareroActionPerformed(evt);
            }
        });

        buttonImprimirTodoCocina.setText("Imprimir Todo");
        buttonImprimirTodoCocina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirTodoCocinaActionPerformed(evt);
            }
        });

        buttonImprimirConsumoCasa.setText("Imprimir Consumo Casa");
        buttonImprimirConsumoCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirConsumoCasaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelResumenesLayout = new javax.swing.GroupLayout(panelResumenes);
        panelResumenes.setLayout(panelResumenesLayout);
        panelResumenesLayout.setHorizontalGroup(
            panelResumenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelResumenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXLabelCocinas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelResumenesLayout.createSequentialGroup()
                        .addComponent(jXLabelCamareros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelResumenesLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelResumenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelResumenesLayout.createSequentialGroup()
                                .addComponent(buttonImprimirTodoCamarero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(236, 236, 236)
                                .addComponent(buttonImprimirCamarero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelResumenesLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                                .addGap(20, 20, 20))))))
            .addGroup(panelResumenesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelResumenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResumenesLayout.createSequentialGroup()
                        .addComponent(buttonImprimirConsumoCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelResumenesLayout.createSequentialGroup()
                        .addComponent(buttonImprimirTodoCocina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(242, 242, 242)
                        .addComponent(buttonImprimirCocina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelResumenesLayout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addGap(20, 20, 20))))
        );
        panelResumenesLayout.setVerticalGroup(
            panelResumenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResumenesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabelCamareros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelResumenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonImprimirCamarero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonImprimirTodoCamarero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addComponent(jXLabelCocinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelResumenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonImprimirCocina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonImprimirTodoCocina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buttonImprimirConsumoCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        tabbedPaneMain.addTab("Resumenes Ventas", panelResumenes);

        jXTableGastosPorProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre Insumo", "U/M", "Cantidad", "Costo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jXTableGastosPorProductos);

        jXLabelGastosPorInsumo.setForeground(new java.awt.Color(255, 255, 255));
        jXLabelGastosPorInsumo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabelGastosPorInsumo.setText("Gasto total por productos");
        jXLabelGastosPorInsumo.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N

        jXLabelValorGastosPorInsumo.setForeground(new java.awt.Color(255, 255, 255));
        jXLabelValorGastosPorInsumo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jXLabelValorGastosPorInsumo.setText("0.00 MN");
        jXLabelValorGastosPorInsumo.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N

        jCheckBoxRedondearValores.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jCheckBoxRedondearValores.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxRedondearValores.setText("Redondear valores");

        javax.swing.GroupLayout panelGastoPorProductoLayout = new javax.swing.GroupLayout(panelGastoPorProducto);
        panelGastoPorProducto.setLayout(panelGastoPorProductoLayout);
        panelGastoPorProductoLayout.setHorizontalGroup(
            panelGastoPorProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGastoPorProductoLayout.createSequentialGroup()
                .addGroup(panelGastoPorProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelGastoPorProductoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxRedondearValores, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelGastoPorProductoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelGastoPorProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(panelGastoPorProductoLayout.createSequentialGroup()
                                .addComponent(jXLabelGastosPorInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jXLabelValorGastosPorInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );
        panelGastoPorProductoLayout.setVerticalGroup(
            panelGastoPorProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGastoPorProductoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelGastoPorProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabelGastosPorInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabelValorGastosPorInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxRedondearValores)
                .addGap(37, 37, 37))
        );

        tabbedPaneMain.addTab("Gasto por Productos", panelGastoPorProducto);

        javax.swing.GroupLayout panelTrabajadoresLayout = new javax.swing.GroupLayout(panelTrabajadores);
        panelTrabajadores.setLayout(panelTrabajadoresLayout);
        panelTrabajadoresLayout.setHorizontalGroup(
            panelTrabajadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        panelTrabajadoresLayout.setVerticalGroup(
            panelTrabajadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );

        tabbedPaneMain.addTab("Pago a Trabajadores", panelTrabajadores);

        buttonTerminarVentas.setText("Terminar Ventas");
        buttonTerminarVentas.setEnabled(Main.NIVEL_2);
        buttonTerminarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTerminarVentasActionPerformed(evt);
            }
        });

        buttonTransluceIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/restManager/resources/images/refresh.png"))); // NOI18N
        buttonTransluceIcon1.setText("buttonTransluceIcon1");
        buttonTransluceIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTransluceIcon1ActionPerformed(evt);
            }
        });

        buttonImprimir.setText("Imprimir Resumen");
        buttonImprimir.setEnabled(Main.NIVEL_4);
        buttonImprimir.setFont(restManager.resources.values.Fonts.BUTTON);
        buttonImprimir.setLabel(restManager.resources.strings.Strings.IMPRIMIR_LABEL);
        buttonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRect1Layout = new javax.swing.GroupLayout(panelRect1);
        panelRect1.setLayout(panelRect1Layout);
        panelRect1Layout.setHorizontalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(buttonTransluceIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonTerminarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRect1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabbedPaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRect1Layout.setVerticalGroup(
            panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRect1Layout.createSequentialGroup()
                .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRect1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRect1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonTransluceIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonTerminarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addGap(97, 97, 97))
        );

        getContentPane().add(panelRect1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDelOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDelOrdenActionPerformed
        eliminarItemFromTable(jXTableOrdenes, 0);
    }//GEN-LAST:event_buttonDelOrdenActionPerformed

    private void buttonTransluceIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransluceIcon1ActionPerformed

        refresh();

    }//GEN-LAST:event_buttonTransluceIcon1ActionPerformed

    private void buttonAddOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddOrdenActionPerformed

        PedidoCrearEditar c = PedidoCrearEditar.getInstance(this, true, null);

    }//GEN-LAST:event_buttonAddOrdenActionPerformed

    private void buttonEditOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditOrdenActionPerformed
        PedidoCrearEditar c = PedidoCrearEditar.getInstance(this, true, getSelectedOrden());
    }//GEN-LAST:event_buttonEditOrdenActionPerformed

    private void buttonTerminarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTerminarVentasActionPerformed
        terminarDia();
        
    }//GEN-LAST:event_buttonTerminarVentasActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        refresh();

    }//GEN-LAST:event_formWindowGainedFocus

    private void buttonImprimirCocinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirCocinaActionPerformed
        imprimirResumenCocina();
    }//GEN-LAST:event_buttonImprimirCocinaActionPerformed

    private void buttonImprimirCamareroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirCamareroActionPerformed
        imprimirResumenPersonal();
    }//GEN-LAST:event_buttonImprimirCamareroActionPerformed

    private void buttonImprimirTodoCamareroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirTodoCamareroActionPerformed
        imprimirTodoCamarero();
    }//GEN-LAST:event_buttonImprimirTodoCamareroActionPerformed

    private void buttonImprimirTodoCocinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirTodoCocinaActionPerformed
        imprimirTodoCocina();
    }//GEN-LAST:event_buttonImprimirTodoCocinaActionPerformed

    private void buttonImprimirConsumoCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirConsumoCasaActionPerformed
        imprimirConsumoCasa();
    }//GEN-LAST:event_buttonImprimirConsumoCasaActionPerformed

    private void jXTableOrdenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTableOrdenesMouseClicked
        if(evt.getClickCount() == 2){
            PedidoCrearEditar c = PedidoCrearEditar.getInstance(this, true, getSelectedOrden());
        }
    }//GEN-LAST:event_jXTableOrdenesMouseClicked

    private void buttonImprimirZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirZActionPerformed
        imprimirZ();
    }//GEN-LAST:event_buttonImprimirZActionPerformed

    private void buttonCalcularCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCalcularCambioActionPerformed
        CalcularCambio cc = new CalcularCambio(this, true, getSelectedOrden());
    }//GEN-LAST:event_buttonCalcularCambioActionPerformed

    private void buttonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirActionPerformed
        try {
            VentaB f = new VentaB(v);
            LoadingWindow.show(this);
            f.execute();
            List<Orden> ords = f.get();
            for (Orden x : ords) {
                imp.print(x, false);
            }
        } catch (InterruptedException | ExecutionException | PrintException ex) {
            Logger.getLogger(VentaMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonTextDown buttonAddOrden;
    private org.edisoncor.gui.button.ButtonTextDown buttonCalcularCambio;
    private org.edisoncor.gui.button.ButtonTextDown buttonDelOrden;
    private org.edisoncor.gui.button.ButtonTextDown buttonEditOrden;
    private org.edisoncor.gui.button.ButtonTextDown buttonImprimir;
    private org.edisoncor.gui.button.ButtonTextDown buttonImprimirCamarero;
    private org.edisoncor.gui.button.ButtonTextDown buttonImprimirCocina;
    private org.edisoncor.gui.button.ButtonTextDown buttonImprimirConsumoCasa;
    private org.edisoncor.gui.button.ButtonTextDown buttonImprimirTodoCamarero;
    private org.edisoncor.gui.button.ButtonTextDown buttonImprimirTodoCocina;
    private org.edisoncor.gui.button.ButtonTextDown buttonImprimirZ;
    private org.edisoncor.gui.button.ButtonTextDown buttonTerminarVentas;
    private org.edisoncor.gui.button.ButtonTransluceIcon buttonTransluceIcon1;
    private javax.swing.JCheckBox jCheckBoxRedondearValores;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private org.jdesktop.swingx.JXLabel jXLabelCamareros;
    private org.jdesktop.swingx.JXLabel jXLabelCocinas;
    private org.jdesktop.swingx.JXLabel jXLabelGastosPorInsumo;
    private org.jdesktop.swingx.JXLabel jXLabelINFOGANANCIA;
    private org.jdesktop.swingx.JXLabel jXLabelINFORTRAB;
    private org.jdesktop.swingx.JXLabel jXLabelINFOTOGASTOS;
    private org.jdesktop.swingx.JXLabel jXLabelINFOTOTALVENTAS;
    private org.jdesktop.swingx.JXLabel jXLabelOrden1;
    private org.jdesktop.swingx.JXLabel jXLabelResumenVentas;
    private org.jdesktop.swingx.JXLabel jXLabelTotalRecaudado;
    private org.jdesktop.swingx.JXLabel jXLabelVALORGASTOS;
    private org.jdesktop.swingx.JXLabel jXLabelVALORVENTAS;
    private org.jdesktop.swingx.JXLabel jXLabelValorGANANCIA;
    private org.jdesktop.swingx.JXLabel jXLabelValorGastosPorInsumo;
    private org.jdesktop.swingx.JXLabel jXLabelValorTotalRecaudado;
    private org.jdesktop.swingx.JXLabel jXLabelValorTrab;
    private org.jdesktop.swingx.JXTable jXTableGastosPorProductos;
    private org.jdesktop.swingx.JXTable jXTableOrdenes;
    private org.jdesktop.swingx.JXTable jXTableResumenCamareros;
    private org.jdesktop.swingx.JXTable jXTableResumenCocina;
    private org.jdesktop.swingx.JXTable jXTableResumenVentas;
    private org.edisoncor.gui.panel.PanelRect panelGastoPorProducto;
    private org.edisoncor.gui.panel.PanelRoundTranslucido panelINFOCGANANCIA;
    private org.edisoncor.gui.panel.PanelRoundTranslucido panelINFOCGASTOS;
    private org.edisoncor.gui.panel.PanelRoundTranslucido panelINFOTRABAJADORES;
    private org.edisoncor.gui.panel.PanelRoundTranslucido panelINFOVENTAS;
    private org.edisoncor.gui.panel.PanelRect panelRESUMEN;
    private org.edisoncor.gui.panel.PanelRect panelRect1;
    private org.edisoncor.gui.panel.PanelRect panelRect3;
    private org.edisoncor.gui.panel.PanelRect panelResumenes;
    private org.edisoncor.gui.panel.PanelRect panelTrabajadores;
    private org.edisoncor.gui.panel.PanelRect panelVentas;
    private org.edisoncor.gui.tabbedPane.TabbedPaneHeader tabbedPaneMain;
    // End of variables declaration//GEN-END:variables

    private Orden getSelectedOrden() {

        int row = jXTableOrdenes.getSelectedRow();
        if (row == -1) {
            throw new IndexOutOfBoundsException("No hay ninguna fila seleccionada");

        } else {
            String cod = (String) jXTableOrdenes.getValueAt(row, 0);
            return staticContent.ordenJPA.findOrden(cod);
        }

    }

    public void imprimirResumenPersonal() {
        int row = jXTableResumenCamareros.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "No Hay ninguna fila seleccionada");
        } else {
            String cod = (String) jXTableResumenCamareros.getValueAt(row, 0);
            Personal p = staticContent.personalJPA.findPersonal(cod);
            List<ProductovOrden> aux = VentaDAO.getResumenVentasCamarero(v, p);
            Collections.sort(aux,(o1, o2) -> {
            return o1.getProductoVenta().getNombre().compareTo(o2.getProductoVenta().getNombre());});
            imp.printPersonalResumen(aux, p, v.getFecha());

        }
    }

    public void imprimirResumenCocina() {
        int row = jXTableResumenCocina.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "No Hay ninguna fila seleccionada");
        } else {
            String cod = (String) jXTableResumenCocina.getValueAt(row, 0);
            Cocina c = staticContent.cocinaJPA.findCocina(cod);
            List<ProductovOrden> aux = VentaDAO.getResumenVentasCocina(v, c);
             Collections.sort(aux,(o1, o2) -> {
            return o1.getProductoVenta().getNombre().compareTo(o2.getProductoVenta().getNombre());});
            imp.printResumenPuntoElab(aux, c, v.getFecha());

        }

    }

    private void terminarDia() {
        int resp = JOptionPane.showConfirmDialog(this, "¿Desea terminar el día de trabajo?");
        if (resp == JOptionPane.YES_OPTION) {
            float ventaTotal = 0,
                    ventasGastosEnInsumos = 0;
            EntityManager em = staticContent.getEMF().createEntityManager();
            em.getTransaction().begin();
            for (Orden x : v.getOrdenList()) {
                if (x.getHoraTerminada() == null) {
                    JOptionPane.showMessageDialog(this, 
                            "Existen tickets sin cerrar. Cierre los tickets antes de terminar la venta");
                    return;
                    
                }
                if(!x.getDeLaCasa()){
                ventaTotal += x.getOrdenvalorMonetario();
                }
                
                ventasGastosEnInsumos += x.getOrdengastoEninsumos();
                
            }
            v.setVentaTotal((double) ventaTotal);
            v.setVentagastosEninsumos((double) ventasGastosEnInsumos);
            em.merge(v);
            em.getTransaction().commit();
            dispose();

        }
    }

    private void initDialog() {
        Cocina c = null;
        for (Cocina x : staticContent.cocinaJPA.findCocinaEntities()) {
            if (x.getNombreCocina().equals("Barra")) {
                c = x;
                break;
            }
        }
        estadoIPVBarra = new IPVEstado(c, v.getFecha());
        tabbedPaneMain.addTab("IPV Barra", estadoIPVBarra);

        if (!Main.NIVEL_3) {
            tabbedPaneMain.remove(0);
            tabbedPaneMain.remove(1);
            tabbedPaneMain.remove(1);

        }
        jXLabelValorTotalRecaudado.setVisible(Main.NIVEL_3);
        jXLabelTotalRecaudado.setVisible(Main.NIVEL_3);
    }

    private void UpdateTablaIPVBarra(Venta ventas) {
        List<IpvRegistro> registros = estadoIPVBarra.getRegistros();

        List<ProductoInsumo> resumenGastos
                = VentaDAO.getResumenGastosCocina(estadoIPVBarra.getC(), v);

        for (IpvRegistro r : registros) {
            for (ProductoInsumo x : resumenGastos) {
                if (x.getInsumo().getCodInsumo().
                        equals(r.getIpvRegistroPK().getIpvinsumocodInsumo())) {
                    r.setConsumo(x.getCantidad());
                }
            }
        }

        estadoIPVBarra.updateDialog();
    }

    private void limpiarMesas() {
    List<Mesa> mesas = staticContent.mesasJPA.findMesaEntities();
    
        for (Mesa m : mesas) {
        m.setEstado("vacia");
        try {
            staticContent.mesasJPA.edit(m);
        } catch (Exception ex) {
            Logger.getLogger(VentaMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    }

    private void imprimirTodoCamarero() {
        for (int i = 0; i < jXTableResumenCamareros.getRowCount(); i++) {
            String cod = (String) jXTableResumenCamareros.getValueAt(i, 0);
            Personal p = staticContent.personalJPA.findPersonal(cod);
            List<ProductovOrden> aux = VentaDAO.getResumenVentasCamarero(v, p);
            Collections.sort(aux,(o1, o2) -> {
            return o1.getProductoVenta().getNombre().compareTo(o2.getProductoVenta().getNombre());});
            imp.printPersonalResumen(aux, p, v.getFecha());
        }
    }

    private void imprimirTodoCocina() {
      
        for (int i = 0; i < jXTableResumenCocina.getRowCount(); i++) {
            String cod = (String) jXTableResumenCocina.getValueAt(i, 0);
            Cocina c = staticContent.cocinaJPA.findCocina(cod);
            List<ProductovOrden> aux = VentaDAO.getResumenVentasCocina(v, c);
            Collections.sort(aux,(o1, o2) -> {
            return o1.getProductoVenta().getNombre().compareTo(o2.getProductoVenta().getNombre());});
            imp.printResumenPuntoElab(aux, c, v.getFecha());

        } 
    }

    private void imprimirConsumoCasa() {
    imp.printResumenCasa(VentaDAO.getResumenVentasCasa(v),v.getFecha());
    
    }

    private void imprimirZ() {
        imp.printZ(v);
    }

}
