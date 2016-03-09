package com.vincent.videosys.controller.home;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.sun.istack.internal.logging.Logger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class HdfsController {
	
	private static final String TAG = "HdfsController: ";
	
	private static final Logger LOGGER = Logger.getLogger(HdfsController.class);
	
	/**
	 *  according to source path, copy local file to HDFS
	 * @param source
	 * @param destination
	 * @param conf
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> copyLocalFileToHdfs (String source, String destination) throws Exception{
		Map<String, Object> rstMap = new HashMap<String, Object>();
		
		Configuration conf = new Configuration();	
		FileSystem fs = FileSystem.get(conf);
		Path srcPath = new Path(source);
		File srcFile = new File(source);
		Path destPath = new Path(destination);
		if(srcFile.exists()){
			if(fs.exists(new Path(destination))){
				rstMap.put("status", 0);
				rstMap.put("reason", "The target file is already existed");
			}
			else{
				fs.copyFromLocalFile(srcPath, destPath);
				fs.close();
				rstMap.put("status", 1);
				System.out.println("copy file from local to hdfs finished");
			}
		}
		else{
			rstMap.put("status", 0);
			rstMap.put("reason", "The source file is not existed");
		}	
		
		return rstMap;
	}
	
	public static Map<String, Object> copyLocalFileToHdfsByStream(InputStream in, String dst) 
			throws Exception{
		Map<String, Object> rstMap = new HashMap<String, Object>();
		
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		try{
			if(fs.exists(new Path(dst))){
				rstMap.put("status", 0);
				rstMap.put("reason", "The target file is already existed");
			}
			else{
				OutputStream out = fs.create(new Path(dst));
				IOUtils.copyBytes(in, out, 4096, true);//4096 is 4 KB
				rstMap.put("status", 1);
				System.out.println("copy file from local to hdfs finished");
			}
		}catch(Exception e){
			rstMap.put("status", 0);
			rstMap.put("reason", "copy file failed: "+e);
			System.out.println("copy file failed: "+e);
		}
		return rstMap;
	}
	
	/**
	 * delete HDFS file
	 * @param srcName
	 * @param conf
	 * @throws Exception
	 */
	public static void deleteHdfsFile(String srcName) throws Exception{
		Configuration conf = new Configuration();
		
		FileSystem fs = FileSystem.get(URI.create(srcName), conf);
		
		Path srcPath = new Path(srcName);
		
		fs.delete(srcPath, true);
	}
	
	/**
	 * create the sub directory
	 * @param dir_name
	 * @throws Exception
	 */
	public static void mkdir(String dir_name) throws Exception{
		Configuration conf = new Configuration();
		
		String parent = "hdfs://192.168.103.128:9000/usr/hadoop/data/";
		String child = parent + dir_name;
		
		FileSystem fs = FileSystem.get(URI.create(parent), conf);
		
		fs.mkdirs(new Path(child));
	}
	
	/**
	 * delete the HDFS file
	 * @param delete_file_path
	 * @throws Exception
	 */
	public static void deleteFile(String delete_file_path) throws Exception{
		Configuration conf = new Configuration();
		
		FileSystem fs = FileSystem.get(URI.create(delete_file_path), conf);
		
		Path srcPath = new Path(delete_file_path);
		
		fs.delete(srcPath, true);
	}

}
