//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.unit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.maven.project.MavenProject;

public class Write {
    public Write() {
    }

    public static void writeResult(MavenProject project, String outDir, String coreCoverage) {
        Config.outDir = outDir;
        String projectName = project.getGroupId() + ":" + project.getArtifactId() + ":" + project.getVersion();
        outDir = Config.getOutDir() + File.separator + projectName.replaceAll("\\p{Punct}", "") + File.separator + projectName.replaceAll("\\p{Punct}", "") + File.separator;
        if (!(new File(outDir)).exists()) {
            (new File(outDir)).mkdirs();
        }

        String resTxt = outDir + "res.txt";
        File result = new File(resTxt);
        if (result.exists()) {
            result.delete();
        }

        PrintWriter printer = null;

        try {
            printer = new PrintWriter(new BufferedWriter(new FileWriter(resTxt, true)));
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        printer.println("ProjectInfo>>>>" + project.getBasedir().getAbsolutePath());
        printer.println("ProjectName>>>>" + projectName);
        printer.println("CodeCoverage>>>>" + coreCoverage);
        printer.close();
    }
}
