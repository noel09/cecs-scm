package cecs.scm.commands;

import cecs.scm.ActivityLogger;
import cecs.scm.ArtifactGenerator;

import java.io.File;
import java.io.IOException;

public class CheckinCommand extends CopyFile implements Command {
    /**
     * @param srcFolder: Root directory of the source
     * @param repoFolder: Root directory of the repository
     */
    public CheckinCommand(String srcFolder, String repoFolder) {
        super(srcFolder, repoFolder);
    }

    @Override
    public void execute() {
        CloneFiles("check-in");
    }

    @Override
    protected void processFile(File srcFile, File leafFolder) {
        ArtifactGenerator gen = new ArtifactGenerator();
        try {
            File artifact = new File(leafFolder + File.separator + gen.generateName(srcFile));
            //Create a backup of the file only if this version of the file doesn't exist in the repo
            if(!artifact.exists() && !artifact.isDirectory()){
                gen.createArtifact(srcFile, leafFolder);
            }
            logger.writeLine("> " + srcFile.getName() + " " + artifact.getName() + " " + leafFolder);
            System.out.println("Recorded artifact: " + artifact);
        } catch (IOException e) {
            System.err.println("Could not create artifact for : " + srcFile);
        }
    }
}
