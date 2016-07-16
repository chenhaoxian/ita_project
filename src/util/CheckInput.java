package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput {
	
	public static boolean check(String line){
		
		String rex = "[ADUL]{1}-[A-Z]{1,2}\\s{0,}.*";
		Pattern p = Pattern.compile(rex);
		Matcher m = p.matcher(line);
		
		if(m.find()){
			return true;
		}else{
			return false;
		}
	}

}
