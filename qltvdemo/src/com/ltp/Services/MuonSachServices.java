/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltp.Services;

import com.ltp.pojo.MuonSach;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class MuonSachServices {
    public static List<MuonSach> getInfoMuonSach(String id) throws SQLException, NumberFormatException
    {
        String sql = "SELECT * FROM muonsach WHERE IdDocGia=?";
        Connection conn = Utils.getConn();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, parseInt(id));
        ResultSet rs = stm.executeQuery();
        rs.first();
        rs.previous();
        List<MuonSach> muonSachs = new ArrayList<>();
        while(rs.next())
        {
            MuonSach d = new MuonSach(rs.getInt("IdDocGia"),rs.getString("TenSach"),rs.getString("NgayMuon"));
            muonSachs.add(d);
        }
        
        return muonSachs;
    }
    public static boolean addMuon(MuonSach m) throws SQLException, NumberFormatException
    {
        Connection conn = Utils.getConn();
        try{
            conn.setAutoCommit(false);
            String sql = "INSERT INTO muonsach(IdDocGia, TenSach, NgayMuon) VALUES"
                    + " (?,?,?)";
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setInt(1, m.getIdMuon());
                stm.setString(2, m.getTenSachMuon());
                stm.setString(3, m.getNgayMuon());
                stm.executeUpdate();
            }
            conn.commit();
            return true;
        }catch(SQLException ex) {
            ex.getStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DocGiaServices.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return false;
    }
    
    public static void xoaMuon(int id) throws SQLException
    {
        Connection conn = Utils.getConn();
        String sql = "DELETE FROM muonsach WHERE IdDocGia=?";
        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1,id );
            stm.executeUpdate();
        }
    }
}
