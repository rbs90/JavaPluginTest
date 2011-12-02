package de.rbs90.test.simplePlugin.pluginLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import de.rbs90.test.simplePlugin.PluginBase;

public class PluginLoader {

	public static List<PluginBase> ListAvailablePlugins(File pluginFolder) throws IOException{
		File[] plugJars = pluginFolder.listFiles(new JarFileFilter());
		ClassLoader cl = new URLClassLoader(PluginLoader.fileArrayToURLArray(plugJars));
		List<Class<PluginBase>> pluginClasses = PluginLoader.extractClassesFromJARs(plugJars, cl);
		return createPluginBaseObjects(pluginClasses);
	}
	
	private static URL[] fileArrayToURLArray(File[] files) throws MalformedURLException {
		  URL[] urls = new URL[files.length];
		  for (int i = 0; i < files.length; i++) {
		    urls[i] = files[i].toURI().toURL();
		  }
		  return urls;
	}
	
	private static List<Class<PluginBase>> extractClassesFromJARs(File[] jars, ClassLoader cl) throws IOException {
		 
		  List<Class<PluginBase>> classes = new ArrayList<Class<PluginBase>>();
		  for (File jar : jars) {
		    classes.addAll(PluginLoader.extractClassesFromJAR(jar, cl));
		  }
		  return classes;
	}
	
	@SuppressWarnings("unchecked")
	private static List<Class<PluginBase>> extractClassesFromJAR(File jar, ClassLoader cl) throws IOException {
	 
	  List<Class<PluginBase>> classes = new ArrayList<Class<PluginBase>>();
	  JarInputStream jaris = new JarInputStream(new FileInputStream(jar));
	  JarEntry ent = null;
	  while ((ent = jaris.getNextJarEntry()) != null) {
	    if (ent.getName().toLowerCase().endsWith(".class")) {
	      try {
	        Class<?> cls = cl.loadClass(ent.getName().substring(0, ent.getName().length() - 6).replace('/', '.'));
	        if (PluginLoader.isPluginBaseClass(cls)) {
	          classes.add((Class<PluginBase>)cls);
	        }
	      }
	      catch (ClassNotFoundException e) {
	        System.err.println("Can't load Class " + ent.getName());
	        e.printStackTrace();
	      }
	    }
	  }
	  jaris.close();
	  return classes;
	}
	
	private static boolean isPluginBaseClass(Class<?> cls) {
		 
		  for (Class<?> i : cls.getInterfaces()) {
		    if (i.equals(PluginBase.class)) {
		      return true;
		    }
		  }
		  return false;
	}
	
	private static List<PluginBase> createPluginBaseObjects(List<Class<PluginBase>> pluginBases) {
		 
		  List<PluginBase> plugs = new ArrayList<PluginBase>(pluginBases.size());
		  for (Class<PluginBase> plug : pluginBases) {
		    try {
		      plugs.add(plug.newInstance());
		    }
		    catch (InstantiationException e) {
		      System.err.println("Can't instantiate plugin: " + plug.getName());
		      e.printStackTrace();
		    }
		    catch (IllegalAccessException e) {
		      System.err.println("IllegalAccess for plugin: " + plug.getName());
		      e.printStackTrace();
		    }
		  }
		  return plugs;
	}
	
	
}
