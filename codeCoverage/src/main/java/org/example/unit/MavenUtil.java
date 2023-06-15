//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.unit;

import java.io.File;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.example.SingleModuleMojo;

public class MavenUtil {
    private SingleModuleMojo mojo;
    private static MavenUtil instance = new MavenUtil();

    public MavenUtil() {
    }

    public static MavenUtil i() {
        return instance;
    }

    public void setMojo(SingleModuleMojo mojo) {
        this.mojo = mojo;
    }

    public SingleModuleMojo getMojo() {
        return this.mojo;
    }

    public MavenProject getProject() {
        return this.mojo.project;
    }

    public String getProjectPom() {
        return this.mojo.project.getFile().getAbsolutePath();
    }

    public String getProjectGroupId() {
        return this.mojo.project.getGroupId();
    }

    public String getProjectArtifactId() {
        return this.mojo.project.getArtifactId();
    }

    public String getProjectVersion() {
        return this.mojo.project.getVersion();
    }

    public String getMvnRep() {
        return this.mojo.localRepository.getBasedir() + File.separator;
    }

    public File artifact_path(Artifact artifact) {
        String classifier = artifact.getClassifier();
        if (classifier != null && classifier != "") {
            classifier = "-" + classifier;
        } else {
            classifier = "";
        }

        String localDirPath = this.getMvnRep() + artifact.getGroupId().replace(".", File.separator) + File.separator + artifact.getArtifactId() + File.separator + artifact.getVersion() + File.separator + artifact.getArtifactId() + "-" + artifact.getVersion() + classifier + ".jar";
        return new File(localDirPath);
    }
}
