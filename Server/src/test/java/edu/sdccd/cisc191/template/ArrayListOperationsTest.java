package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;

public class ArrayListOperationsTest {
    private ArrayList<ArrayList<Integer>> arrayList;

    @BeforeEach
    public void setUp() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrayList.add(new ArrayList<>(Collections.nCopies(3, 0)));
        }
    }

    @Test
    public void testSetElement() {
        arrayList.get(0).set(0, 10);
        assertEquals(10, arrayList.get(0).get(0).intValue());
    }

    @Test
    public void testGetElement() {
        arrayList.get(1).set(1, 20);
        assertEquals(20, arrayList.get(1).get(1).intValue());
    }

    @Test
    public void testExpandArray() {
        arrayList.add(new ArrayList<>(Collections.nCopies(arrayList.size() + 1, 0)));
        assertEquals(4, arrayList.size());
    }

    @Test
    public void testShrinkArray() {
        arrayList.remove(arrayList.size() - 1);
        assertEquals(2, arrayList.size());
    }
}