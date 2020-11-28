/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltp.Test1;

import com.ltp.Services.MuonSachServices;
import com.ltp.Services.Utils;
import com.ltp.pojo.MuonSach;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Le Tran Phu
 */
public class MuonSachTester {
    
    private static java.sql.Connection conn;
    @BeforeClass
    public static void setUp() {
        conn = (Connection) Utils.getConn();
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
    public void TestAddMuon() throws SQLException
    {
        MuonSach test = new MuonSach(1, "Lão Hạc", "2020/30/11");
        
        boolean kq =  MuonSachServices.addMuon(test);  
        Assert.assertTrue(kq);
    }
    
    @Test
    public void TestMuonSach() throws SQLException
    {
       
        MuonSach test1 = new MuonSach(2, "Thơ Thơ", "2020/30/11");
        MuonSach test2 = new MuonSach(2, "Suối Nguồn", "2020/30/11");
        MuonSach test3 = new MuonSach(2, "Nhà Giả Kim", "2020/30/11");
        
        
        MuonSachServices.addMuon(test1);
        MuonSachServices.addMuon(test2);
        MuonSachServices.addMuon(test3);  
        
        List<MuonSach> kq = MuonSachServices.getInfoMuonSach("2");
        Assert.assertEquals(3, kq.size());      
    }
    
    
}
