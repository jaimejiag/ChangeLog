/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoreslocales.ChangelogChangesDAO;

import com.al.changelogdtos.ChangelogCambioDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jaimejimenez
 */
public interface IChangelogChanges {
    List<ChangelogCambioDTO> getAllChanges(Connection conn) throws Exception;
}
