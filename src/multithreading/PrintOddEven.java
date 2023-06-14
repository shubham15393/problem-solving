package multithreading;

//print numbers from 1 to n using two threads one thread prints odd number and another thread prints even number
public class PrintOddEven {


    static int counter =1;
    static int N;
      void printOddNumber(){
        synchronized (this) {
            while(counter<N) {
                while (counter % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(Thread.currentThread().getName() +" : " + counter);
                counter++;
                notify();
            }

        }
    }
      void printEvenNumber(){

          synchronized (this) {
              while(counter<N) {
                  while (counter % 2 != 0) {
                      try {
                          wait();
                      } catch (InterruptedException e) {
                          throw new RuntimeException(e);
                      }
                  }
                  System.out.println(Thread.currentThread().getName() +" : " + counter);
                  counter++;
                  notify();
              }
          }
    }


    public static void main(String[] args) {
          N=100;
          PrintOddEven obj = new PrintOddEven();
        Thread oddThread = new Thread(()->{
            obj.printOddNumber();
        });
        Thread evenThread = new Thread(()->{
            obj.printEvenNumber();
        });

        oddThread.start();
        evenThread.start();
    }
}
