public class Main {


    public static void main(String[] args) {
        String exp1 = "ВаСиЛийедет";
        String exp2 = "дмитриЙ едет задом";
        String exp3 = "макСим  едет";

        String exp4 = "ABCD";
        String exp5 = "агент007";

        String regex = "([а-я]*\\h*)*[А-Я]?\\h*([а-я]*\\h*)*";
        String regex2 = "([а-яА-Я]\\h*)+";
//        String regex2 = "^([\\p{Lu}\\p{Lt}][\\p{Ll}\\p{Lm}\\p{Lo}]+)+$";



        boolean newExp = exp5.matches(regex);

        System.out.println(newExp);

    }
}
