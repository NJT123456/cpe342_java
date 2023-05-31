package quicksort;

import java.util.Arrays;

public class quicksort extends Thread{
	
	public int[] arr;
	public int left, right;
	
	public quicksort(int[] arr, int left, int right) {
		this.arr = arr;
		this.left = left;
		this.right = right;
	}
	// ref https://www.geeksforgeeks.org/quick-sort/
	public int partition(int[] arr, int left, int right) {
		// กำหนด pivot เป็นตำแหน่งสุดท่ายของทางด้านขวา
		int pivot = arr[right];
		// เป็นตัวเก็บตำแหน่งที่แบ่งข้อมูล
		int i = left - 1;
		
		for (int j = left; j < right; j++) {
			if(arr[j] < pivot) {
				i++;
				int swaptemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swaptemp;
			}
		}
		int swaptemp = arr[i+1];
		arr[i+1] = arr[right];
		arr[right] = swaptemp;

		return i + 1;
	}
	
	public void quicksortThread(int[] arr, int left, int right) {
		if(left < right) {
			int pivot = partition(arr, left, right);
			
			// สร้างและเริ่ม thread เพื่อเรียงด้านขวา
			quicksort rightThread = new quicksort(arr, pivot + 1, right);
			rightThread.start();
			
			//เรียงด้านซ้าย
			quicksortThread(arr, left, pivot - 1);
			
			try {
				rightThread.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	public void run() {
		quicksortThread(arr,left,right);
	}
	
	public static void main(String args[]) throws InterruptedException{
		
		int[] arr = new int[10];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*10) + 1;
		}
		System.out.println("before sorting:");
		System.out.println(Arrays.toString(arr));
		int n = arr.length;
		quicksort t = new quicksort(arr,0, n-1);
		t.start();
		
		t.join();
		System.out.println("after sorting:");
		System.out.println(Arrays.toString(arr));
	}
	
	

}
