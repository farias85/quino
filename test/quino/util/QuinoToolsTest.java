/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quino.util;

import javax.swing.JDialog;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quino.clases.model.Prueba;
import quino.view.main.PrincipalView;

/**
 *
 * @author farias
 */
public class QuinoToolsTest {

    public QuinoToolsTest() {
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
     * Test of getColumnWidthSize method, of class QuinoTools.
     */
    @Test
    public void testGetColumnWidthSize() {
        System.out.println("getColumnWidthSize");
        int strlength = 0;
        int expResult = 0;
        int result = QuinoTools.getColumnWidthSize(strlength);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPanelMovimiento method, of class QuinoTools.
     */
    @Test
    public void testGetPanelMovimiento() {
        System.out.println("getPanelMovimiento");
        Prueba prueba = null;
        int panel = 0;
        String expResult = "";
        String result = QuinoTools.getPanelMovimiento(prueba, panel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarPruebaEnRegistro method, of class QuinoTools.
     */
    @Test
    public void testSalvarPruebaEnRegistro() {
        System.out.println("salvarPruebaEnRegistro");
        PrincipalView principalView = null;
        JDialog testView = null;
        Prueba prueba = null;
        QuinoTools.salvarPruebaEnRegistro(principalView, testView, prueba);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCI method, of class QuinoTools.
     */
    @Test
    public void testCheckCI() throws Exception {
        System.out.println("checkCI");
        String ci = "";
        long expResult = 0L;
        long result = QuinoTools.checkCI(ci);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarLibroExcel method, of class QuinoTools.
     */
    @Test
    public void testSalvarLibroExcel() {
        System.out.println("salvarLibroExcel");
        String path = "";
        HSSFWorkbook book = null;
        QuinoTools.salvarLibroExcel(path, book);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarConfiguracion method, of class QuinoTools.
     */
    @Test
    public void testSalvarConfiguracion() {
        System.out.println("salvarConfiguracion");
        QuinoTools.salvarConfiguracion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cargarConfiguracion method, of class QuinoTools.
     */
    @Test
    public void testCargarConfiguracion() {
        System.out.println("cargarConfiguracion");
        QuinoTools.cargarConfiguracion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of porcientoDuracion method, of class QuinoTools.
     */
    @Test
    public void testPorcientoDuracion_double() {
        System.out.println("porcientoDuracion");
        double porcentaje = 0.0;
        int expResult = 0;
        int result = QuinoTools.porcientoDuracion(porcentaje);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of porcientoDuracion method, of class QuinoTools.
     */
    @Test
    public void testPorcientoDuracion_double_double() {
        System.out.println("porcientoDuracion");
        double porcentaje = 0.0;
        double tiempoDuracion = 0.0;
        double expResult = 0.0;
        double result = QuinoTools.porcientoDuracion(porcentaje, tiempoDuracion);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistancia method, of class QuinoTools.
     */
    @Test
    public void testGetDistancia() {
        System.out.println("getDistancia");
        Punto p1 = null;
        Punto p2 = null;
        double expResult = 0.0;
        double result = QuinoTools.getDistancia(p1, p2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAngulo method, of class QuinoTools.
     */
    @Test
    public void testGetAngulo_Punto_Punto() {
        System.out.println("getAngulo");
        Punto p1 = null;
        Punto p2 = null;
        double expResult = 0.0;
        double result = QuinoTools.getAngulo(p1, p2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAngulo method, of class QuinoTools.
     */
    @Test
    public void testGetAngulo_Punto() {
        System.out.println("getAngulo");
        Punto p2 = null;
        double expResult = 0.0;
        double result = QuinoTools.getAngulo(p2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAngulo method, of class QuinoTools.
     */
    @Test
    public void testGetAngulo_3args() {
        System.out.println("getAngulo");
        Punto p2 = null;
        double desplazamientX = 0.0;
        double desplazamientoY = 0.0;
        double expResult = 0.0;
        double result = QuinoTools.getAngulo(p2, desplazamientX, desplazamientoY);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVelocidad method, of class QuinoTools.
     */
    @Test
    public void testGetVelocidad() {
        System.out.println("getVelocidad");
        double tiempoMovimiento = 0.0;
        double expResult = 0.0;
        double result = QuinoTools.getVelocidad(tiempoMovimiento);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}