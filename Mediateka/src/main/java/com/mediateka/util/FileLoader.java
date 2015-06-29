package com.mediateka.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import org.apache.commons.io.FileUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

import com.mediateka.exception.WrongInputException;
import com.mediateka.model.enums.MediaType;

public class FileLoader {

	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 210;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 210;

	private List<String> defaultFileNames = new ArrayList<String>();
	private List<String> filePaths = new ArrayList<String>();
	private HashMap<String, String> parameterMap = new HashMap<String, String>();
	private List<MediaType> mediaTypes = new ArrayList<MediaType>();

	public boolean loadFile(HttpServletRequest request) throws ServletException {
		String folderName = "temp";
		String uploadFolder = request.getServletContext().getRealPath("")
				+ "media\\" + folderName;
		File source = new File(uploadFolder);
		if (source.exists() == false) {
			source.mkdir();
			new File(source.getAbsolutePath() + "\\images").mkdir();
			new File(source.getAbsolutePath() + "\\videos").mkdir();
			new File(source.getAbsolutePath() + "\\audios").mkdir();
		}

		boolean returnState = loadFile(request, folderName);
		String type = "";
		if (parameterMap.get("clubId") != null) {
			type = "club" + parameterMap.get("clubId");
		} else if (parameterMap.get("eventId") != null) {
			type = "event" + parameterMap.get("eventId");
		}
		System.out.println(type);
		System.out.println(type.replaceAll("[0-9]", "") + "\\" + type);
		type = type.replaceAll("[0-9]", "") + "\\" + type;
		String destFolder = request.getServletContext().getRealPath("")
				+ "media\\" + type;
		System.out.println(destFolder);
		File dest = new File(destFolder);
		for (int i = 0; i < filePaths.size(); i++) {
			System.out.println(filePaths.get(i));
			filePaths.set(i, filePaths.get(i).replace("temp", type));
			System.out.println(filePaths.get(i));
		}
		String typeAray[] = { "images", "videos", "audios" };
		try {
			for (int i = 0; i < typeAray.length; i++) {
				File[] fileList = new File(uploadFolder + "\\" + typeAray[i])
						.listFiles();
				for (File file : fileList) {
					FileUtils.moveFileToDirectory(file, new File(destFolder
							+ "\\" + typeAray[i]), false);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnState;

	}

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

				if (item.getSize() > 0 && !item.isFormField()) {

					if (item.getContentType()
							.substring(0, item.getContentType().indexOf('/'))
							.equals(item.getFieldName().toLowerCase()) == false) {
						System.out.println(item.getContentType().substring(0,
								item.getContentType().indexOf('/'))
								+ " " + item.getFieldName().toLowerCase());
						throw new WrongInputException("wrong input file type");
					}

					mediaTypes.add(MediaType.valueOf(item.getFieldName()
							.toUpperCase()));
					String fileName = SecurityStringGenerator
							.generateString(16);
					defaultFileNames.add(new File(item.getName()).getName());
					String extention = "";
					if (defaultFileNames.get(i).indexOf('.') != -1) {
						extention = defaultFileNames.get(i).substring(
								defaultFileNames.get(i).indexOf('.'));
					}
					String filePath = uploadFolder + "\\"
							+ item.getFieldName().toLowerCase() + "s"
							+ File.separator + fileName + extention;
					filePaths.add(filePath);
					File uploadedFile = new File(filePath);
					item.write(uploadedFile);
					isUploaded = true;
					i++;
					// open file
					Metadata metadata = new Metadata();
					try (FileInputStream fileInputStream = new FileInputStream(
							uploadedFile)) {
						AutoDetectParser parser = new AutoDetectParser();
						ContentHandler contenthandler = new BodyContentHandler();
						metadata.set(Metadata.RESOURCE_NAME_KEY,
								uploadedFile.getName());
						parser.parse(fileInputStream, contenthandler, metadata);
					}
					if (Files.probeContentType(Paths.get(filePath)).equals(
							metadata.get(Metadata.CONTENT_TYPE)) == false) {
						System.out.println(Files.probeContentType(Paths
								.get(filePath))
								+ " "
								+ item.getContentType()
								+ " " + metadata.get(Metadata.CONTENT_TYPE));
						uploadedFile.delete();
						throw new WrongInputException("wrong file type");
					}
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
		if (defaultFileNames.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		return defaultFileNames.get(0);
	}

	public void setDefaultFileName(String defaultFileName) {
		this.defaultFileNames.add(defaultFileName);
	}

	public String getFilePath() throws WrongInputException {
		if (filePaths.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		return filePaths.get(0);
	}

	public void setFilePath(String filePath) {
		this.filePaths.add(filePath);
	}

	public String getRelativePath() throws WrongInputException {
		if (filePaths.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		return filePaths.get(0).substring(filePaths.get(0).indexOf("media\\"));
	}

	public List<String> getAllRelativePathes() throws WrongInputException {
		if (filePaths.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		List<String> relativePathes = new ArrayList<String>();
		for (String path : filePaths) {
			relativePathes.add(path.substring(path.indexOf("media\\")));
		}
		return relativePathes;
	}

	public List<String> getAllFilePathes() throws WrongInputException {
		if (filePaths.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		return filePaths;
	}

	public List<String> getAllFileDefaultNames() throws WrongInputException {
		if (defaultFileNames.size() == 0) {
			throw new WrongInputException("wrong file type");
		}
		return defaultFileNames;
	}

	public List<MediaType> getMediaTypes() {
		return mediaTypes;
	}

	public MediaType getMediaType() {
		return mediaTypes.get(0);
	}

}