import java.io.*;
class Thread1
{
int n;
boolean valueset = false;
synchronized int get()
{
if(!valueset)
try
{
wait();
}
catch(Exception e)
{
System.out.println("Exception occur at : "+e);
}
System.out.println("GET:"+n);
try
{
Thread.sleep(0);
}
catch(Exception e)
{
System.out.println("Exception occur at : "+e);
}
valueset=false;
notify();
return n;
}
synchronized int put(int n)
{
if(valueset)
{
try
{
wait();
}
catch(Exception e)
{
System.out.println("Exception occur at : "+e);
}
}
this.n=n;
valueset=true;
System.out.println("PUT:"+n);
try
{
Thread.sleep(0);
}
catch(Exception e)
{
System.out.println("Exception occur at : "+e);
}
notify();
return n;
  class Producer implements Runnable {
Thread1 t;
Producer(Thread1 t) {
this.t=t;
new Thread(this,"Producer").start();
}
public void run() {
int i=0,j=0;
while(j<=5) {
t.put(i++);
j++;
}
}
}
class Consumer implements Runnable {
Thread1 t;
Consumer(Thread1 t) {
this.t=t;
new Thread(this,"Consumer").start();
}
public void run() {
int i=0,j=0;
while(j<=5) {
t.get();
j++;
}
}
}
class ProdCons {
public static void main(String args[]) throws IOException {
Thread1 t =new Thread1();
new Producer(t);
new Consumer(t);
}
}

