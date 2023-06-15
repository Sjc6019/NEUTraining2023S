package org.example;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Build;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.plugin.AbstractMojo;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.example.unit.Handle;
import org.example.unit.MavenUtil;
import org.example.unit.PomHandle;
import org.example.unit.Write;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mojo(
        name = "start",
        defaultPhase = LifecyclePhase.VALIDATE,
        requiresDependencyCollection = ResolutionScope.COMPILE_PLUS_RUNTIME,
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME,
        threadSafe = true
)
public class SingleModuleMojo extends AbstractMojo {
    public static final Logger log = LoggerFactory.getLogger(SingleModuleMojo.class);
    @Parameter(
            defaultValue = "${project}",
            readonly = true,
            required = true
    )
    public MavenProject project;
    @Parameter(
            defaultValue = "${session}",
            readonly = true
    )
    public MavenSession session;
    @Parameter(
            defaultValue = "${project.compileSourceRoots}",
            readonly = true,
            required = true
    )
    public List<String> compileSourceRoots;
    @Parameter(
            property = "reactorProjects",
            required = true,
            readonly = true
    )
    public List<MavenProject> reactorProjects;
    @Parameter(
            defaultValue = "${localRepository}",
            readonly = true
    )
    public ArtifactRepository localRepository;
    @Parameter(
            property = "outDir",
            defaultValue = "null"
    )
    public String outDir;

    public SingleModuleMojo() {
    }

    public void execute() {
        MavenUtil.i().setMojo(this);
        String pckType = this.project.getPackaging();
        Model model = this.project.getModel();
        List<String> args = ManagementFactory.getRuntimeMXBean().getInputArguments();
        boolean isDebug = true; //默认为false
        Iterator var5 = args.iterator();

        while(var5.hasNext()) {
            String arg = (String)var5.next();
            if (arg.startsWith("-Xrunjdwp") || arg.startsWith("-agentlib:jdwp")) {
                isDebug = true;
                break;
            }
        }

        if ((isDebug || Handle.mvnTest(model.getPomFile())) && ("jar".equals(pckType) || "war".equals(pckType) || "maven-plugin".equals(pckType))) {
            Plugin jacoco = new Plugin();
            jacoco.setArtifactId("jacoco-maven-plugin");
            jacoco.setGroupId("org.jacoco");
            jacoco.setVersion("0.8.8");
            Handle h = new Handle();
            PluginExecution pluginExecution1 = h.setPluginExecution("default-prepare-agent", "prepare-agent");
            PluginExecution pluginExecution2 = h.setPluginExecution("default-report", "report");
            List<PluginExecution> pluginExecutions = new ArrayList();
            pluginExecutions.add(pluginExecution1);
            pluginExecutions.add(pluginExecution2);
            jacoco.setExecutions(pluginExecutions);
            model.getBuild().addPlugin(jacoco);
            PomHandle ph = new PomHandle();
            Model model2 = ph.parsePomFileToModel(model.getPomFile());
            Build build = new Build();
            build.addPlugin(jacoco);
            model2.setBuild(build);
            PomHandle.writePom(model.getPomFile(), model2);
            if (isDebug || Handle.mvnResultInstall(model.getPomFile())) {
                File jacocoReport = new File(this.project.getBasedir() + File.separator + "target" + File.separator + "site" + File.separator + "jacoco" + File.separator + "index.html");
                if (jacocoReport.exists()) {
                    try {
                        Document doc = Jsoup.parse(jacocoReport, "UTF-8");
                        Element tfoot = (Element)doc.getElementsByTag("tfoot").get(0);
                        Element td = (Element)tfoot.getElementsByTag("td").get(2);
                        String codeCoverage = td.text();
                        log.info("------------------------");
                        log.info("| 测试用例覆盖率为:{}  |", codeCoverage);
                        log.info("------------------------");
                        //Write.writeResult(this.project, this.outDir, codeCoverage);
                    } catch (IOException var18) {
                    }
                }
            }
        }

    }
}