package br.jao.core;

public class Properties {
    
    public static boolean QUIT_BROWSER = false;

    public static Browsers browser = Browsers.CHROME;

    public static String NOME_CONTA_ALT = "Conta Alterada " + System.nanoTime();

    public enum Browsers {
        CHROME,
        FIREFOX
    }

}
