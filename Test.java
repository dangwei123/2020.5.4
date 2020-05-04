猫狗队列
 *
 *     add方法：将cat类或dog类的实例放入队列中
 *     pollAll方法：将队列中所有的实例按照进队列的先后顺序弹出
 *     pollDog方法：将队列中的dog类的实例按照进队列的先后顺序弹出
 *     pollCat方法：将队列中的Cat类的实例按照进队列的先后顺序弹出
 *     isEmpty方法：队列是否还有cat类或dog类的实例
 *     isDogEmpty方法：队列是否还有dog类的实例
 *     isCatEmpty方法：队列是否还有cat类的实例
 public class DogCatQueue {
    private static class Node{
        private Pet pet;
        private long time;
        public Node(Pet pet,long time){
            this.pet=pet;
            this.time=time;
        }
    }

    private Queue<Node> dogQueue;
    private Queue<Node> catQueue;
    private long time;
    public DogCatQueue(){
        dogQueue=new LinkedList<>();
        catQueue=new LinkedList<>();
        this.time=0;
    }

    public void add(Pet pet){
        Node node=new Node(pet,time++);
        if("dog".equals(pet.getType())){
            dogQueue.offer(node);
        }else if("cat".equals(pet.getType())){
            catQueue.offer(node);
        }
    }

    public List<Pet> pollAll(){
        List<Pet> list=new ArrayList<>();
        while(!dogQueue.isEmpty()&&!catQueue.isEmpty()){
            if(dogQueue.peek().time<catQueue.peek().time){
                list.add(dogQueue.poll().pet);
            }else{
                list.add(catQueue.poll().pet);
            }
        }
        while(!dogQueue.isEmpty()){
            list.add(dogQueue.poll().pet);
        }
        while(!catQueue.isEmpty()){
            list.add(catQueue.peek().pet);
        }
        return list;
    }

    public List<Pet> pollDog(){
        List<Pet> list=new ArrayList<>();
        while(!dogQueue.isEmpty()){
            list.add(dogQueue.poll().pet);
        }
        return list;
    }

    public List<Pet> pollCat(){
        List<Pet> list=new ArrayList<>();
        while(!catQueue.isEmpty()){
            list.add(catQueue.peek().pet);
        }
        return list;
    }

    public boolean isEmpty(){
        return dogQueue.isEmpty()&&catQueue.isEmpty();
    }

    public boolean isDogEmpty(){
        return dogQueue.isEmpty();
    }

    public boolean isCatEmpty(){
        return catQueue.isEmpty();
    }
}
class Pet{
    private String type;
    public Pet(String type){
        this.type=type;
    }
    public String getType(){
        return this.type;
    }
}

class Dog extends Pet{

    public Dog(){
        super("dog");
    }
}

class Cat extends Pet{

    public Cat() {
        super("cat");
    }
}


最大值减去最小值小于等于num的子数组个数
public class GetNum {
    public int getNum(int[] arr,int num){
        Deque<Integer> max=new LinkedList<>();
        Deque<Integer> min=new LinkedList<>();
        int j=0;
        int res=0;
        for(int i=0;i<arr.length;i++){
            while(j<arr.length){
                while(!max.isEmpty()&&arr[max.peekLast()]<=arr[j]){
                    max.pollLast();
                }
                max.offerLast(j);
                while(!min.isEmpty()&&arr[min.peekLast()]>=arr[j]){
                    min.pollLast();
                }
                min.offerLast(j);
                if(!max.isEmpty()&&!min.isEmpty()&&arr[max.peekFirst()]-arr[min.peekFirst()]>num) {
                    break;
                }
                j++;
            }

            res+=j-i;
            if(!max.isEmpty()&&max.peekFirst()==i) max.pollFirst();
            if(!min.isEmpty()&&min.peekFirst()==i) min.pollFirst();

        }
        return res;
    }
}


升级版汉诺塔：不能直接从左移动到右，也不能直接从右移动到左
//方法一：递归
public class HanoiProblem {
    public static void main(String[] args) {
        System.out.println(hanoi(2,"A","B","C","A","C"));
    }
    public static int hanoi(int num,String left,String mid,String right,String from,String to){
        if(num==1){
            if(from.equals(mid)||to.equals(mid)){
                System.out.println("move 1 from "+from+" to "+to);
                return 1;
            }else{
                System.out.println("move 1 from "+from+" to "+mid);
                System.out.println("move 1 from "+mid+" to "+to);
                return 2;
            }
        }

        if(from.equals(mid)||to.equals(mid)){
            String another=(from.equals(left)||to.equals(left))?right:left;
            int a=hanoi(num-1,left,mid,right,from,another);
            System.out.println("move "+num+" from "+from+" to "+to);
            int b=1;
            int c=hanoi(num-1,left,mid,right,another,to);
            return a+b+c;
        }else{
            int a=hanoi(num-1,left,mid,right,from,to);
            System.out.println("move "+num+" from "+from+" to "+mid);
            int b=1;
            int c=hanoi(num-1,left,mid,right,to,from);
            System.out.println("move "+num+" from "+mid+" to "+to);
            int d=1;
            int e=hanoi(num-1,left,mid,right,from,to);
            return a+b+c+d+e;
        }
    }
}


//方法二：非递归
class HanoiProblem1{
    public static void main(String[] args) {
        System.out.println(hanoi(2,"A","B","C"));
    }
    public enum Action{No,LTOM,MTOL,MTOR,RTOM};
    public static int hanoi(int num,String left,String mid,String right){
        Stack<Integer> leftStack=new Stack<>();
        Stack<Integer> midStack=new Stack<>();
        Stack<Integer> rightStack=new Stack<>();
        leftStack.push(Integer.MAX_VALUE);
        midStack.push(Integer.MAX_VALUE);
        rightStack.push(Integer.MAX_VALUE);
        for(int i=num;i>0;i--){
            leftStack.push(i);
        }
        int step=0;
        Action[] record= {Action.No};
        while(rightStack.size()!=num+1){
            step+=func(record,Action.MTOL,Action.LTOM,leftStack,midStack,"A","B");
            step+=func(record,Action.LTOM,Action.MTOL,midStack,leftStack,"B","A");
            step+=func(record,Action.RTOM,Action.MTOR,midStack,rightStack,"B","C");
            step+=func(record,Action.MTOR,Action.RTOM,rightStack,midStack,"C","B");
        }
        return step;
    }

    private static int func(Action[] record,Action pre,Action cur,Stack<Integer> fromStack,Stack<Integer> toStack,
                     String from,String to){
        if(record[0]!=pre&&fromStack.peek()<toStack.peek()){
            toStack.push(fromStack.pop());
            System.out.println("move "+toStack.peek()+" from "+from+" to "+to);
            record[0]=cur;
            return 1;
        }
        return 0;
    }
}
