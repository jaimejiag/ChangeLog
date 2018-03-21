/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoreslocales.ChangelogVersonEJB;

import com.asesoreslocales.ChangelogVersionDAO.ChangelogVersionDAO;
import com.al.changelogdtos.ChangelogVersionDTO;
import com.al.db.ConnectionJDBC;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaimejimenez
 */
public class ChangelogVersionEJB implements IChangelogVersionEJB {
    
    @Override
    public List<ChangelogVersionDTO> requestToGetAllVersions() {
        ChangelogVersionDAO versionDAO = new ChangelogVersionDAO();
        List<ChangelogVersionDTO> versions = null;
        Connection conn = null;
        
        try {
            conn = ConnectionJDBC.getConnection("jdbc/log");
            versions = versionDAO.getAllVersions(conn);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChangelogVersionEJB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return versions;
    }
}
