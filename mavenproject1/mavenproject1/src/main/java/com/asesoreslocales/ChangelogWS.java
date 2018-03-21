package com.asesoreslocales;

import com.al.changelogdtos.ChangelogCambioDTO;
import com.asesoreslocales.ChangelogVersonEJB.ChangelogVersionEJB;
import com.al.changelogdtos.ChangelogVersionDTO;
import com.asesoreslocales.ChangelogChangesEJB.ChangelogChangesEJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import com.google.gson.Gson;;
import java.sql.SQLException;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jaimejimenez
 */
@Stateless
@Path("/log")
public class ChangelogWS {   

    @GET
    @Path("/versions")
    public String versions() throws SQLException {
        ChangelogVersionEJB versionEJB = new ChangelogVersionEJB();
        List<ChangelogVersionDTO> versionList = null;
        String result = null;
        
        versionList = versionEJB.requestToGetAllVersions();
        result = new Gson().toJson(versionList);

        return result;
    }
    
    
    @GET
    @Path("/changes")
    public String changes() throws Exception {
        ChangelogChangesEJB changesEJB = new ChangelogChangesEJB();
        List<ChangelogCambioDTO> changesList = null;
        String result = null;
        
        try {
            changesList = changesEJB.requestToGetAllChanges();
            result = new Gson().toJson(changesList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
}
