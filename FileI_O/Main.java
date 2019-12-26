package fa.edu.javacore.fileIo;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {

	public static void main(String[] args) {
		creatingPaths();
		creatingEmptyFiles();
		creatingDirectories();
		creatingTemporaryFileOrDirectory();
		copyFileOrDirectory();
		deleteFileOrDirectory();
		movingFileOrDirectory();
		workingWithFileSystem();
	}
	
	static void creatingPaths() {
		// 1. Creating Paths
		// Create a path which is relative to the current directory:
		Path path1 = Paths.get("IoExamples");
		
		// Create an absolute path on Windows:
		Path path2 = Paths.get("E:\\Java\\JavaSE\\IO");
		
		// Create a relative path: Java/NIOExamples.java
		Path path4 = Paths.get("Java", "IOExamples.java");		
	}
	
	// 2. Creating Empty Files
	static void creatingEmptyFiles() {
		Path file = Paths.get("Hello.java");
		try {
		      Files.createFile(file);
		} catch (FileAlreadyExistsException ex) {
		      System.err.format("File %s already exists%n", file);
		} catch (IOException ex) {
		      System.err.format("Error creating file %s%n", file);
		}

	}
	
	// 3. Creating Directories
	static void creatingDirectories() {
		Path dirs = Paths.get("E:\\JavaCode\\IO\\Directories");
		try {
			Files.createDirectories(dirs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 4. Creating a Temporary File or Directory
	static void creatingTemporaryFileOrDirectory() {
		Path dir = Paths.get(System.getProperty("user.cuongtv21"));
		try {
		      Files.createTempFile(dir, "CuongTV21", ".log");
		} catch (IOException ex) {
		      System.err.format("Error creating temp file");
		}
	}
	
	// 5. Copying a File or Directory
	static void copyFileOrDirectory() {
		Path source = Paths.get("Remember.mp4");
		Path target = Paths.get("D:\\Remember.mp4");
		try {
		      Files.copy(source, target,
		            StandardCopyOption.COPY_ATTRIBUTES,
		            StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
		      System.err.format("Error copying file");
		}

	}
	
	// 6. Deleting a File or Directory
	static void deleteFileOrDirectory() {
		Path path = Paths.get("Gau.mp4");
		try {
		      Files.delete(path);
		} catch (NoSuchFileException ex) {
		      System.out.format("%s: no such file or directory", path);
		} catch (DirectoryNotEmptyException ex) {
		      System.out.format("%s not empty", path);
		} catch (IOException ex) {
		      System.err.println(ex);
		}
	}
	
	// 7. Moving a File or Directory
	static void movingFileOrDirectory() {
		Path source = Paths.get("ReadTextFile.java");
		Path target = Paths.get("e:\\Java\\JavaSE\\ReadTextFile.java");
		try {
		      Files.move(source, target);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
	
	// 8. Working with the File System
	static void workingWithFileSystem() {
		try {
		      FileSystem fs = FileSystems.getDefault();
		      for (FileStore store: fs.getFileStores()) {
		            System.out.println(store);
		            System.out.format("\tTotal Space: %d%n", store.getTotalSpace());
		            System.out.format("\tFree Space: %d%n", store.getUnallocatedSpace());
		      }
		} catch (IOException ex) {
		      System.err.println(ex);
		}

	}
}
