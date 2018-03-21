/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoreslocales.ChangelogChangesEJB;

import com.al.changelogdtos.ChangelogCambioDTO;
import java.util.List;

/**
 *
 * @author jaimejimenez
 */
public interface IChangelogChangesEJB {
    List<ChangelogCambioDTO> requestToGetAllChanges() throws Exception;
}
