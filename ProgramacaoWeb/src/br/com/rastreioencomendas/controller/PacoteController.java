package br.com.rastreioencomendas.controller;

import java.util.Random;

public class PacoteController {
	
	public String gerarCodigoRastreio() {
		String codigo = "";
		Random random = new Random();
		
		for(int i = 0; i < 13 ; i ++) {
			
			if(i == 0) {
				codigo+= String.valueOf((char)(65 + random.nextInt(90 - 65)));
			}else if(i == 1) {
				codigo+= String.valueOf((char)(65 + random.nextInt(90 - 65)));
			}else if(i == 2) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 3) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 4) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 5) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 6) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 7) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 8) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 9) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 10) {
				codigo+= String.valueOf(random.nextInt((9 - 1) +1) + 1);
			}else if(i == 11) {
				codigo+= String.valueOf((char)(65 + random.nextInt(90 - 65)));
			}else if(i == 12) {
				codigo+= String.valueOf((char)(65 + random.nextInt(90 - 65)));
			}
		}
		return codigo;
	}
		
}
