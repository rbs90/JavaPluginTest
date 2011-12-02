package de.rbs90.test.simplePlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import de.rbs90.test.simplePlugin.pluginLoader.JarFileFilter;
import de.rbs90.test.simplePlugin.pluginLoader.PluginLoader;

public class PluginTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		List<PluginBase> plugins = PluginLoader.ListAvailablePlugins(new File("plugins/"));
		System.out.println("availale plugins:");
		for (PluginBase plugin: plugins){
			System.out.println("" + plugin.getLocationID());
		}
	}

}
