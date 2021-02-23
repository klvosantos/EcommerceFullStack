package com.marcelosantos.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
												 // Converte uma string que pode ter espaçoes em branco/caracters especiais	para uma string com caracteres basicos 
	public static String decodeParam(String s) { // Descodifica um parametro vindo da URL 
		try {									 // Ex: no console devtools encodeUriComponent(Monitor LED)
			return URLDecoder.decode(s, "UTF-8");							// "Monitor%20LED"				
		}
		catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeList(String s) {
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}

}
