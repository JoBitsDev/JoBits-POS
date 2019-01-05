/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restManager.exceptions;

import restManager.resources.R;

/**
 * FirstDream
 *
 * @author Jorge
 *
 */
public class DevelopingOperationException extends RestManagerRuntimeException {

    public DevelopingOperationException() {
        super();
        showMessage(R.RESOURCE_BUNDLE.getString("exception_developing"));
    }

    public DevelopingOperationException(String message) {
        super(message);
        showMessage(message);
    }

}
