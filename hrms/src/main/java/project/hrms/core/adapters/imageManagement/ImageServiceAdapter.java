package project.hrms.core.adapters.imageManagement;

import java.io.IOException;

public interface ImageServiceAdapter {

	String upload(String imagePath) throws IOException;
}
