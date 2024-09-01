package my.code.admin.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {

    String uploadPositionImage(MultipartFile multipartFile);
}
