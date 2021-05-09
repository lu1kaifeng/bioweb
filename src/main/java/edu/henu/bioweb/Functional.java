package edu.henu.bioweb;

import java.util.ArrayList;
import java.util.List;

public class Functional {
    public interface Condition<T>{
        boolean ifKeep(T elem);
    }
    public interface Mapper<S,D>{
         D map(S s);
    }
     public static <T> List<T> filter(List<T> src, Condition<T> condition){
        ArrayList<T> dest = new ArrayList<>();
        for(T t : src){
            if(condition.ifKeep(t)){
                dest.add(t);
            }
        }
        return dest;
    }
    public static <S,D>  List<D> map(List<S> src,Mapper<S,D> mapper){
        List<D> dest = new ArrayList<>();
        for(S s : src){
            dest.add(mapper.map(s));
        }
        return dest;
    }
}
