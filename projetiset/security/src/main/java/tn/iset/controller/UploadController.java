package tn.iset.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import tn.iset.model.User;
import tn.iset.repository.UserRepository;


@CrossOrigin("*")
@Controller
@RequestMapping("/post")
public class UploadController {

	@Autowired
	StorageService storageService;
@Autowired
UserRepository userRepo;
	List<String> files = new ArrayList<String>();

	@PostMapping("/upload/{username}")
	public ResponseEntity<String> handleFileUpload(@PathVariable String username,@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.store(file);
			files.add(file.getOriginalFilename());
			User u=userRepo.findByUsername(username).get();
			u.setImage(file.getOriginalFilename());
			userRepo.save(u);
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			
			
			
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	@PostMapping("/uploadFile")
	public ResponseEntity<String> FileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.store(file);
			files.add(file.getOriginalFilename());
			message =  file.getOriginalFilename() ;
			
			
			
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@GetMapping("/getallfiles")
	public ResponseEntity<List<String>> getListFiles(Model model) {
		List<String> fileNames = files
				.stream().map(fileName -> MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(fileNames);
	}

	private final Path rootLocation = Paths.get("upload-dir");
	@GetMapping("/files/{filename}")
	@ResponseBody
	public Map<String, String> getFile(@PathVariable String filename) throws IOException {
		/*Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);*/
		 File file;
		  Map<String, String> jsonMap=new HashMap<>();
		try {
			file = Paths.get("upload-dir/"+filename).toFile();
			String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));

			   jsonMap.put("content", encodeImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		 
		return jsonMap;
	
	}
	@GetMapping("/image/{username}")
	@ResponseBody
	public ResponseEntity<String>getImage(@PathVariable String username){
		User u=userRepo.findByUsername(username).get();
		String filename=MvcUriComponentsBuilder
				.fromMethodName(UploadController.class, "getFile", u.getImage()).build().toString();
		return ResponseEntity.ok().body(filename);

	}
}
