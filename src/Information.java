/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usoci
 */
public class Information {
    private String[] infoList;
    
    public Information() {
        infoList = "Search;Insert;Favorite;History;Edit;Google;Audio;Export;Delete;".split(";");
    }
    
    public String[] getInfoList() {
        return this.infoList;
    }
    
    public String getInfo(String inf) {
        java.lang.StringBuilder contentBuilder = new java.lang.StringBuilder();
        try {
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.FileReader("rsc/information/" + inf + ".html"));
            String s;
            while ((s = in.readLine()) != null) {
                contentBuilder.append(s);
            }
            in.close();
        } catch (java.io.IOException e) {
            
        }
        String content = contentBuilder.toString();
        return content;
    }
}
