/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobits.pos.servicios.impresion;

import com.jobits.pos.domain.models.volatil.Impresora;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Javier
 */
public class ImpresoraRepoImplTest {

    public ImpresoraRepoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of eliminarImpresora method, of class ImpresoraRepoImpl.
     */
    @Test
    public void testEliminarImpresora() {
        System.out.println("eliminarImpresora");
        Impresora impresoraToDelete = null;
        ImpresoraRepoImpl instance = new ImpresoraRepoImpl();
        boolean expResult = false;
        boolean result = instance.eliminarImpresora(impresoraToDelete);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarImpresoras method, of class ImpresoraRepoImpl.
     */
    @Test
    public void testGuardarImpresoras() {
        System.out.println("guardarImpresoras");

        List<Impresora> listaAGuardar = new ArrayList<>();
        listaAGuardar.add(new Impresora("Prueba", "prueba", "preba", true));

        ImpresoraRepoImpl instance = new ImpresoraRepoImpl();
        boolean expResult = true;
        boolean result = instance.guardarImpresoras(listaAGuardar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarImpresoras method, of class ImpresoraRepoImpl.
     */
    @Test
    public void testCargarImpresoras() {
        System.out.println("cargarImpresoras");
        ImpresoraRepoImpl instance = new ImpresoraRepoImpl();
        List<Impresora> expResult = new ArrayList<>();
        expResult.add(new Impresora("Prueba", "prueba", "preba", true));
        List<Impresora> result = instance.cargarImpresoras();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
