//package com.example.demo;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.demo.domain.Bookgenre;
//import com.example.demo.domain.Usermember;
//
//@RestController
//public class FirstController {
//	@Autowired
//	UserRepository use;
//
//	// 파일이 들어갈 위치
//    private static final String uploadDir = "C:\\Users\\nclab\\Desktop\\imgs";
//	
//    @GetMapping("/Bookgenre") // GET 요청과 "/greeting" 경로에 대한 매핑
//    public List<Bookgenre> greeting() {
//    	
//    	List<Bookgenre> gelist = use.BookgenreList();
//    	
//        return gelist;
//    }
//         
//    @PostMapping("/login")
//	public Usermember login(Usermember usermember) {
//    	Usermember usermem = use.login(usermember);
//	    return usermem;
//	}
//    
//    @PostMapping("/upload")
//    public String handleImageUpload(@RequestParam("image") MultipartFile imageFile, @RequestParam("genre") String genre) {
//        try {
//        	UUID uid = UUID.randomUUID();
//            String fileName = uid.toString() + "_" + imageFile.getOriginalFilename();
//            String filePath = uploadDir + File.separator + fileName;
//            // 이미지 파일 저장
//            File dest = new File(filePath);
//            imageFile.transferTo(dest);
//            System.out.println(genre);
//            return "이미지 업로드 성공: " + fileName;
//        } catch (IOException e) {
//            return "이미지 업로드 실패: " + e.getMessage();
//        }
//    }
//}
//
