package org.example.Model;

import java.util.*;

import org.apache.commons.lang3.tuple.*;

public interface Mapper<V1,V2> {
   default List<Pair<V1,V2>> join(V1 left, List<V2> right) {
      // if (isJoin(left, right)) {
           List<Pair<V1, V2>> result = new ArrayList<>();
           for (V2 rightObj : right){
               result.add(Pair.of(left, rightObj));
           }
           return result;
        //}else{
        // throw new RuntimeException(); // TODO myException (невозможно склеить сущности)
       // }
       }


   default boolean isJoin(V1 left, List<V2> right){
       List<String> setLeft = new ArrayList<>();
       List<String> setRight = new ArrayList<>();
       Arrays.asList(left.getClass().getDeclaredFields()).forEach(field -> {
           String name = field.getName();
            setLeft.add(name);
       });
       Arrays.asList(right.get(0).getClass().getDeclaredFields()).forEach(field -> {
           String name = field.getName();
           setRight.add(name);
       });
        setRight.retainAll(setLeft);
        if (setRight.isEmpty()) return false;
        else{
            for (V2 obj: right){
                for (String params : setRight){
                    try {
                        if (!obj.getClass().getDeclaredField(params).get(obj).equals(left.getClass().getDeclaredField(params).get(left))) return false;
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        throw new RuntimeException(e); // TODO myException
                    }
                }
            }
        }
        return true;
   }
}
