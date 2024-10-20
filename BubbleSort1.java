public class BubbleSort1 {
    public static void main(String[] args) {
        int[] arr={6,4,5,7,8,1};

        for(int i=0;i<=arr.length-1;i++){
            for(int j=1;j<=arr.length-1-i;j++){
                if(arr[j]>arr[j-1]){
                    int temp=arr[j-1];
                    
                    arr[j-1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        for(int i=0;i<=arr.length-1;i++){
            System.out.println(arr[i]);
        }
    }
}
