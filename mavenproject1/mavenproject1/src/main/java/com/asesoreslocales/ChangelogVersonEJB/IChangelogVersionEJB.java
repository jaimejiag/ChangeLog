/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asesoreslocales.ChangelogVersonEJB;

import com.al.changelogdtos.ChangelogVersionDTO;
import java.util.List;

/**
 *
 * @author jaimejimenez
 */
public interface IChangelogVersionEJB {
    List<ChangelogVersionDTO> requestToGetAllVersions();
}
