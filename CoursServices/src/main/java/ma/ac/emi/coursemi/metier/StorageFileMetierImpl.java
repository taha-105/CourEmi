package ma.ac.emi.coursemi.metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
@Transactional
public class StorageFileMetierImpl implements StorageFileMetier{
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation = Paths.get("upload-dir");
	 

	@Override
	public String store(MultipartFile file) {
		init();
		try {
			String filename = file.getOriginalFilename();
			String modifiedFileName =FilenameUtils.getBaseName(filename)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
		      Files.copy(file.getInputStream(), this.rootLocation.resolve(modifiedFileName));
		      return modifiedFileName;
		    } catch (Exception e) {
		      throw new RuntimeException("Failed to Store This file !" + e);
		    }

	}

	@Override
	public void init() {
		
		try {
			boolean isDirExist = new File("upload-dir").exists();
			if(!isDirExist) Files.createDirectory(rootLocation);
		    } catch (IOException e) {
		      throw new RuntimeException("Could not initialize storage!" + e);
		    }
		
	}

	@Override
	public Resource loadFileAsResource(String fileName) throws FileNotFoundException {
		try {
            Path filePath = this.rootLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (Exception e) {
			throw new FileNotFoundException("File not found " + fileName);
		}
	}

}
