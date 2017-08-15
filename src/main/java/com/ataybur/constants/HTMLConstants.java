package com.ataybur.constants;

public class HTMLConstants {
    public static final String HTML_TEMPLATE_1 = "<!DOCTYPE html> " + //
	    " <html> " + //
	    " <head> " + //
	    " <style> " + //
	    " table { " + //
	    "     font-family: arial, sans-serif; " + //
	    "     border-collapse: collapse; " + //
	    "     width: 100%; " + //
	    " } " + //
	    " td, th { " + //
	    "     border: 1px solid #dddddd; " + //
	    "     text-align: left; " + //
	    "     padding: 8px; " + //
	    " } " + //
	    " tr:nth-child(even) { " + //
	    "     background-color: #dddddd; " + //
	    " } " + //
	    " </style> " + //
	    " </head> " + //
	    " <body> " + //
	    " <table> " + //
	    "   <tr> ";
    public static final String HTML_TEMPLATE_2 = "      <th>%s</th> ";
    public static final String HTML_TEMPLATE_3 = "     </tr> " + //
	    "     <tr> ";
    public static final String HTML_TEMPLATE_4 = "       <td>%s</td> ";
    public static final String HTML_TEMPLATE_5 = "     </tr> " + //
	    "   </table> " + //
	    "   </body> " + //
	    "   </html> ";
}
