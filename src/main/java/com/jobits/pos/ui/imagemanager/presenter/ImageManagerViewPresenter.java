/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.ui.imagemanager.presenter;

//import com.jobits.pos.controller.imagemanager.impl.ImageManagerController;
import com.jobits.pos.controller.imagemanager.ImageManagerService;
import com.jobits.pos.main.Application;
import com.root101.clean.core.app.services.utils.TipoNotificacion;
import com.jobits.pos.recursos.R;
import com.jobits.pos.ui.imagemanager.PanelDibujo;
import com.jobits.pos.ui.module.PosDesktopUiModule;
import com.jobits.pos.ui.presenters.AbstractViewAction;
import com.jobits.pos.ui.presenters.AbstractViewPresenter;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 *
 * @author Home
 */
public class ImageManagerViewPresenter extends AbstractViewPresenter<ImageManagerViewModel> {

    public static String ACTION_CARGAR_IMAGEN = "Cargar Imagen";
    public static String ACTION_RECORTAR_IMAGEN = "Recortar Imagen";
    public static String ACTION_GUARDAR_IMAGEN = "Guardar Imagen";
    public static String ACTION_CERRAR = "Cerrar";

    ImageManagerService service = PosDesktopUiModule.getInstance().getImplementation(ImageManagerService.class);

    public ImageManagerViewPresenter(String imageName) {
        super(new ImageManagerViewModel());
        service.setImageName(imageName);
        preLoadOldImage();
    }

    @Override
    protected void registerOperations() {
        registerOperation(new AbstractViewAction(ACTION_CARGAR_IMAGEN) {
            @Override
            public Optional doAction() {
                BufferedImage img = service.openNewImage(new Dimension(700, 480));//TODO: Hacer que la dimension sea generica
                if (img != null) {
                    getBean().setPanel_dibujo(new PanelDibujo(img));
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_RECORTAR_IMAGEN) {
            @Override
            public Optional doAction() {
                PanelDibujo pd = getBean().getPanel_dibujo();
                getBean().setPanel_dibujo(new PanelDibujo(service.recortar_imagen(
                        pd.getImg(), pd.getXValue(), pd.getYValue(), pd.getAncho(), pd.getAlto())));
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_GUARDAR_IMAGEN) {
            @Override
            public Optional doAction() {
                if ((boolean) Application.getInstance().getNotificationService().
                        showDialog("Esta seguro que desea continuar?",
                                TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                    PanelDibujo pd = getBean().getPanel_dibujo();
                    service.guardar_imagen(pd.getImagmemoria());
                    Application.getInstance().getNotificationService().showDialog(
                            "Se ha guardado Correctamente la imagen recortada", TipoNotificacion.INFO);
                }
                return Optional.empty();
            }
        });
        registerOperation(new AbstractViewAction(ACTION_CERRAR) {
            @Override
            public Optional doAction() {
                if ((boolean) Application.getInstance().getNotificationService().
                        showDialog("Esta seguro que desea cancelar?",
                                TipoNotificacion.DIALOG_CONFIRM).orElse(false)) {
                    Application.getInstance().getNavigator().navigateUp();
                }
                return Optional.empty();
            }
        });
    }

    private void preLoadOldImage() {
        String path = R.MEDIA_FILE_PATH + service.getImageName() + ".jpg";
        getBean().setOld_image(service.loadImageIcon(path, new Dimension(70, 70)));//TODO: Hacer que la dimension sea generica
    }
}
