package com.ataybur.constants;

public class Constants {
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String TEMP_DIR_PROPERTY = "java.io.tmpdir";
    public static final String TEMP_FILE_PREFIX = "splitted.part";
    public static final String TEMP_FILE_TEMPLATE = "%s%ssplitted.part%s";
    public static final String EXPORT_FILE_NAME = "exported.html";
    public static final String ERROR_LOG_FILE_NAME = "error.log";
    public static final String LINE_SEPERATOR = "line.separator";
    public static final String SYS_LINE_SEPERATOR = System.getProperty(LINE_SEPERATOR);
    
    public static final String FILE_NAME = "C://Users//Burak//Documents//Dosyaicerik - Kopya.txt";
    public static final String FILE_NAME_2 = "C://Users//Burak//Documents//Dosyaicerik - Kopya-4.txt";
    
    public static final String DOT = ".";
    public static final String EMPTY = "";
    public static final int PART_SIZE = "D00012641400000982000000002550.6511-05-20160042016A-561418170".length()+System.lineSeparator().getBytes().length;
    public static final int MAX_FILE_COUNT = 100;
}
