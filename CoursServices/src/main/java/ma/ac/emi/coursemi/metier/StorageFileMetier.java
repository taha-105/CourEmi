package ma.ac.emi.coursemi.metier;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

import org.springframework.core.io.Resource;
public interface StorageFileMetier {
	
	public String store(MultipartFile file);
	public Resource loadFileAsResource(String fileName) throws FileNotFoundException;
	public void init();

}
