package ru.job4j.template;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;

public class GeneratorImplTest {

//    @Test
    public void whenProduceThenSuccess() {
        String template = "I am a ${name1}, and this is ${name2}.";
        HashMap<String, String> valuesMap = new HashMap<>();
        valuesMap.put("name1", "Sasha");
        valuesMap.put("name2", "Victor");
        GeneratorImpl generator = new GeneratorImpl();
        String produce = generator.produce(template, valuesMap);
        Assert.assertThat(produce, is("I am a Sasha, and this is Victor."));
    }

//    @Test(expected = IllegalArgumentException.class)
    public void whenProduceWithLotArgThenThrow() {
        String template = "I am a ${name1}, and this is ${name2}.";
        HashMap<String, String> valuesMap = new HashMap<>();
        valuesMap.put("name1", "Sasha");
        valuesMap.put("name2", "Victor");
        valuesMap.put("name3", "Den");
        GeneratorImpl generator = new GeneratorImpl();
        generator.produce(template, valuesMap);
    }

//    @Test(expected = IllegalArgumentException.class)
    public void whenProduceWithFewerArgThenThrow() {
        String template = "I am a ${name1}, and this is ${name2}.";
        HashMap<String, String> valuesMap = new HashMap<>();
        valuesMap.put("name1", "Sasha");
        GeneratorImpl generator = new GeneratorImpl();
        generator.produce(template, valuesMap);
    }
}
