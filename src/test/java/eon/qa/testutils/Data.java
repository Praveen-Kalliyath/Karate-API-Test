package eon.qa.testutils;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public interface Data {

	/**
	 * FOLDER SECTION
	 */
	public final Path PARENT_FOLDER_PATH = FileSystems.getDefault().getPath(".").toAbsolutePath().getParent();

	public final String SRC_JAVA_PATH = PARENT_FOLDER_PATH + "\\src\\test\\java";

	public final String DATA_FILES_FOLDER = SRC_JAVA_PATH + "\\eon\\qa\\datafiles";

	public final String EXCEL_FOLDER = DATA_FILES_FOLDER + "\\excel\\";
	
	public final String JSON_FOLDER = DATA_FILES_FOLDER + "\\jsons\\";
	
	public final String PROPERTIESFILES_FOLDER = DATA_FILES_FOLDER + "\\propertiesfiles\\";
	
	/**
	 * API BASE PATH SECTION
	 */
	
	public final String GET_USER_DETAILS = "/users";
	public final String PUT_USER_DETAILS = "/posts/1";
	
	
	
}
