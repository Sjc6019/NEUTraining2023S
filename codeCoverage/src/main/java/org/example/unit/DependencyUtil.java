//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.unit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Exclusion;
import org.apache.maven.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DependencyUtil {
    public static final Logger log = LoggerFactory.getLogger(DependencyUtil.class);

    public DependencyUtil() {
    }

    public String print_getDependency(Dependency dependency) {
        String classifier = dependency.getClassifier();
        if (classifier != null && classifier != "") {
            classifier = classifier + ":";
        } else {
            classifier = "";
        }

        return dependency.getGroupId() + ":" + dependency.getArtifactId() + ":" + classifier + dependency.getVersion() + ":" + dependency.getScope();
    }

    public Model removed(Model model, Dependency removeDependency) {
        Iterator var3 = model.getDependencies().iterator();

        while(var3.hasNext()) {
            Dependency dependency = (Dependency)var3.next();
            if (dependency.getGroupId().equals(removeDependency.getGroupId()) && dependency.getArtifactId().equals(removeDependency.getArtifactId())) {
                model.removeDependency(dependency);
                break;
            }
        }

        return model;
    }

    public Model excluded(Model model, Dependency modifyDependency, Exclusion exclusion, boolean addExclusion) {
        Iterator var5 = model.getDependencies().iterator();

        while(var5.hasNext()) {
            Dependency dependency = (Dependency)var5.next();
            if (dependency.getGroupId().equals(modifyDependency.getGroupId()) && dependency.getArtifactId().equals(modifyDependency.getArtifactId())) {
                if (addExclusion) {
                    if (!this.hasExclusions(dependency, exclusion)) {
                        dependency.addExclusion(exclusion);
                    }
                } else if (this.hasExclusions(dependency, exclusion)) {
                    dependency.removeExclusion(exclusion);
                }
                break;
            }
        }

        return model;
    }

    public boolean getModelDependencyExclusion(Model model, Dependency modifyDependency, Exclusion exclusion) {
        Iterator var4 = model.getDependencies().iterator();

        Dependency dependency;
        do {
            if (!var4.hasNext()) {
                return false;
            }

            dependency = (Dependency)var4.next();
        } while(!dependency.getGroupId().equals(modifyDependency.getGroupId()) || !dependency.getArtifactId().equals(modifyDependency.getArtifactId()));

        return dependency.getExclusions().contains(exclusion);
    }

    public boolean hasExclusions(Dependency dependency, Exclusion excluded) {
        Iterator var3 = dependency.getExclusions().iterator();

        Exclusion exclusion;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            exclusion = (Exclusion)var3.next();
        } while(!exclusion.getGroupId().equals(excluded.getGroupId()) || !exclusion.getArtifactId().equals(excluded.getArtifactId()));

        return true;
    }

    public boolean dependencyManagement(Model model, Dependency verifyDependency, boolean flag) {
        Iterator var4 = model.getDependencyManagement().getDependencies().iterator();

        Dependency dependency;
        do {
            if (!var4.hasNext()) {
                return false;
            }

            dependency = (Dependency)var4.next();
        } while(!dependency.getGroupId().equals(verifyDependency.getGroupId()) || !dependency.getArtifactId().equals(verifyDependency.getArtifactId()) || !dependency.getVersion().equals(verifyDependency.getVersion()));

        if (flag) {
            model.getDependencyManagement().removeDependency(dependency);
        }

        return true;
    }

    public String isUsedSelfIndirectDependency(Set<String> usedSelfDependency, String dependency) {
        Iterator var3 = usedSelfDependency.iterator();

        String one_usedSelfDependency;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            one_usedSelfDependency = (String)var3.next();
        } while(!one_usedSelfDependency.split(":")[0].equals(dependency.split(":")[0]) || !one_usedSelfDependency.split(":")[1].equals(dependency.split(":")[1]));

        return one_usedSelfDependency;
    }

    public String dependencyFormat(String dependency) {
        String[] temp = dependency.split(":");
        return temp[0] + ":" + temp[1] + ":" + temp[2];
    }

    public Set<String> dependencySetFormat(Set<String> dependencySet) {
        Set<String> dependencySetFormat = new HashSet();
        Iterator var3 = dependencySet.iterator();

        while(var3.hasNext()) {
            String dependency = (String)var3.next();
            dependencySetFormat.add(this.dependencyFormat(dependency));
        }

        return dependencySetFormat;
    }

    public Map<String, String> dependencySetFormatMap(Set<String> dependencySet) {
        Map<String, String> dependencySetFormat = new HashMap();
        Iterator var3 = dependencySet.iterator();

        while(var3.hasNext()) {
            String dependency = (String)var3.next();
            dependencySetFormat.put(this.dependencyFormat(dependency), dependency);
        }

        return dependencySetFormat;
    }

    public Dependency getModelDependency(Model model, Dependency modifyDependency) {
        Iterator var3 = model.getDependencies().iterator();

        Dependency dependency;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            dependency = (Dependency)var3.next();
        } while(!dependency.getGroupId().equals(modifyDependency.getGroupId()) || !dependency.getArtifactId().equals(modifyDependency.getArtifactId()));

        return dependency;
    }
}

