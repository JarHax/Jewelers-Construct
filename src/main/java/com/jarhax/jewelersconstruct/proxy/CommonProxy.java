package com.jarhax.jewelersconstruct.proxy;

public class CommonProxy {
    
    public void registerRenders () {
        
    }
    
    public String translate(String str, Object... objects){
        return String.format(str, objects);
    }
    
    
}
