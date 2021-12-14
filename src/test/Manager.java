package test;
 
public class Manager {
    private String name;
    private int Age;
    private boolean Capability;
    
    //конструктор
    public Manager(String name, int Age, boolean Capability){
        this.name = name;
        this.Age = Age;
        this.Capability = Capability;
    }
 
    public Manager() {
    }
 
    public Manager(String[] s) {
        name = s[0];
        Age = Integer.parseInt(s[1]);
        Capability = Boolean.parseBoolean(s[2]);
    }
    // методы get / set
    public String getName(){
        return name;
    }
    
    public int getAge(){
        return Age;
    }
    
    public boolean getCapability(){
        return Capability;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setAge(int Age){
        this.Age = Age;
    }
    
    public void setCapability(boolean Capability){
        this.Capability = Capability;
    }
    
    //вывод информации
    @Override
    public String toString(){
        String s = "Imya: " + name + ". Vozrast " + Age + " let. spravlyaetsya?: " + getCapabilityAsString();
        return s;
    }
 
    private String getCapabilityAsString() {
        if(Capability){
            return "da";
        }
        else{
            return "net";
        }
    }
    
}