package com.zihua.webdriver.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomData {
	
	private int size = 0;
	private List<Integer> list;
	
	public static RandomData create(int size) {
		return new RandomData(size);
	}

	public RandomData(int size) {
		this.size = size;
		this.list = new ArrayList<Integer>(size);
	}
	
	public RandomData random() {
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			int var1 = r.nextInt(size - i) + i;
			int var2 = list.get(i);
			list.set(i, list.get(var1));
			list.set(var1, var2);
		}
		return this;
	}
	
	public int[] toArray() {
		return this.list.stream().mapToInt(Integer::intValue).toArray();
	}
	
	public List<Integer> toList() {
		return this.list;
	}
}
