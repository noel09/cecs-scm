package cecs.scm.commands;

import cecs.scm.ActivityLogger;
import cecs.scm.ArtifactGenerator;
import java.io.File;
import java.io.IOException;

/**
 * AUTHORS:
 * 1. Douglas Choi 		- douglchoi@gmail.com
 * 2. Imanuel Kurniawan - imanuel.k09@hotmail.com
 * 3. Vincent Cheong 	- vincentkcheong@gmail.com
 *
 * FILE DESCRIPTION:
 * Command to copy files from source directory to target directory
 */

public class CopyFile {

    protected String srcFolder;
    protected String repoFolder;
    protected ActivityLogger logger;

    public CopyFile(String srcFolder, String repoFolder) {
        this.srcFolder = srcFolder;
        this.repoFolder = repoFolder;
    }

    /**
     * Recursively traverse the file tree
     * @param srcTree
     * @param repoRoot
     */
    private void traverseTree(File srcTree, String repoRoot) {
        // Iterate through each file
        File[] files = srcTree.listFiles();
        for (File f : files) {
            // create a subfolder
            File subFolder = new File(repoRoot + File.separator + f.getName());
            subFolder.mkdir();

            if (f.isDirectory()) {
                // Continue down the tree
                traverseTree(f, subFolder.getAbsolutePath());
            } else {
                // Handle as a file
                processFile(f, subFolder);
            }
        }
    }

    /**
     * Creates a new leaf folder containing the artifacts of the original file,
     * logs the activity to the manifest file
     * @param srcFile
     * @param leafFolder
     */
    protected void processFile(File srcFile, File leafFolder) {
        ArtifactGenerator gen = new ArtifactGenerator();
        try {
            File artifact = gen.createArtifact(srcFile, leafFolder);
            logger.writeLine("> " + srcFile.getName() + " " + artifact.getName() + " " + leafFolder);
            System.out.println("Created artifact: " + artifact);
        } catch (IOException e) {
            System.err.println("Could not create artifact for : " + srcFile);
        }
    }

    protected void CloneFiles(String commandPar)
    {
        // Make repo directory if doesn't exist
        File repoFile = new File(repoFolder);
        if (!repoFile.exists()) {
            repoFile.mkdirs();
        }

        // create manifest
        logger = new ActivityLogger(repoFolder);
        logger.writeLine(commandPar.toUpperCase() + " " + srcFolder + " " + repoFolder);

        // TODO: check if src folder exists
        File srcFile = new File(srcFolder);
        System.out.println("Copying source directory: " + srcFile);

        // TODO: check if the repo folder contains contents
        System.out.println("Writing to repository directory: " + repoFolder);
        traverseTree(srcFile, repoFolder);
    }
}//End class
