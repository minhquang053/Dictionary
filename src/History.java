import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class History {
    private File file;
    private List<String> historyList = new ArrayList<String>();

    public History() {
        try {
            file = new File("rsc/database/History.txt");
            importHistory();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void importHistory() throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            historyList.add(reader.nextLine());
            // historyList.add(reader.nextLine());
        }
        reader.close();
    }

    public List<String> getHistory() {
        return historyList;
    }
    
    public String[] getHistory2() {
        String[] ret = new String[historyList.size()];
        int index = historyList.size() - 1;
        for (String h: historyList) {
            ret[index--] = h;
        }
        return ret;
    }
    
    public void deleteHistory(String word) {
        try {
           historyList.remove(word); 
        } catch (Exception e) {}
    }
    
    public void updateHistory(String word) {
        historyList.add(word);
    }

    public void saveHistory() {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < historyList.size(); ++i) {
                writer.write(historyList.get(i) + "\n");
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String[] historySearch(String s) {
        String data = new String("");
        java.util.Iterator itr = historyList.iterator();
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
