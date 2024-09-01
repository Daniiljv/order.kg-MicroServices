package my.code.admin.services.impl;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import my.code.admin.configs.FireBaseConfig;
import my.code.admin.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final FireBaseConfig fireBaseConfig;

    @Override
    @SneakyThrows(IOException.class)
    public String uploadPositionImage(MultipartFile multipartFile) {
        FirebaseApp firebaseApp = fireBaseConfig.getFirebaseApp();
        String bucketName = "order-kg.appspot.com";

        Storage storage = StorageClient.getInstance(firebaseApp).bucket(bucketName).getStorage();

        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        File pngFile = File.createTempFile("position-image", "png");
        ImageIO.write(bufferedImage, "png", pngFile);

        String fileName = multipartFile.getOriginalFilename();
        String filePath = "position-image" + fileName;

        BlobId blobId = BlobId.of(bucketName, filePath);

        String token = UUID.randomUUID().toString();
        BlobInfo blobInfo = BlobInfo
                .newBuilder(blobId)
                .setContentType("image/png")
                .setMetadata(Map.of("firebaseStorageDownloadTokens", token))
                .build();

        Blob blob;
        try (FileInputStream fileInputStream = new FileInputStream(pngFile)) {
            blob = storage.create(blobInfo, fileInputStream);
        } catch (IOException ioException) {
            throw new IOException(ioException.getMessage());
        }

        if(pngFile.exists()){
            pngFile.delete();
        }

        return generateDownloadUrl(blob, token);
    }

    private String generateDownloadUrl(Blob blob, String token) {
        return "https://firebasestorage.googleapis.com/v0/b/"
                + blob.getBucket()
                + "/o/"
                + blob.getName().replace("/", "%2F")
                + "?alt=media&token="
                + token;
    }
}
