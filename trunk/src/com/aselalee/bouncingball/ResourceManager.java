package com.aselalee.bouncingball;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.util.Log;

public class ResourceManager {
	Context context;
	String inputDataFileName;

	public ResourceManager(Context mContext) {
		String basePath = mContext.getFilesDir().getAbsolutePath();
		inputDataFileName = basePath + "/inputdata.xml";
		context = mContext;
		
		try {
			copyIfNotExist(R.raw.inputdata, inputDataFileName);
		} catch (IOException e) {
			Log.e("ResMgr", "Resource Manager cannot extract file " + e);
			e.printStackTrace();
			inputDataFileName = null;
		}
	}

	public String getResourceFileName(int id) {
		String fileName = null;
		switch (id) {
			case R.raw.inputdata:
				fileName = inputDataFileName;
		}
		return fileName;
	}

	private void copyIfNotExist(int ressourceId,String target) throws IOException {
		File lFileToCopy = new File(target);
		if (!lFileToCopy.exists()) {
			copyFromPackage(ressourceId,lFileToCopy.getName()); 
		}
	}

	private void copyFromPackage(int ressourceId,String target) throws IOException{
		FileOutputStream lOutputStream = context.openFileOutput (target, 0); 
		InputStream lInputStream = context.getResources().openRawResource(ressourceId);
		int readByte;
		byte[] buff = new byte[8048];
		while (( readByte = lInputStream.read(buff))!=-1) {
			lOutputStream.write(buff,0, readByte);
		}
		lOutputStream.flush();
		lOutputStream.close();
		lInputStream.close();
	}
}
