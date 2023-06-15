//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.unit;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationOutputHandler;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.apache.maven.shared.invoker.PrintStreamLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Handle {
    public static final Logger log = LoggerFactory.getLogger(Handle.class);

    public Handle() {
    }

    public boolean mvnClean(MavenProject project) {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(project.getModel().getPomFile());
        request.setGoals(Collections.singletonList("clean"));
        Invoker invoker = new DefaultInvoker();

        try {
            request.setOutputHandler((InvocationOutputHandler) null);
            InvocationResult invocationResult = invoker.execute(request);
            if (invocationResult.getExitCode() != 0) {
                log.error("Failed to acquire clean!");
                return false;
            } else {
                log.info("Successfully acquire clean!");
                return true;
            }
        } catch (MavenInvocationException var5) {
            var5.printStackTrace();
            log.error("Failed to acquire clean!");
            return false;
        }
    }

    public static boolean mvnCompile(MavenProject project) {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(project.getModel().getPomFile());
        request.setGoals(Collections.singletonList("clean compile"));
        Invoker invoker = new DefaultInvoker();

        try {
            invoker.setLogger(new PrintStreamLogger(System.err, 1) {
            });
            request.setOutputHandler(System.out::println); // Updated line
            InvocationResult invocationResult = invoker.execute(request);
            if (invocationResult.getExitCode() != 0) {
                log.error("Failed to acquire compile!");
                return false;
            } else {
                log.info("Successfully acquire compile!");
                return true;
            }
        } catch (MavenInvocationException var4) {
            var4.printStackTrace();
            log.error("Failed to acquire compile!");
            return false;
        }
    }

    public static boolean mvnTest(File pom) {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(pom);
        request.setGoals(Collections.singletonList("clean test"));
        Invoker invoker = new DefaultInvoker();

        try {
            invoker.setLogger(new PrintStreamLogger(System.err, 1) {
            });
            request.setOutputHandler((InvocationOutputHandler) null);
            InvocationResult invocationResult = invoker.execute(request);
            return invocationResult.getExitCode() == 0;
        } catch (MavenInvocationException var4) {
            log.error("Failed to acquire test!");
            var4.printStackTrace();
            return false;
        }
    }

    public static boolean mvnResultInstall(File pom) {
    InvocationRequest request = new DefaultInvocationRequest();
    request.setPomFile(pom);
    request.setGoals(Collections.singletonList("clean install"));
    Invoker invoker = new DefaultInvoker();

    try {
        invoker.setLogger(new PrintStreamLogger(System.err, 1) {
        });
        request.setOutputHandler((InvocationOutputHandler) null);
        InvocationResult invocationResult = invoker.execute(request);
        return invocationResult.getExitCode() == 0;
    } catch (MavenInvocationException var4) {
        var4.printStackTrace();
        return false;
    }
}

    public boolean mvnLicense(MavenProject project) {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(project.getModel().getPomFile());
        request.setGoals(Collections.singletonList("license:add-third-party"));
        Invoker invoker = new DefaultInvoker();

        try {
            invoker.setLogger(new PrintStreamLogger(System.err, 1) {
            });
            request.setOutputHandler((InvocationOutputHandler) null);
            InvocationResult invocationResult = invoker.execute(request);
            if (invocationResult.getExitCode() != 0) {
                log.error("collect licenses failed!");
                return false;
            } else {
                log.info("Successfully collect licenses Successfully!");
                return true;
            }
        } catch (MavenInvocationException var5) {
            var5.printStackTrace();
            log.error("collect licenses failed!");
            return false;
        }
    }

    public PluginExecution setPluginExecution(String id, String goal) {
        PluginExecution pe = new PluginExecution();
        List<String> goals = new ArrayList();
        goals.add(goal);
        pe.setGoals(goals);
        pe.setId(id);
        return pe;
    }
}
