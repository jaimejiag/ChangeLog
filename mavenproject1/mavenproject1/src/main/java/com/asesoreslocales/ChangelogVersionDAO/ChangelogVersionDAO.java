/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoreslocales.ChangelogVersionDAO;

import com.al.changelogdtos.ChangelogVersionDTO;
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
public class ChangelogVersionDAO implements IChangelogVersionDAO {

    @Override
    public List<ChangelogVersionDTO> getAllVersions(Connection conn) throws Exception {
        ArrayList<ChangelogVersionDTO> versionList = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuilder sql = null;

        try {
            sql = new StringBuilder();
            int id = 0;
            String version = null;
            int idAplicacion = 0;

            sql.append("SELECT ");
            sql.append("* ");
            sql.append(" FROM ");
            sql.append(" changelog_version");
            stmt = conn.prepareStatement(sql.toString());

            rs = stmt.executeQuery();

            if (rs.isBeforeFirst()) {
                versionList = new ArrayList<ChangelogVersionDTO>();

                while (rs.next()) {
                    id = rs.getInt("id");
                    version = rs.getString("version");
                    idAplicacion = rs.getInt("idAplicacion");
                    versionList.add(new ChangelogVersionDTO(id, version, idAplicacion));
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            ConnectionJDBC.closeResultset(rs);
            ConnectionJDBC.closePreparedStatement(stmt);
            return versionList;
        }
    }
}

