//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.unit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PomHandle {
    public static final Logger log = LoggerFactory.getLogger(PomHandle.class);

    public PomHandle() {
    }

    public static void writePom(File pomFile, Model model) {
        Writer writer = null;

        try {
            writer = new FileWriter(pomFile);
            MavenXpp3Writer mavenXpp3Writer = new MavenXpp3Writer();
            mavenXpp3Writer.write(writer, model);
        } catch (IOException var13) {
            var13.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException var12) {
                log.error("重构Pom文件时，关闭流失败");
            }

        }

    }

    public Model parsePomFileToModel(File pomPath) {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(pomPath);
            model = reader.read(fileReader);
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException var13) {
                    log.error("解析pom文件时，关闭流失败");
                }
            }

        }

        return model;
    }
}
