/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.util;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;

/**
 *
 * @author Administrator
 */
public class DozerUtil {
    
    public static <T, U> List<U> map(final Mapper mapper, final List<T> source, final Class<U> destType) {

        final List<U> dest = new ArrayList<>();

        for (T element : source) {
            //dest.add(mapper.map(element, destType));
            dest.add(mapper.map(element, destType));
        }

        return dest;
    }
}
