package com.dandelion.backend.service;

import com.dandelion.backend.entity.ItemImage;
import com.dandelion.backend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;


    public ItemImage save(ItemImage itemImage) {
        return imageRepository.save(itemImage);
    }

    public List<ItemImage> imageList () {
        return imageRepository.findAll();
    }

    public void write(MultipartFile file, Path dir, String newFileName) {

        String format = getFormat(file);
        Path filepath = Paths.get(dir.toString(), newFileName + format);

        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFormat (MultipartFile file) {
        String originalFileName = file.getContentType(); // image/jfif
        int dotPos = originalFileName.indexOf("/");
        String format = originalFileName.substring(dotPos + 1, originalFileName.length());
        // vue 에서  .jfif 는 읽기만하면 오류나서 추가함...
        if (format == "jfif") {
            format = "jpeg";
        }
        return "." + format;
    }

    public Path getAbsolutePath (String relativePath) {
        Path path = Paths.get(relativePath);
        return path.toAbsolutePath().normalize();
    }

}
