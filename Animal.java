package com.singtel.test;

import java.util.HashMap;
import java.util.Map;


public abstract class Animal {
    private boolean isWalk;
    private boolean isFly;
    private boolean isSing;
    private boolean isSwim;

    private static Map<String, Map<String, String>> capabilityExceptions = new HashMap<>();

    public abstract void walk();
    public abstract void fly();
    public abstract void sing();
    public abstract void swim();


    public static String getCapabilityExceptions(String className, String type, String capabilty)
    {
        Map<String, String> excps = capabilityExceptions.get(className  + "/" + capabilty);
        
      //  System.out.println("excps : " + excps);
        
        if (excps != null) {
       // System.out.println("excps not null  : " + type);
            return excps.get(type);
            
        }
        return null;
    }

    public static void addExceptions(String className, String type,String capability, String value)
    {
    	
    	
        Map<String, String> excps = capabilityExceptions.get(className + "/" + capability);
  
        if (excps == null)
        {
            excps =  new HashMap<String, String>();
            capabilityExceptions.put(className + "/" + capability,excps);
        }
        excps.put(type, value);    
    }

    public void setIsWalk(boolean val){this.isWalk = val;}
    public boolean isWalk() {return this.isWalk;}
    public void setIsFly(boolean val){this.isFly = val;}
    public boolean isFly() {return this.isFly;}
    public void setIsSwim(boolean val){this.isSwim = val;}
    public boolean isSwim() {return this.isSwim;}
    public void setIsSing(boolean val){this.isSing = val;}
    public boolean isSing() {return this.isSing;}
}

class Bird extends Animal{
	
    private static Map<String, String> sounds = new HashMap<>();
  
    
    static{
        Animal.addExceptions(Bird.class.getName(), "Caterpillar", "walk", "Crawl");
        Animal.addExceptions(Bird.class.getName(), "butterfly", "fly", "can fly");
        Animal.addExceptions(Bird.class.getName(), "Duck", "swim", "can swim");
        Animal.addExceptions(Bird.class.getName(), "Duck", "fly", "Cannot Fly");
        Animal.addExceptions(Bird.class.getName(), "Chicken", "fly", "Cannot Fly");
        

        sounds.put("Duck", "Quack, quack");
        sounds.put("Chicken", "Cluck, cluck");
        sounds.put("Rooster", "Cock-a-doodle-doo");
        sounds.put("Dog", "Woof, woof");
        sounds.put("Cat", "Meow");
   
    }  

    private String type;
    private String gender;
    private String staysNearByType;


    public Bird(String type, String gender, String staysNearByType)
    {
        this.type = type;
        this.gender = gender;
        this.staysNearByType = staysNearByType;
        super.setIsWalk(Animal.getCapabilityExceptions(Bird.class.getName(), type, "walk") == null);
        super.setIsFly(Animal.getCapabilityExceptions(Bird.class.getName() , type, "fly") == null);
        super.setIsSwim(Animal.getCapabilityExceptions(Bird.class.getName(), type, "swim") != null);
        super.setIsSing(Animal.getCapabilityExceptions(Bird.class.getName(), type, "sing") != null);
    }

    public  void walk() {
        String capabilityException = Animal.getCapabilityExceptions(Bird.class.getName(), type, "walk");
        if (capabilityException != null)
            System.out.println(this.type + " can " + capabilityException);
        else
            System.out.println(this.type + " can walk");
    }

    public  void swim() {
    	
        String capabilityException = Animal.getCapabilityExceptions(Bird.class.getName(), type, "swim");
     
        
        if (capabilityException != null)
            System.out.println(this.type + " can " + capabilityException);
        else
            System.out.println(this.type + " can not swim");
    }

    public  void sing() {
        String capabilityException = Animal.getCapabilityExceptions(Bird.class.getName(), type, "sing");
        if (capabilityException != null)
            System.out.println(this.type + " " + capabilityException);
        else
            System.out.println(this.type + " can not sing");
    }

    public  void fly() {
        String capabilityException = Animal.getCapabilityExceptions(Bird.class.getName(), type, "fly");
        if (capabilityException != null)
            System.out.println(this.type + " " + capabilityException);
        else
            System.out.println(this.type + " can fly");
    }
   

    public void says()
    {
        String says = sounds.get(this.type);
        
        if (type.equalsIgnoreCase("Chicken")){
            if (gender != null)
            {
                if ("male".equalsIgnoreCase(gender) && this.staysNearByType == null)
                {
                    says = sounds.get("Rooster");
                }
            }
        }
        else if (type.equalsIgnoreCase("Parrot") && this.staysNearByType != null)
        {
            says = sounds.get(this.staysNearByType);
        }

        if (says == null)
            System.out.println(this.type + " cannot say anything");
        else 
            System.out.println(this.type + " says : " + says);
    }

	/*
	 * public static void addSound(String type, String sound) { sounds.put(type,
	 * sound); }
	 */
    
}
