package com.example.Algo.kthElement;

/*public class kthElement {

    public static int kthElem(int[] arr, int start, int end, int k) {

        //todo validate here...
        if(start==end) {

            return arr[start];
        }
        int pivot = partition(arr, start,  end);

        int length = pivot-start +1;
        if(length == k) {
            return arr[pivot];
        }
        if(pivot < k) {

            return kthElem(arr,pivot+1, end, k);
        }
        else {
            return kthElem(arr, start, pivot-1, k);

        }
    }

    public static int partition(int[] arr, int start, int end){
        int pivot = start;
        int pivotValue = arr[start];

        int i=pivot+1;
        int j = end;

        while(i<j){
            while(arr[i] < pivotValue ){
                i++;
            }
            while(arr[j] > pivotValue){
                j--;
            }
            if(i<j) {//swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

    }

    public static void main(String[] args) {

        int[] arr = {2, 5, 8, 10, 4, 6, 1};
        int k = kthElem(arr, 0, 6, 3);
        System.out.println(String.format("3-th elem is %s",k));
        System.exit(0);
    }
}*/
