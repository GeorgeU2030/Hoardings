package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;

public class HordingData {

	private String reporter="";
	private ArrayList<Hoarding>hoardings= new ArrayList<>();

	public ArrayList<Hoarding> getHoardings() {
		return hoardings;
	}

	public void setHoardings(ArrayList<Hoarding> hoardings) {
		this.hoardings = hoardings;
	}
	public void importHoardings(String path) { 
		FileReader readFile;
		try {
			readFile = new FileReader(path);
			BufferedReader textFile = new BufferedReader(readFile);
	        String line;
	        String fileComplete="";
	        try {
				while((line=textFile.readLine())!=null) {
					fileComplete+=line;
				}
				String[]data1 = fileComplete.split("\\|");
				for (int i=4;i<data1.length;i=i+4) {
					boolean status=false;
					int width = Integer.parseInt(data1[i]);
					int height = Integer.parseInt(data1[i+1]);
					if(data1[i+2].equals("true")) {
						status=true;
					}
					String brand = data1[i+3];
					Hoarding hoarding = new Hoarding(width,height,status,brand);
					hoardings.add(hoarding);
				}
			
				readFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
	}
	public void saveJSON() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		File file = new File("data.json");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(json.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loadJSON() {
		try {
			FileInputStream is = new FileInputStream(new File("data.json"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line ;
			String json="";
			while((line=reader.readLine())!=null) {
				json+=line;
			}
			//String a objecto
			Gson gson = new Gson();
			HordingData data = gson.fromJson(json, HordingData.class);
			hoardings = data.hoardings;
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void showHoardings() {
		for (int i = 0; i < hoardings.size(); i++) {
			System.out.print(hoardings.get(i).getWidth()+"  ");
			System.out.print(hoardings.get(i).getHeight()+ "  ");
			if(hoardings.get(i).isUse()==false) {
				System.out.print(hoardings.get(i).isUse()+ "  ");
			}else {
			System.out.print(hoardings.get(i).isUse()+ "   ");
			}
			System.out.print(hoardings.get(i).getBrand()+"\n");
		
		}
		System.out.println("\nTOTAL HOARDINGS: "+ hoardings.size());
	}
	public void add(int height,int width,boolean use,String brands) {
		hoardings.add(new Hoarding(height,width,use,brands));
		
	}
	public void report() {
		System.out.println("\n===================");
		System.out.println("DANGEROUS BILLBOARD REPORT");
		System.out.println("===================");
		System.out.println("The dangerous billboard are:");
		reporter+="\n===================\nDANGEROUS BILLBOARD REPORT\n===================\nThe dangerous billboard are:";
		
		int a=0;
		for (int i = 0; i < hoardings.size(); i++) {
			int area = hoardings.get(i).getHeight()*hoardings.get(i).getWidth();
			if(area>200000) {
				a++;
				System.out.println(a+". Billboard "+ hoardings.get(i).getBrand()+" with area "+area);
				reporter+=a+". Billboard "+ hoardings.get(i).getBrand()+" with area "+area+"\n";
			}
		}
		try {
			generateReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void generateReport() throws IOException {
		File file = new File("report.txt");
		if (!file.exists()) {
            file.createNewFile();
        }
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(reporter.getBytes());
		fos.close();
  
}

	}

