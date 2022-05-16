import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Favorite {
    private File file;
    private List<String> favoriteList = new ArrayList<String>();

    public Favorite() {
        try {
            file = new File("rsc/database/Favorite.txt");
            importFavorite();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void importFavorite() throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            favoriteList.add(reader.nextLine());
            // historyList.add(reader.nextLine());
        }
        reader.close();
    }

    public List<String> getFavorite() {
        return favoriteList;
    }
    
    public String[] getFavorite2() {
        String[] ret = new String[favoriteList.size()];
        int index = favoriteList.size() - 1;
        for (String h: favoriteList) {
            ret[index--] = h;
        }
        return ret;
    }

    public void deleteFavorite(String word) {
        try {
           favoriteList.remove(word); 
        } catch (Exception e) {}
    }
    
    public void updateFavorite(String word) {
        favoriteList.add(word);
    }

    public void saveFavorite() {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < favoriteList.size(); ++i) {
                writer.write(favoriteList.get(i) + "\n");
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isFavorited(String word) {
        if (favoriteList.contains(word)) {
            return true;
        } else {
            return false;
        }
    }
    
    public String[] favoriteSearch(String s) {
        String data = new String("");
        java.util.Iterator itr = favoriteList.iterator();
        String temp;
        try {
            while (itr.hasNext()) {
                temp = (String) itr.next();
                if (temp.length() < s.length()) {
                    continue;
                }
                if (temp.substring(0, s.length()).equals(s)) {
                    data += temp + ";";
                    while (itr.hasNext()) {
                        temp = (String) itr.next();
                        if (!temp.substring(0, s.length()).equals(s)) {
                            break;
                        }
                        data += temp + ";";
                    }
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.split(";");
    }
}