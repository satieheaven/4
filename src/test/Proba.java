package test;
 //в рот ебал я этот код 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
 
public class Proba {
    public static ArrayList<Manager> ars = new ArrayList<>(); //переменная для хранения менеджеров
      public static void main(String[] args) {
        readManager();//считывает инфу о объекте из файла, файл уже должен существовать
        //запускаем рабочийт цикл програмы
        work();
    }
         private static void readManager() {
        BufferedReader in = null;
        File f = new File("manager.txt"); //создаем обект файла 
        if(f.exists()){
            try {            
                in = new BufferedReader(new FileReader(f));
                try {
                    while(in.readLine() != null){
                        objReader(in.readLine());
                    }//достаем студентов из файла
                } catch (IOException ex) {
                }
            } catch (FileNotFoundException ex) {
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                }
            }
        }
    }
         private static void objReader(String readLine) {
        String[] s = recut(readLine, "||");//разрезаем строку по маркеру
        Manager st = new Manager(s);
        ars.add(st);
    } 
          private static String[] recut(String s, String marker) {
        int[] i = allIndexOf(s, marker);//находим общее количество маркеров
        String[] ss = null;
        if(i != null){
            ss = new String[i.length + 1];
            ss[0] = s.substring(0, i[0]);
            for(int a = 1; a < i.length; a++){
                ss[a] = s.substring(i[a - 1] + marker.length(), i[a]);
            }
            ss[ss.length - 1] = s.substring(i[i.length - 1] + marker.length());
        }
        else{
            ss = new String[]{s};
        }
        return ss;
    }
            private static int[] allIndexOf(String s, String marker){
        //ищем общее количество проходов заданной строки (или символа String)
        int[] ii = null;
        ArrayList<String> a = new ArrayList<>();
        for(int i = 0; i < s.length() - (marker.length() - 1); i++){
            if(marker.equals(s.substring(i, i + marker.length()))){
                a.add("" + i);
            }
        }
        if(a.size() > 0){
            ii = new int[a.size()];
            for(int i = 0; i < a.size(); i++){
                ii[i] = Integer.parseInt(a.get(i));
            }
        }
        return ii;
    }
             private static void work() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        OUTER:
        while (true) {
            try {
                System.out.println("Viberite deistvie programmi:\n"
                        + "add - dobavit' managera\n"
                        + "out - vivod informacii pro managera\n"
                        + "exit - vihod");
                String s = br.readLine();
                switch (s) {//свитчи для удобства, не понтов :)
                    case "add" -> addMngr();//добавить менеджера
                    case "out" -> outManager();//вывод информации про менеджера;
                    case "exit" -> {
                        exitNaH();//сохраняем менеджеров и выходим
                        break OUTER; //прерываем цикл
                    }
                    default -> System.out.println("~lab: command not found");
                }
            }catch (IOException ex) {
            }
        }
    }
             private static void addMngr() throws IOException {
        boolean yes = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Manager st = new Manager();
        System.out.println("FIO");
        st.setName(br.readLine());
        System.out.println("Vozrast");
        int i = Integer.parseInt(br.readLine());
        st.setAge(i);
        System.out.println("Spravlyaetsya y/n");
        String s = br.readLine();
        switch (s) {
            case "y", "Y" -> st.setCapability(true);
            case "n", "N" -> st.setCapability(false);
            default -> yes = false;
        }
        if(yes){
            ars.add(st);
        System.out.println("Manager vveden v spisok");
        }
        else{
            System.out.println("ERROR");
        }
        
    }
                 private static void outManager() {
        for(int i = 0; i < ars.size(); i++){
            System.out.println(ars.get(i).toString());
        }
        System.out.println("Srednii vozrast " + sredniyVozrast());
        System.out.println("Kol-vo spravlyauvhihsya " + capable());
    }
                     
                         private static int sredniyVozrast() {
        int i = 0;
        for(int a = 0; a < ars.size(); a++){
            i += ars.get(a).getAge();
        }
        i /= ars.size();
        return i;
    }
                             private static int capable() {
        int a = 0;
        for(int i = 0; i < ars.size(); i++){
            if(ars.get(i).getCapability()){
                a++;
            }
        }
        return a;
    }
                                 private static void exitNaH() {
        PrintWriter pw = null;
        try {
            String s = saveString();//создаем строку для записи
            pw = new PrintWriter("manager.txt", "UTF-8");
            pw.print(s);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
        } finally {
            pw.close();
        }
    }
                                     private static String saveString() {
        String s = "";
        for(int i = 0; i < ars.size() - 1; i++){
            s = s + ars.get(i).getName() + "||" + ars.get(i).getAge() + "||" + ars.get(i).getCapability() + "\n";//создаем строку с маркерами
        }
        s = s + ars.get(ars.size() - 1).getName() + "||" + ars.get(ars.size() - 1).getAge() + "||" + ars.get(ars.size() - 1).getCapability();//не было пустой строки в конце файла
        return s;
    }
}
    //скучно
