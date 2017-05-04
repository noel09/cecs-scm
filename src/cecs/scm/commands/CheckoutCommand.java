// Bugs: Will not function properly if there are spaces in the path or file name
package cecs.scm.commands;

import java.io.*;

import cecs.scm.ActivityLogger;

/**
 * AUTHORS:
 * 1. Douglas Choi 		- douglchoi@gmail.com
 * 2. Imanuel Kurniawan - imanuel.k09@hotmail.com
 * 3. Vincent Cheong 	- vincentkcheong@gmail.com
 *
 * FILE DESCRIPTION:
 * Command to create a new repository from an existing source.
 */

public class CheckoutCommand implements Command {

    private String repoFolder;
    private String manifestDateTime;
    private String manifest;
    private String tgtFolder;
    private ActivityLogger logger;

    /**
     * @param repoFolder: Root directory of the repository
     * @param tgtFolder: Empty target directory
     * @param manifestDateTime: Date_Time of Manifest (YYYYMMdd_hhmm)
     */
    public CheckoutCommand(String repoFolder, String tgtFolder, String manifestDateTime) {
        this.repoFolder = repoFolder;
        this.tgtFolder = tgtFolder;
        this.manifestDateTime = manifestDateTime;
        manifest = repoFolder + "\\activity\\manifest_" + manifestDateTime;
    }

    @Override
    public void execute() {
        // Check if manifest exists
        File manifestFile = new File(manifest);
        if (!manifestFile.exists()) {
            System.out.println("Manifest not found.");
            return;
        }
        File tgtFile = new File(tgtFolder);
        if (!tgtFile.exists()) {
            tgtFile.mkdirs();
        }

        // Create manifest
        logger = new ActivityLogger(repoFolder);
        logger.writeLine("CHECKOUT " + repoFolder + " " + tgtFolder + " " + manifestDateTime);

        System.out.println("Checking-out manifest: " + manifestFile);
        System.out.println("Writing to target directory: " + tgtFolder);

        processManifest();
    }


    /**
     * Processes each line of the manifest. Stops if it hits an empty line or if reaches end of file.
     */
    private void processManifest() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(manifest));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty())
                    return;
                processLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes a single line.
     * @param line: A single line from the manifest
     */
    private void processLine(String line) throws IOException {
        String delimiter = "[ ]+"; // Split tokens by white spaces
        String[] token = line.split(delimiter);

        if (!token[0].equals(">")) // Do nothing unless it's a copied file
            return;

        // Token Structure
        // token[0] = ">"
        // token[1] = original file name
        // token[2] = artifact file name
        // token[3] = target location
        String repoStr = token[3] + "\\" + token[2]; // "...\subfolder\file.txt\1.1.txt"
        String localPath = token[3].replace(repoFolder, ""); // Extract local hierarchy: "\subfolder\file.txt"
        String tgtStr = tgtFolder + localPath;

        // Generate subfolders if they don't exist
        File tgtDir = new File(tgtStr.substring(0, (tgtStr.length() - token[1].length())));
        if (!tgtDir.exists())
            tgtDir.mkdirs();

        // Copy the file
        InputStream is = new FileInputStream(new File(repoStr));
        OutputStream os = new FileOutputStream(new File(tgtStr));
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = is.read(buffer)) > 0) {
            os.write(buffer, 0, len);
        }
        is.close();
        os.close();

        logger.writeLine("> " + token[2] + " " + tgtStr);
        System.out.println("Copied file: " + token[1]);
    }
}
