/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltp.Test1;

import com.ltp.Services.DocGiaServices;
import com.ltp.Services.Utils;
import com.ltp.pojo.DocGia;
import java.sql.Connection;
import java.sql.SQLException;
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
public class DocGiaTester {
    
    private static Connection conn;
    
     @BeforeClass
    public static void setUp() {
        conn = Utils.getConn();
    }
    
    @AfterClass
    public static void tearDown() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DocGiaTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void TestAddDocGiaSuccess() throws SQLException
    {
        DocGia test = new DocGia("name", "gioi tinh", "ngaysinh", "doiTuong",
                "boPhan", "hanThe", "hanThe2", "hanThe2", 0, "diaChi");
        boolean kq = DocGiaServices.add(test);
        
        Assert.assertTrue(kq);        
    }
   
    @Test
    public void TestDataSaving() throws SQLException
    {
        DocGia test1 = new DocGia("name", "gioi tinh", "ngaysinh", "doiTuong",
                "boPhan", "hanThe", "hanThe2", "hanThe2", 0, "diaChi");
        DocGiaServices.add(test1);
        String id = Integer.toString(test1.getId());
        DocGia test2 = DocGiaServices.getInfoDocGia(id);
        Assert.assertEquals(test1.getName(), test2.getName());
    }
    
    @Test
    public void TestAutoID() throws SQLException
    {
        
        DocGia test3 = new DocGia();
        DocGia test4 = new DocGia();
        Assert.assertEquals(test3.getId(), test4.getId() - 1);
    }
    
    
    
}
