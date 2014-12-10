package de.htwg.battleship.model.impl;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
public class FieldTest {
    
    Field field;
    
    public FieldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        field = new Field(0, 0);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetXY() {
        this.field = new Field(5, 9);
        assertEquals(5, field.getX());
        assertEquals(9, field.getY());
        this.field = new Field(2, 3);
        assertEquals(2, field.getX());
        assertEquals(3, field.getY());
    }
    
    /**
     * Test of isHit method, of class Field.
     */
    @Test
    public void testIsHit() {
        this.field = new Field(0, 0);
        boolean expResult = false;
        boolean result = field.isHit();
        assertEquals(expResult, result);
        this.field = new Field(2, 3);
        result = field.isHit();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHit method, of class Field.
     */
    @Test
    public void testSetHit() {
        field.setHit(true);
        assertEquals(field.isHit(), true);
        field.setHit(false);                // Doesn't make sense.
        assertEquals(field.isHit(), true);
        field.setHit(true);
        assertEquals(field.isHit(), true);
        field = new Field(0, 0);
        assertEquals(field.isHit(), false);
        field.setHit(false);
        assertEquals(field.isHit(), false);
    }
    
}
