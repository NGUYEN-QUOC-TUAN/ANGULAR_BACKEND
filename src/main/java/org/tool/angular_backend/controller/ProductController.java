package org.tool.angular_backend.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tool.angular_backend.dto.ProductDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    //Hiển thị tất cả Categories
    @GetMapping("") //http://localhost:8088/api/v1/products?page=1&limit=10
    public ResponseEntity<String> getAllProducts(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return ResponseEntity.ok(String.format("getProducts, page = %d, limit = %d", page, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(@PathVariable("id") String productId) {
        return ResponseEntity.ok("Product with ID: " + productId);
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertProduct(@Valid @ModelAttribute ProductDTO productDTO,
                                           BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            List<MultipartFile> files = productDTO.getFiles();
            files = files == null ? new ArrayList<MultipartFile>() : files;
            for (MultipartFile file : files) {
                if(file.getSize() == 0){
                    continue;
                }
                // kiểm tra kích thước file và định dạng
                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large! Maximum size is 10MB");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
                }
                // Lưu file và cập nhật thumbnail trong DTO
                String fileName = storeFile(file);
                System.out.println(fileName);
                //Lưu vào đối tượng product trong DB => sẽ làm sau
                // Lưu vào bảng product_images
            }
            return ResponseEntity.ok("Product insert successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // Thêm UUID vào trước tên file để đảm bảo tê file là duy nhất
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        // Đường dẫn đầy đủ đến file
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok("This is updateProduct with id = " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok("This is deleteProduct with id = " + id);
    }
}
