/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhulingfeng.android.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName: CheckingUtils
 * @description: 用户数据验证工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class CheckingUtils {

	/**
	 * @FunctionName: valiObjectIsNull
	 * @description: 验证对象是否为空
	 * @author:  Mr.Lee
	 * @param object 待验证的对象
	 * @param objectName 抛出异常时提示："Object '"+objectName+"' is null
	 * @throws NullPointerException 为空的话抛出NullPointerException异常
	 */
	public static void valiObjectIsNull(Object object, String objectName) throws NullPointerException {
		if(object == null){
			throw new NullPointerException("Object '"+objectName+"' is null!");
		}
	}

	/**
	 * @FunctionName:valiStringLength
	 * @description: 验证字符串的长度是在指定范围内
	 * @author:  Mr.Lee
	 * @param string 待验证的字符串
	 * @param minLength 最小长度（包括）
	 * @param maxLength 最大长度（包括）
	 * @throws IllegalArgumentException string的长度不在minLength到maxLength之间时抛出此异常
	 */
	public static void valiStringLength(String string, int minLength, int maxLength) throws IllegalArgumentException {
		int length = string.trim().length();
		if(!(length >= minLength && length <= maxLength)){
			throw new IllegalArgumentException("String '"+string+"' length illegal!");
		}
	}

	/**
	 * @FunctionName:
	 * @description: 验证字符串的长度的最小值
	 * @author:  Mr.Lee
	 * @param string 待验证的字符串
	 * @param minLength 最小长度（包括）
	 * @throws IllegalArgumentException string的长度小于minLength时抛出此异常
	 */
	public static void valiStringMinLength(String string, int minLength) throws IllegalArgumentException {
		int length = string.trim().length();
		if(!(length >= minLength)){
			throw new IllegalArgumentException("String '"+string+"' length illegal!");
		}
	}
	

	/**
	 * @FunctionName: valiStringMaxLength
	 * @description: 验证字符串的长度的最大值
	 * @author:  Mr.Lee
	 * @param string 待验证的字符串
	 * @param maxLength 最大长度（包括）
	 * @throws IllegalArgumentException string的长度大于maxLength时抛出此异常
	 */
	public static void valiStringMaxLength(String string, int maxLength) throws
																		 IllegalArgumentException {
		int length = string.trim().length();
		if(!(length <= maxLength)){
			throw new IllegalArgumentException("String '"+string+"' length illegal!");
		}
	}

	/**
	 * @FunctionName: valiIntValue
	 * @description: 验证Int型数据是在指定范围内
	 * @author:  Mr.Lee
	 * @param number 待验证的数字
	 * @param minValue 最小值（包括）
	 * @param maxValue 最大值（包括）
	 * @param objectName 抛出异常时提示："Int object '"+objectName+"' is illegal!"
	 * @throws IllegalArgumentException number的值不在minLength到maxLength之间时抛出此异常
	 */
	public static void valiIntValue(int number, int minValue, int maxValue, String objectName) throws IllegalArgumentException {
		if(!((number >= minValue) && (number <= maxValue))){
			throw new IllegalArgumentException("Int object '"+objectName+"' is illegal!");
		}
	}

	/**
	 * @FunctionName:
	 * @description: 验证int型数据的最小值
	 * @author:  Mr.Lee
	 * @param number 待验证的Int数据
	 * @param minValue 最小值（包括）
	 * @param objectName 抛出异常时提示："Int object '"+objectName+"' is illegal!"
	 * @throws IllegalArgumentException number的值小于minLength时抛出此异常
	 */
	public static void valiIntMinValue(int number, int minValue, String objectName) throws IllegalArgumentException {
		if(!(number >= minValue)){
			throw new IllegalArgumentException("Int object '"+objectName+"' is illegal!");
		}
	}


	/**
	 * @FunctionName: valiIntMaxValue
	 * @description: 验证Int数据的最大值
	 * @author:  Mr.Lee
	 * @param number 待验证的Int数据
	 * @param maxValue 最大值（包括）
	 * @param objectName 抛出异常时提示："Int object '"+objectName+"' is illegal!"
	 * @throws IllegalArgumentException number的值大于maxLength时抛出此异常
	 */
	public static void valiIntMaxValue(int number, int maxValue, String objectName) throws IllegalArgumentException {
		if(!(number <= maxValue)){
			throw new IllegalArgumentException("Int object '"+objectName+"' is illegal!");
		}
	}

	/**
	 * @FunctionName: valiLongValue
	 * @description: 验证Long型数据是在指定范围内
	 * @author:  Mr.Lee
	 * @param number 待验证的数字
	 * @param minValue 最小值（包括）
	 * @param maxValue 最大值（包括）
	 * @param objectName 抛出异常时提示："Long object '"+objectName+"' is illegal!"
	 * @throws IllegalArgumentException number的值不在minLength到maxLength之间时抛出此异常
	 */
	public static void valiLongValue(long number, long minValue, long maxValue, String objectName) throws IllegalArgumentException {
		if(!((number >= minValue) && (number <= maxValue))){
			throw new IllegalArgumentException("Long object '"+objectName+"' is illegal!");
		}
	}

	/**
	 * @FunctionName: valiLongMinValue
	 * @description: 验证Long数据的最小值
	 * @author:  Mr.Lee
	 * @param number 待验证的Int数据
	 * @param minValue 最小值（包括）
	 * @param objectName 抛出异常时提示："Long object '"+objectName+"' is illegal!"
	 * @throws IllegalArgumentException number的值小于minLength时抛出此异常
	 */
	public static void valiLongMinValue(long number, long minValue, String objectName) throws IllegalArgumentException {
		if(!(number >= minValue)){
			throw new IllegalArgumentException("Long object '"+objectName+"' is illegal!");
		}
	}

	/**
	 * @FunctionName: valiLongMaxValue
	 * @description: 验证Long数据的最大值
	 * @author:  Mr.Lee
	 * @param number 待验证的Int数据
	 * @param maxValue 最大值（包括）
	 * @param objectName 抛出异常时提示："Int object '"+objectName+"' is illegal!"
	 * @throws IllegalArgumentException number的值大于maxLength时抛出此异常
	 */
	public static void valiLongMaxValue(long number, long maxValue, String objectName) throws IllegalArgumentException {
		if(!(number <= maxValue)){
			throw new IllegalArgumentException("Long object '"+objectName+"' is illegal!");
		}
	}

	/**
	 * @FunctionName: valiFileIsExists
	 * @description: 验证文件是否存在
	 * @author:  Mr.Lee
	 * @param file 待验证的文件
	 * @throws FileNotFoundException 当文件不存在时抛出此异常
	 */
	public static void valiFileIsExists(File file) throws FileNotFoundException {
		if(!file.exists()){
			throw new FileNotFoundException("File '"+file.getName()+"' not found!");
		}
	}

	/**
	 * @FunctionName: valiFileCanRead
	 * @description: 验证文件是否能读取
	 * @author:  Mr.Lee
	 * @param file 被检测的对象
	 * @throws IOException 不能读时抛出此异常
	 */
	public static void valiFileCanRead(File file) throws IOException {
		if(!file.canRead()){
			 throw new IOException("For file '"+file.getName()+"' not read access!");
		}
	}

	/**
	 * @MethodName: valiFileCanWrite
	 * @description: 验证文件是否能写入
	 * @author:  Mr.Lee
	 * @param file 被检测的对象
	 * @throws IOException 不能写时抛出此异常
	 */
	public static void valiFileCanWrite(File file) throws IOException {
		if(!file.canWrite()){
			 throw new IOException("For file '"+file.getName()+"' not write access!");
		}
	}

	/**
	 * @MethodName: valiFileIsDirectory
	 * @description: 验证file是否是目录
	 * @author:  Mr.Lee
	 * @param file 待验证的文件对象
	 * @throws IllegalArgumentException file 不存在或不是目录时抛出此异常
	 */
	public static void valiFileIsDirectory(File file) throws IllegalArgumentException {
		if(!file.isDirectory()){
			throw new IllegalArgumentException("File:'"+file.getName()+"'does not exist or not directory!");
		}
	}

	/**
	 * @MethodName: valiFileIsFile
	 * @description: 验证file是否是文件
	 * @author:  Mr.Lee
	 * @param file 待验证的文件对象
	 * @throws IllegalArgumentException file 不存在或不是文件时抛出此异常
	 */
	public static void valiFileIsFile(File file) throws IllegalArgumentException {
		if(!file.isFile()){
			throw new IllegalArgumentException("File:'"+file.getName()+"'does not exist or not file!");
		}
	}

	/**
	 * @MethodName: valiFile
	 * @description: 对指定的文件对象进行是否null、是否存在以及是否是文件校验
	 * @author:  Mr.Lee
	 * @param file 指定的文件
	 * @throws FileNotFoundException 找不到file代表的文件
	 * @throws IllegalArgumentException file不是文件
	 */
	public static void valiFile(File file) throws NullPointerException,FileNotFoundException,IllegalArgumentException {
		valiObjectIsNull(file, "file");
		valiFileIsExists(file);
		valiFileIsFile(file);
	}

	/**
	 * @MethodName: valiDir
	 * @description: 对指定的文件对象进行是否null、是否存在以及是否是目录校验
	 * @author:  Mr.Lee
	 * @param file 指定的文件
	 * @throws FileNotFoundException 找不到file代表的文件
	 * @throws IllegalArgumentException file不是文件
	 */
	public static void valiDir(File file) throws NullPointerException,FileNotFoundException,IllegalArgumentException {
		valiObjectIsNull(file, "file");
		valiFileIsExists(file);
		valiFileIsDirectory(file);
	}

	/**
	 * @MethodName: valiFileByReadBefore
	 * @description: 用于在执行读取之前对指定的文件对象进行是否null、是否存在、是否是文件以及能否读取校验，
	 * @author:  Mr.Lee
	 * @param file 指定的文件
	 * @throws FileNotFoundException 找不到file代表的文件
	 * @throws IllegalArgumentException file不是文件
	 * @throws IOException file代表的文件不能读取
	 */
	public static void valiFileByReadBefore(File file) throws NullPointerException,IllegalArgumentException,IOException {
		valiFile(file);
		valiFileCanRead(file);
	}

	/**
	 * @MethodName: valiFileByWriteBefore
	 * @description: 用于在执行写入之前对指定的文件对象进行是否null、是否存在、是否是文件以及能否写入校验
	 * @author:  Mr.Lee
	 * @param file 指定的文件
	 * @throws IOException file代表的文件不能写入
	 */
	public static void valiFileByWriteBefore(File file) throws IOException {
		valiFile(file);
		valiFileCanWrite(file);
	}
}
