package dev.hkor.vcprogram.mechanisms;

import java.awt.Color;

public class ColorTranslator {

	private Color[] colorPalette = {Color.BLUE, Color.GRAY, new Color(0,128,0), 
			Color.RED, Color.CYAN, Color.MAGENTA,
			Color.ORANGE, Color.PINK, Color.YELLOW, 
			new Color(0,128,128), new Color(0,49,83), new Color(255,204,153),
			new Color(139,71,38), new Color(255,192,0), new Color(65,105,225),
			new Color(147,246,0), new Color(128,0,128), new Color(159,251,136),
			new Color(8,38,103), new Color(255,215,0), new Color(219,176,239),
			new Color(128,0,0), new Color(115,84,47), new Color(25,165,111),
			new Color(138,164,183), new Color(205,87,0), new Color(0,127,255),
			new Color(207,41,41), new Color(221,158,205), new Color(0,102,51)};
	
	private String[] translated = {"niebieski", "szary", "zielony", 
			"czerwony", "turkusowy", "fuksji",
			"pomarañczowy", "ró¿owy", "¿ó³ty",
			"morski","atramentowy","brzoskwiniowy",
			"czekoladowy","marchewkowy","modry",
			"limonkowy","purpurowy","pistacjowy",
			"szafirowy","z³oty","wrzosowy",
			"wiœniowy","umbra","szmaragdowy",
			"siny","rudy","lazurowy",
			"krwisty","liliowy","malachitowy"};	
	
	
	public String translate(Color color){
		String translation = null;
		for(int i = 0; i< colorPalette.length; i++){
			if(color != null){
				if(color.getRGB() == colorPalette[i].getRGB()){
					translation = translated[i];
					break;
				}
			}
			else translation = "- brak (za ma³o kolorów)";
		}
		return translation;
	}
	
	public Color[] getColorPalette(){
		return colorPalette;
	}
	
}
