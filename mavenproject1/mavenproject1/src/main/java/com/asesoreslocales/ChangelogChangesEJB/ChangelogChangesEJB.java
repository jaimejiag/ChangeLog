/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoreslocales.ChangelogChangesEJB;

import com.al.changelogdtos.ChangelogCambioDTO;
import com.al.db.ConnectionJDBC;
import com.asesoreslocales.ChangelogChangesDAO.ChangelogChangesDAO;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jaimejimenez
 */
public class ChangelogChangesEJB implements IChangelogChangesEJB {

    @Override
    public List<ChangelogCambioDTO> requestToGetAllChanges() throws Exception {
        ChangelogChangesDAO changesDAO = new ChangelogChangesDAO();
        List<ChangelogCambioDTO> changesList = null;
        Connection conn = null;
        
        try {
            conn = ConnectionJDBC.getConnection("jdbc/log");
            changesList = changesDAO.getAllChanges(conn);
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (conn != null)
                conn.close();
        }
        
        return changesList;
    }
}
