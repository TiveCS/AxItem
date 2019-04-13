package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;

import net.md_5.bungee.api.chat.TextComponent;

public class DataConverter {
	
	public static void playSoundByString(Location loc, String text) {
		String[] split = text.split("-");
		loc.getWorld().playSound(loc, Sound.valueOf(split[0].toUpperCase()), Float.parseFloat(split[1]), Float.parseFloat(split[2]));
	}

	public static double randomDouble(double min, double max){
		double greater = min > max ? min : max, lower = min > max ? max : min;
		return lower + (greater - lower) * new Random().nextDouble();
	}

	public static TextComponent addMultipleLineComponent(List<String> list) {
		TextComponent com = new TextComponent("");
		for (String s : list) {
			if (com.getText().equals("")) {
				com.addExtra(s);
			}else {
				com.addExtra("\n");
				com.addExtra(s);
			}
		}
		return com;
	}

	public static String returnDecimalFormated(int decimalLength, double num){
		String m = "#0.";
		for (int i = 0; i < decimalLength; i++){
			m = m.concat("0");
		}
		NumberFormat f = new DecimalFormat(m);
		String text = f.format(num);
		while (text.endsWith("0")){
			text = text.substring(0, text.length() - 1);
		}
		if (text.endsWith(".")){
			text = text.substring(0, text.length() - 1);
		}
		return text;
	}

	public static boolean chance(double num){
		return num >= new Random().nextDouble() * 100;
	}

	public static int convertStringToInt(String text) {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByName("JavaScript");
		int result = 0;
		
		try {
			result = Integer.parseInt(se.eval(text).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static double convertStringToDouble(String text) {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByName("JavaScript");
		double result = 0;
		
		try {
			result = Double.parseDouble(se.eval(text).toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
	}
	
    public static List<String> colored(List<String> list){
        for (int i = 0; i < list.size(); i++){
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        return list;
    }
    
    public static List<String> arrayToList(String[] array){
    	List<String> list = new ArrayList<String>();
    	for (String s : array) {
    		list.add(s);
    	}
    	return colored(list);
    }
    
    public static List<String> objectToList(Object object){
        List<String> list = new ArrayList<String>();
        String s = object.toString();
        s = s.substring(1);
        s = s.substring(0, s.length() - 1);
        String[] split = s.split(", ");

        for (String sp : split){
            list.add(sp);
        }
        return colored(list);
    }
    
    public static Object matchConvert(Object str) {
    	try {
    		return Integer.parseInt(str.toString());
    	}catch(Exception e) {}
    	try {
    		return Double.parseDouble(str.toString());
    	}catch(Exception e) {}
    	
    	return str;
    }
    
    public static List<String> combineList(List<String> first, List<String> second){
    	for (String s : second) {
    		first.add(s);
    	}
    	return first;
    }

}
