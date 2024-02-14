package me.greencat.shimmer.module;

public abstract class CatGirl {
    public final String name;
    public CatGirl(String name){
        this.name = name;
    }
    public boolean getStatus(){
        return CatGirlManager.getPlayStatus(this.name);
    }
}
