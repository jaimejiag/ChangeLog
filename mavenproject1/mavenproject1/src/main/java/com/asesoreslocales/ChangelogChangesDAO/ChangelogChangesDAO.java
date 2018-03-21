/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoreslocales.ChangelogChangesDAO;

import com.al.changelogdtos.ChangelogCambioDTO;
import com.al.db.ConnectionJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaimejimenez
 */
public class ChangelogChangesDAO implements IChangelogChanges {

    @Override
    public List<ChangelogCambioDTO> getAllChanges(Connection conn) throws Exception {
        List<ChangelogCambioDTO> changesList = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        StringBuilder sql = null;
        ChangelogCambioDTO cambioDTO = null;
        
        try {
            sql = new StringBuilder();
            sql.append("Select ");
            sql.append("* ");
            sql.append("FROM ");
            sql.append("changelog_cambios");
            
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            if (rs.isBeforeFirst()) {
                changesList = new ArrayList<>();
                
                while (rs.next()) {
                    cambioDTO = new ChangelogCambioDTO();
                    cambioDTO.setId(rs.getInt("id"));
                    cambioDTO.setIdVersion(rs.getInt("idVersion"));
                    cambioDTO.setCambio(rs.getString("cambio"));
                    cambioDTO.setIdAplicacion(rs.getInt("idAplicacion"));
                    changesList.add(cambioDTO);
                }
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            ConnectionJDBC.closeResultset(rs);
            ConnectionJDBC.closePreparedStatement(statement);
        }
        
        return changesList;
    }
}
