package cecs.scm;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

/**
 * AUTHORS:
 * 1. Douglas Choi 		- douglchoi@gmail.com
 * 2. Imanuel Kurniawan - imanuel.k09@hotmail.com
 * 3. Vincent Cheong 	- vincentkcheong@gmail.com
 * 
 * FILE DESCRIPTION:
 * Unit test for the artifact name generator
 */
public class ArtifactGeneratorTest {

	@Test
	public void generateArtifactNameTest() throws IOException {
		// Create a sample file
		File f = File.createTempFile("test", "HelloWorld.txt");
		f.deleteOnExit(); // for cleanup
		PrintWriter pw = new PrintWriter(f);
		pw.print("HELLO WORLD");
		pw.close();

		// Test the function
		ArtifactGenerator generator = new ArtifactGenerator();
		String name = generator.generateName(f);
		System.out.println("Generated artifact is " + name);
		
		assertTrue("5940.11.txt".equals(name));
	}
}
