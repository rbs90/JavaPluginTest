package de.rbs90.test.simplePlugin.plugins;

import de.rbs90.test.simplePlugin.PluginBase;

public class NauenPlugin implements PluginBase{

	private String input = null;
	
	@Override
	public void setText(String input) {
		this.input = input;
		
	}

	@Override
	public void executeFilter() {
		System.out.println("Executing...");
		
	}

	@Override
	public String getEOrt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getENr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocationID() {
		return "Nauen";
	}

}
