package project.hrms.core.adapters.imageManagement;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageUploadDownload  implements ImageServiceAdapter {

	@Override
	public String upload(String imagePath) throws IOException {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "batuhan-photos",
				"api_key", "693237567359286",
				"api_secret", "a633j2Wqv9VnIGaubkudHT9Aydo"));
		
		File file = new File("pictures/" + imagePath);
		
		Map	uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		
		return uploadResult.get("url").toString();
	}

}