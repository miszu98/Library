package pl.wasko.Library.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.wasko.Library.DTO.Image;
import pl.wasko.Library.service.Impl.ImageUploaderServiceImpl;

import java.io.IOException;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/images")
public class ImagesController {

    private ImageUploaderServiceImpl uploaderService;

    @PostMapping("/")
    public ResponseEntity<Image> upload(@RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(new Image(uploaderService.uploadImage(file)));
    }
}
