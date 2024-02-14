package me.greencat.shimmer.module;

import me.greencat.shimmer.ShimmerClient;
import me.greencat.shimmer.config.ModuleStatusConfig;
import me.greencat.shimmer.mixins.ScreenManagerAccessor;
import me.greencat.src.ScreenManager;
import me.greencat.src.config.EnumConfigType;
import me.greencat.src.utils.ClassCategory;
import net.minecraftforge.common.MinecraftForge;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class CatGirlManager {
    public static HashMap<String,CatGirl> catGirlHome = new HashMap<>();
    public static HashMap<String,Boolean> catGirlStatus = new HashMap<>();
    public static void adopt(Class<? extends CatGirl> catGirl){
        try {
            CatGirl instance = catGirl.newInstance();
            AtomicReference<String> classCategory = new AtomicReference<>("Default");
            if (catGirl.isAnnotationPresent(ClassCategory.class)) {
                Optional<Annotation> optional = Arrays.stream(catGirl.getAnnotations()).filter((it) -> it instanceof ClassCategory).findFirst();
                optional.ifPresent((it) -> {
                    classCategory.set(((ClassCategory)it).value());
                });
            }
            String classCategoryString = classCategory.get();
            if (!((ScreenManagerAccessor)ShimmerClient.config).getInstances().containsKey(classCategoryString)) {
                ShimmerClient.config.createInstance(classCategoryString);
            }
            ((ScreenManagerAccessor)ShimmerClient.config).getInstances().get(classCategoryString).addConfig(EnumConfigType.BOOLEAN,catGirl.getSimpleName(),"isEnable_" + catGirl.getSimpleName(),false);
            ShimmerClient.config.autoAdd(catGirl);
            MinecraftForge.EVENT_BUS.register(instance);
            catGirlHome.put(catGirl.getSimpleName(),instance);
        } catch(Exception | Error e){
            throw new RuntimeException("这只猫猫不想跟你回家",e);
        }
    }
    public static boolean getPlayStatus(String name){
        return catGirlStatus.get(name);
    }
    public static void playTogether(String name,boolean status){
        try {
            catGirlStatus.put(name,status);
            if(status){
                ModuleStatusConfig.add(name);
            } else {
                ModuleStatusConfig.remove(name);
            }
        } catch(Exception e){
            throw new RuntimeException("这只猫猫不想跟你一起玩",e);
        }
    }
}
