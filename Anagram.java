class Anagram {

    public static void main(String[] args) {

        String str = "cars";
        String str2 = "race";
        boolean isAnagram = true;
        int count1[] = new int[256]; 
        int count2[] = new int[256]; 

        
        if (str.length() != str2.length()) {
            System.out.println("false");
            return; 
        }

       
        for (int i = 0; i < str.length(); i++) {
            count1[str.charAt(i)]++;
            count2[str2.charAt(i)]++;
        }

    
        for (int i = 0; i < 256; i++) {
            if (count1[i] != count2[i]) {
                isAnagram = false;
                break; 
            }
        }

       
        if (isAnagram) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
