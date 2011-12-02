package de.rbs90.test.simplePlugin.pluginLoader;

import java.io.File;
import java.io.FileFilter;
 
public class JarFileFilter implements FileFilter {	
	
  public boolean accept(File f) {
    return f.getName().toLowerCase().endsWith(".jar");
  }
}