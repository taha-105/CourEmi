package ma.ac.emi.coursemi.controllers;



import java.io.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.ac.emi.coursemi.metier.StorageFileMetier;


@RestController
@RequestMapping("/upload-dir")
public class FileController {
	@Autowired
	private StorageFileMetier storageFileMetier;
	
	@GetMapping("/{fileName:.+}")
    public ResponseEntity<Object> downloadFile(@PathVariable String fileName){
		try {
			Resource file= this.storageFileMetier.loadFileAsResource(fileName);

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
	                .body(file);
		} catch (Exception e) {
			 return new ResponseEntity<>("une erreur est survenue lors de téléchargement de l'image : "+e,HttpStatus.NOT_FOUND);
		}
		
		
	}

}
