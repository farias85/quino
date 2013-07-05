/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.clases.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quino.clases.config.ConfigPrueba;

/**
 *
 * @author farias
 */
public class EnsayoTest {

    public EnsayoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getConfiguracion method, of class Ensayo.
     */
    @Test
    public void testGetConfiguracion() {
        System.out.println("getConfiguracion");
        Ensayo instance = new Ensayo();
        ConfigPrueba expResult = null;
        ConfigPrueba result = instance.getConfiguracion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConfiguracion method, of class Ensayo.
     */
    @Test
    public void testSetConfiguracion() {
        System.out.println("setConfiguracion");
        ConfigPrueba configuracion = null;
        Ensayo instance = new Ensayo();
        instance.setConfiguracion(configuracion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescripcion method, of class Ensayo.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Ensayo instance = new Ensayo();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescripcion method, of class Ensayo.
     */
    @Test
    public void testSetDescripcion() {
        System.out.println("setDescripcion");
        String descripcion = "";
        Ensayo instance = new Ensayo();
        instance.setDescripcion(descripcion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isError method, of class Ensayo.
     */
    @Test
    public void testIsError() {
        System.out.println("isError");
        Ensayo instance = new Ensayo();
        boolean expResult = false;
        boolean result = instance.isError();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setError method, of class Ensayo.
     */
    @Test
    public void testSetError() {
        System.out.println("setError");
        boolean error = false;
        Ensayo instance = new Ensayo();
        instance.setError(error);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKey method, of class Ensayo.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        Ensayo instance = new Ensayo();
        int expResult = 0;
        int result = instance.getKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKey method, of class Ensayo.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        int key = 0;
        Ensayo instance = new Ensayo();
        instance.setKey(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPanelEstimulo method, of class Ensayo.
     */
    @Test
    public void testGetPanelEstimulo() {
        System.out.println("getPanelEstimulo");
        Ensayo instance = new Ensayo();
        int expResult = 0;
        int result = instance.getPanelEstimulo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPanelEstimulo method, of class Ensayo.
     */
    @Test
    public void testSetPanelEstimulo() {
        System.out.println("setPanelEstimulo");
        int panelEstimulo = 0;
        Ensayo instance = new Ensayo();
        instance.setPanelEstimulo(panelEstimulo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTiempoRespuesta method, of class Ensayo.
     */
    @Test
    public void testGetTiempoRespuesta() {
        System.out.println("getTiempoRespuesta");
        Ensayo instance = new Ensayo();
        int expResult = 0;
        int result = instance.getTiempoRespuesta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTiempoRespuesta method, of class Ensayo.
     */
    @Test
    public void testSetTiempoRespuesta() {
        System.out.println("setTiempoRespuesta");
        int tiempoRespuesta = 0;
        Ensayo instance = new Ensayo();
        instance.setTiempoRespuesta(tiempoRespuesta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}