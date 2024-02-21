/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.client.webconnection.login.model;

/**
 *
 * JoBits
 *
 * @author Jorge
 *
 */
public interface UbicacionService {

    public UbicacionModelWrapper loadLocations();
    
    public UbicacionModelWrapper saveLocations(UbicacionModelWrapper wrapper);

    public UbicacionModelWrapper setSelectedLocation(UbicacionModel location);
}
