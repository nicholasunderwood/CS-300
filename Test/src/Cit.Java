class Result{
    public static void fizzBuzz(int n){
        String str = "";

        if(n%3 == 0){
            str += "Fizz";
        }

        if(n%5 == 0){
            str += "Buzz"
        }

        System.out.println(str == "" ? n : str);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 15; i++){
            fizzBuzz(i);
        }
    }
}