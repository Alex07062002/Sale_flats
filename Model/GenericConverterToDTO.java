package org.example.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@FunctionalInterface
public interface GenericConverterToDTO<Input, Output> extends Function<Input,Output> { // TODO не понадобился

    default List<Output> convert(final List<Input> input){
        List<Output> output = new ArrayList<>();
        if (input != null){
            output = input.stream().map(this).collect(Collectors.toList());
        }
        return output;
    }
}
