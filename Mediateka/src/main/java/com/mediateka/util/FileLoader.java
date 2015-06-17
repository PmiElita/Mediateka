package com.mediateka.util;

import java.io.File;
import java.util.ArrayList;
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

	private List<String> defaultFileName = new ArrayList<String>();
	private List<String> filePath = new ArrayList<String>();
	private HashMap<String, String> parameterMap = new HashMap<String, String>();

	public boolean loadFile(HttpServletRequest request, String folderName)
			throws ServletException {
		boolean isUploaded = false;
		;

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

		File mediaDir = new File(request.getServletContext().getRealPath("")
				+ "media");
		if (!mediaDir.exists()) {
			mediaDir.mkdir();
		}

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
			int i = 0;
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();

				if (!item.isFormField()) {

					String fileName = SecurityStringGenerator
							.generateString(16);
					defaultFileName.add(new File(item.getName()).getName());
					String extention = "";
					if (defaultFileName.get(i).indexOf('.') != -1) {
						extention = defaultFileName.get(i).substring(
								defaultFileName.get(i).indexOf('.'));
					}
					filePath.add(uploadFolder + File.separator + fileName
							+ extention);
					File uploadedFile = new File(filePath.get(i));
					item.write(uploadedFile);
					isUploaded = true;
					i++;
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
		return parameterMap;
	}

	public void setParameterMap(HashMap<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public String getDefaultFileName() throws WrongInputException {
		if (defaultFileName.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		return defaultFileName.get(0);
	}

	public void setDefaultFileName(String defaultFileName) {
		this.defaultFileName.add(defaultFileName);
	}

	public String getFilePath() throws WrongInputException {
		if (filePath.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		return filePath.get(0);
	}

	public void setFilePath(String filePath) {
		this.filePath.add(filePath);
	}

	public String getRelativePath() throws WrongInputException {
		if (filePath.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		return filePath.get(0).substring(filePath.get(0).indexOf("media\\"));
	}

	public List<String> getAllRelativePathes() throws WrongInputException {
		if (filePath.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		List<String> relativePathes = new ArrayList<String>();
		for (String path : filePath) {
			relativePathes.add(path.substring(path.indexOf("media\\")));
		}
		return relativePathes;
	}

	public List<String> getAllFilePathes() throws WrongInputException {
		if (filePath.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		return filePath;
	}

	public List<String> getAllFileDefaultNames() throws WrongInputException {
		if (defaultFileName.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		return defaultFileName;
	}

}
