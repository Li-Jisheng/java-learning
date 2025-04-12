import java.util.Scanner;

//零钱通系统，有待完善，想要实现将收入和支出设计成账单类的子类，但是余额这一变量是一个需要各个对象共同使用的变量
//暂时没找到解决办法。
public class Main {

    public static void main (String[]args){
        boolean loop = true;
        do {
            System.out.println("================Меню================");
            System.out.println("\t\t\t\t1.Детали Счета");
            System.out.println("\t\t\t\t2.Доходы");
            System.out.println("\t\t\t\t3.Расходы");
            System.out.println("\t\t\t\t4.Выход");
            System.out.println("Вветите номер 1-4:");

            //Выберить функцию меню
            Scanner scanner = new Scanner(System.in);
            switch (scanner.next()) {

                case "1":
                    break;
                case "2":
                    break;
                case "3":

                    break;
                case "4":
                    System.out.println("Выход");
                    loop = false;
                    break;
                default:
                    System.out.println("Вы ввели неверное значение");
            }
        } while(loop);
    }
}