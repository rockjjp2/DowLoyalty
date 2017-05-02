package com.dowloyalty.utils;

public class CompareNums {
	/**
	 * 比较获取的页码和最大页码
	 * @param num	当前页码
	 * @param maxPageNum	最大页码
	 * @return	实际页码
	 */
	public static int compareNums(int num, int maxPageNum)
	{
		if(num < 1)
		{
			num = 1;
		}
		else
		{
			if(num > maxPageNum)
			{
				if(maxPageNum == 0)
				{
					num = 1;
				}
				else
				{
					num = maxPageNum;
				}
			}
		}
		return num;
	}
}
