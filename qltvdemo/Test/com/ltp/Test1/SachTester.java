/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltp.Test1;

import com.ltp.Services.SachServices;
import com.ltp.Services.Utils;
import com.ltp.pojo.Sach;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Le Tran Phu
 */
public class SachTester {
    private static Connection conn;
    private List<Sach> sach;
    
    @BeforeClass
    public static void setUp() {
        conn = Utils.getConn();
    }
    
    @AfterClass
    public static void tearDown() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SachTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TestSachSize() throws SQLException
    {
        sach = SachServices.getInfoSach();
        
        Assert.assertEquals(6, sach.size());
    }
    
    @Test 
    public void TestDataSaving() throws SQLException
    {
        sach = SachServices.getInfoSach();
        Sach s = sach.get(0);
        
        Assert.assertEquals(2009, s.getNamXB());
    }
    
    
}
