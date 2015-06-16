package com.mediateka.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mediateka.exception.WrongInputException;

public class FileLoader {

	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 210;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 210;

	private String defaultFileName;
	private String filePath;
	private HashMap<String, String> parameterMap = new HashMap<String, String>();

	public boolean loadFile(HttpServletRequest request, String folderName)
			throws ServletException {
		boolean isUploaded = false;

		String fileName = SecurityStringGenerator.generateString(16);

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (!isMultipart) {
			return false;
		}

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Sets the size threshold beyond which files are written directly to
		// disk.
		factory.setSizeThreshold(MAX_MEMORY_SIZE);

		// Sets the directory used to temporarily store files that are larger
		// than the configured size threshold. We use temporary directory for
		// java
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		// constructs the folder where uploaded file will be stored
		String uploadFolder = request.getServletContext().getRealPath("")
				+ "media\\" + folderName;

		File fileDir = new File(uploadFolder);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(MAX_REQUEST_SIZE);

		try {
			// Parse the request
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (!item.isFormField()) {
					defaultFileName = new File(item.getName()).getName();
					String extention = defaultFileName
							.substring(defaultFileName.indexOf('.'));
					filePath = uploadFolder + File.separator + fileName
							+ extention;
					File uploadedFile = new File(filePath);
					item.write(uploadedFile);
					isUploaded = true;
				} else {

					parameterMap.put(item.getFieldName(),
							item.getString("UTF-8"));
				}
			}

		} catch (FileUploadException ex) {
			throw new ServletException(ex);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		return isUploaded;

	}

	public HashMap<String, String> getParameterMap() {
		if (parameterMap == null) {
			throw new WrongInputException("Wrong paramet map. ");
		}
		return parameterMap;
	}

	public void setParameterMap(HashMap<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public String getDefaultFileName() {
		if (defaultFileName == null) {
			throw new WrongInputException("Wrong file name. ");
		}
		return defaultFileName;
	}

	public void setDefaultFileName(String defaultFileName) {
		this.defaultFileName = defaultFileName;
	}

	public String getFilePath() {
		if (filePath == null) {
			throw new WrongInputException("Wrong file type. ");
		}
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRelativePath() {
		if (filePath == null) {
			throw new WrongInputException("Wrong file type. ");
		}
		return filePath.substring(filePath.indexOf("media"));
	}

}
