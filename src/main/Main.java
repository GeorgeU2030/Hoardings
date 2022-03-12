package main;
import java.util.Scanner;

import model.HordingData;
public class Main {
	Scanner sc = new Scanner(System.in);
	private HordingData data=new HordingData() ;
	public static void main(String[] args) {
     Main obj = new Main();
     int option=0;
     do{option=obj.menu();
     obj.answerOption(option);
     }
     while(option!=0);
	}

	public int menu() {
		System.out.println("\n\nHoardings");
		System.out.println("1.Import CSV Data");
		System.out.println("2.Add a Hoarding");
		System.out.println("3.Show Hoardings");
		System.out.println("4 Export Hazard Report");
		System.out.println("0.Exit");
		int option = sc.nextInt();
		data.loadJSON();
		
		return option;
		

	}
	public void answerOption(int option) {
		switch(option) {
		case 1:
			System.out.println("Enter the route of the file");
			String path=sc.next();
			data.importHoardings(path);
			data.saveJSON();
			data.loadJSON();
			System.out.println("The File has been imported\n");
			break;
		case 2:
			addHoarding();
			data.saveJSON();
			data.loadJSON();
			System.out.println("\nThe hoarding has been added");
			break;
		case 3:
			System.out.println("W "+ "    H"+ "   inUse"+ "   Brand\n");
			data.showHoardings();
			break;
		case 4:
			data.report();
			break;
		}
	}
	public void addHoarding() {
		System.out.println("Enter the data separates for ++");
		String string=sc.next();
		String[]dataH = string.split("\\++");
		int height = Integer.parseInt(dataH[0]);
		int width = Integer.parseInt(dataH[1]);
		boolean state = false;
		if(dataH[2].equals("true")) {
			state=true;
		}
		data.add(height, width, state, dataH[3]);
	}
	
}
