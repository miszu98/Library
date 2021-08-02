package pl.wasko.Library.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.EagerTransformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class ImageUploaderServiceImpl {
    private final Cloudinary cloudinary = new Cloudinary(
            ObjectUtils.asMap(
                    "cloud_name", "miszu",
                    "api_key", "218543362259791",
                    "api_secret", "c_nHz6jY4REIeZha9yeE415I2QQ",
                    "secure", true)
    );

    public String uploadImage(MultipartFile file) throws IOException {

        return (String) cloudinary
                .uploader()
                .upload(file.getBytes(), ObjectUtils.asMap(
                        "eager", Arrays.asList(
                                new EagerTransformation().width(400).height(300).crop("pad"),
                                new EagerTransformation().width(260).height(200).crop("crop").gravity("north")
                        )

                ))
                .get("url");
    }

}
