仅用递归函数逆序一个栈
public class ReverseStack {

    //逆序一个栈
    public void reverse(Stack<Integer> stack){
        if(stack.isEmpty()) return ;
        int num=getAndRemoveLast(stack);
        reverse(stack);
        stack.push(num);
    }
    //获取栈底元素并删除栈底元素
    private int getAndRemoveLast(Stack<Integer> stack){
        int result=stack.pop();
        if(stack.isEmpty()){
            return result;
        }
        int last=getAndRemoveLast(stack);
        stack.push(result);
        return last;
    }
}

滑动窗口：找出窗口中的最大值
public class SlidWindow {
    public int[] getMaxWindow(int[] arr,int k){
        int n=arr.length;
        int[] res=new int[n-k+1];
        int j=0;
        Deque<Integer> queue=new LinkedList<>();
        for(int i=0;i<n;i++){
            while(!queue.isEmpty()&&arr[queue.peekLast()]<=arr[i]){
                queue.pollLast();
            }
            queue.offerLast(i);
            if(!queue.isEmpty()&&queue.peekFirst()==i-k){
                queue.pollFirst();
            }
            if(!queue.isEmpty()&&i>=k-1){
                res[j++]=arr[queue.peekFirst()];
            }
        }
        return res;
    }
}


用一个栈实现另一个栈的排序
public class SortStackByStack {
    public  static void sortStack(Stack<Integer> stack){
        Stack<Integer> help=new Stack<>();
        while(!stack.isEmpty()){
            int cur=stack.pop();
            while(!help.isEmpty()&&help.peek()<cur){
                stack.push(help.pop());
            }
            help.push(cur);
        }

        while(!help.isEmpty()){
            stack.push(help.pop());
        }
    }
}

两个栈实现队列
public class TwoStacksQueue {
    Stack<Integer> pushStack;
    Stack<Integer> popStack;
    public TwoStacksQueue(){
        this.pushStack=new Stack<>();
        this.popStack=new Stack<>();
    }

    //入队
    public void offer(int val){
        pushStack.push(val);
    }

    //出队
    public int poll(){
        if(isEmpty()) return -1;
        if(popStack.isEmpty()){
            while(!pushStack.isEmpty()){
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }

    //获取队头元素
    public int peek(){
        if(isEmpty()) return -1;
        if(popStack.isEmpty()){
            while(!pushStack.isEmpty()){
                popStack.push(pushStack.pop());
            }
        }
        return popStack.peek();
    }

    //队列是否为空
    public boolean isEmpty(){
        return pushStack.isEmpty()&&popStack.isEmpty();
    }
}

