/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.controllers;



/**
 *
 * @author Vladimir
 */


import com.cefisi.modeles.Equipe;
import com.cefisi.modeles.Personne;
import com.cefisi.modeles.UploadFile;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/*
 * Handles requests for the file upload page.
 */
@Controller
public class UploadController {
    
    
    @PersistenceContext
    private EntityManager entityManager;

	@RequestMapping(value = "/upload-{idEquipe}", method = RequestMethod.GET)
	public String showUploadForm(HttpServletRequest request,  ModelMap map , @PathVariable(value = "idEquipe") long idEquipe ) {
            Equipe equipe = entityManager.find(Equipe.class, idEquipe);
            map.put("equipe", equipe);
            map.put("action", "upload-"+idEquipe);
            map.put("titre", "Ajouter un document dans l'equipe "+equipe.getId());
		return "formUpload";
	}
    @Transactional
    @RequestMapping(value = "/upload-{idEquipe}", method = RequestMethod.POST)
    public String handleFileUpload(@Valid @ModelAttribute("uploadFile") UploadFile uploadFile,HttpServletRequest request, ModelMap map, 
            @RequestParam CommonsMultipartFile[] fileUpload, HttpSession session 
    , @PathVariable(value = "idEquipe") long idEquipe
    ) throws SQLException {
        Personne auteur =  (Personne) session.getAttribute("user"); 
        Equipe equipe = entityManager.find(Equipe.class, idEquipe);
            map.put("action", "upload-"+idEquipe);
            map.put("titre", "Ajouter un document dans l'equipe "+equipe.getId());
        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){
                 
                System.out.println("Saving file: " + aFile.getOriginalFilename() +aFile.getContentType());
                
               // UploadFile uploadFile = new UploadFile();
                uploadFile.setAuteur(auteur);
                uploadFile.setEquipe(equipe);
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setType(aFile.getContentType());
                uploadFile.setData(aFile.getBytes());
                uploadFile.setDateAjout(new Date(Calendar.getInstance().getTimeInMillis()));
                entityManager.persist(uploadFile); 
            }
        }
 
        return "redirect:/equipe-"+idEquipe;
    }	
 
    @RequestMapping(value = { "/download-document-{id}" }, method = RequestMethod.GET)
	public String downloadDocument( @PathVariable(value = "id") long id,HttpServletResponse response ,
              ModelMap map //,  BindingResult result
          ) throws  IOException {
		UploadFile document = entityManager.find(UploadFile.class, id);
		response.setContentType(document.getType());
        response.setContentLength(document.getData().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getFileName() +"\"");
 
        FileCopyUtils.copy(document.getData(), response.getOutputStream());
            map.put("action", "Download");
            map.put("titre", "Download a file"+document.getFileName());
 
 		return null;
	}


        
        @Transactional
    @RequestMapping(value = "/delete-document-{id}", method = RequestMethod.GET)
    public String deleteDoc(@Valid @ModelAttribute("uploadFile") UploadFile uploadFile,
            BindingResult result, ModelMap map, @PathVariable(value = "id") long id
    ) throws SQLException {
        uploadFile = entityManager.find(UploadFile.class, id);
        map.put("action", "Delete");
        map.put("titre", "Suprimer un document");

        if (result.hasErrors()) {
            System.out.println("erreurs");
        } else {
            String sql = "Delete FROM files_upload WHERE upload_id =:uploadId";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("uploadId", id);
            query.executeUpdate();
            System.out.println("ok");
            map.put("message", "la doc  a été bien suprimée");
        }
        return "redirect:/equipe-"+uploadFile.getEquipe().getId();
    }
    
    
    
    
    
    @RequestMapping(value = "/upload-{idEquipe}/{id}-modify", method = RequestMethod.GET)
	public String askModifyFile(HttpServletRequest request,  ModelMap map ,@PathVariable(value = "idEquipe") long idEquipe , @PathVariable(value = "id") long id ) {
            UploadFile uploadFile = entityManager.find(UploadFile.class, id);
            map.put("equipe", uploadFile);
            map.put("action", "upload-"+idEquipe+"/"+id+"-modify");
            map.put("titre", "Modifier le doc "+uploadFile.getId());
		return "formUploadMod";
	}
    @Transactional
    @RequestMapping(value = "/upload-{idEquipe}/{id}-modify", method = RequestMethod.POST)
    public String doModifyFile(@Valid /* @ModelAttribute("uploadFile") */UploadFile uploadFile,
            ModelMap map, 
            BindingResult result,
            HttpServletRequest request, 

            @RequestParam CommonsMultipartFile[] fileUpload, HttpSession session, @PathVariable(value = "idEquipe") long idEquipe ,@PathVariable(value = "id") long id 
    ) throws SQLException {
        Personne auteur =  (Personne) session.getAttribute("user"); 
            map.put("action", "upload-"+idEquipe/id+"-modify");
            map.put("titre", "Modifier le doc"+uploadFile.getId());
        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){
                 
                System.out.println("update file: " + aFile.getOriginalFilename() +aFile.getContentType());
                
                String sql = "UPDATE files_upload SET id_persoAjour=:idPersoMisAjr, file_name =:fileName, type=:type ,file_data=:fileData ,date_mise_jr=:dateMiseJr WHERE upload_id=:id";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("idPersoMisAjr",auteur.getIdPersonne())
                    .setParameter("fileName",aFile.getOriginalFilename())
                    .setParameter("type",aFile.getContentType())
                    .setParameter("fileData",aFile.getBytes())
                    .setParameter("dateMiseJr",new Date(Calendar.getInstance().getTimeInMillis()))
                    .setParameter("id", id);
                 query.executeUpdate();
               

            }
        }
 
        return "redirect:/equipe-"+idEquipe;
    }	
    
}

