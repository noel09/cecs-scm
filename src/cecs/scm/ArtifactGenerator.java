package cecs.scm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * AUTHORS:
 * 1. Douglas Choi 		- douglchoi@gmail.com
 * 2. Imanuel Kurniawan - imanuel.k09@hotmail.com
 * 3. Vincent Cheong 	- vincentkcheong@gmail.com
 * 
 * FILE DESCRIPTION:
 * Creates a new leaf folder and an artifact file of the old versions for each file
 */


public class ArtifactGenerator {

	
	private static final int[] CHKSUM_WEIGHTS = new int[] { 1, 3, 11, 17 };
	
	public ArtifactGenerator() {}
	
	/**
	 * The code name will be a rolling 4-byte weighted checksum of all the characters (bytes) in the file
	 * followed by a dot and the integer file size. The 4 weights by which each 4 characters are multiplied are
	 * 1, 3, 11, and, 17.
	 */
	public String generateName(File file) throws IOException {
		InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
		
		int wtIdx = 0;
		int ch;
		int checksum = 0;
		int size = 0;
		
		// Calculate the artifact
		while ((ch = reader.read()) > -1) {
			checksum += ch * CHKSUM_WEIGHTS[wtIdx];
			size++;
			wtIdx = wtIdx < CHKSUM_WEIGHTS.length - 1? wtIdx + 1 : 0; 
		}
		reader.close();
		
		// Assemble the name
		String[] fileParts = file.getName().split("\\.");
		if (fileParts.length > 1) {
			// Append the extension to the full artifact name 
			return checksum + "." + size + "." + fileParts[fileParts.length - 1];
		} else {
			// No extension, return just the full artifact name
			return checksum + "." + size;
		}
	}
	
	/**
	 * Copies the src file to the leaf folder 
	 * @param srcFile
	 * @param leafFolder
	 * @return the artifact file
	 */
	public File createArtifact(File srcFile, File leafFolder) throws IOException {
		
		// Create a new artifact file
		if (!leafFolder.exists()) {
			leafFolder.mkdir();
		}
		File artifactFile = new File(leafFolder + File.separator + generateName(srcFile));
		
		// copy the file
		InputStream is = new FileInputStream(srcFile);
		OutputStream os = new FileOutputStream(artifactFile);
		
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = is.read(buffer)) > 0) {
			os.write(buffer, 0, len);
		}
		
		is.close();
		os.close();

		return artifactFile;
	}
}

