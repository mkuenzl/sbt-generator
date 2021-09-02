package sbt.automization.util;

import sbt.automization.data.DataTable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class Separator
{
	private Separator(){}

	/**
	 * Method can be used to separate lists into Collections of lists
	 *
	 * @param inputList a list
	 * @param size      the maximal size per list
	 * @param <T>       the object which is put into a list
	 * @return a collection of lists with maximal sizes
	 */
	public static <T> Collection<List<T>> separateBasedOnSize(List<T> inputList, int size)
	{
		final AtomicInteger counter = new AtomicInteger(0);
		return inputList.stream().collect(Collectors.groupingBy(l -> counter.getAndIncrement() / size)).values();
	}
}
