求最大子矩阵大小
public class MatrixSize {
    public int getMatrixSize(int[][] matrix){
        int maxArea=0;
        int m=matrix.length;
        if(m==0) return 0;
        int n=matrix[0].length;
        int[] height=new int[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                height[j]=matrix[i][j]==1?height[j]+1:0;
            }
            maxArea=Math.max(maxArea,getMax(height));
        }
        return maxArea;
    }

    private int getMax(int[] height){
        Stack<Integer> stack=new Stack<>();
        int max=0;
        for(int i=0;i<height.length;i++){
            while(!stack.isEmpty()&&height[stack.peek()]>=height[i]){
                int j=stack.pop();
                int left=stack.isEmpty()?-1:stack.peek();
                max=Math.max(max,(i-left-1)*height[j]);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int j=stack.pop();
            int left=stack.isEmpty()?-1:stack.peek();
            max=Math.max(max,(height.length-left-1)*height[j]);
        }
        return max;
    }
}


构造数组的MaxTree
public class MaxTree {
    private static class Node{
        private int val;
        private Node left;
        private Node right;
        public Node(int val){
            this.val=val;
        }
    }

    public Node getMaxTree(int[] arr){
        Map<Node,Node> leftBig=new HashMap<>();
        Map<Node,Node> rightBig=new HashMap<>();
        Stack<Node> stack=new Stack<>();
        for(int i=0;i<arr.length;i++){
            Node node=new Node(arr[i]);
            while(!stack.isEmpty()&&stack.peek().val<arr[i]){
                Node cur=stack.pop();
                if(stack.isEmpty()) leftBig.put(cur,null);
                else leftBig.put(cur,stack.peek());
            }
            stack.push(node);
        }
        while(!stack.isEmpty()){
            Node cur=stack.pop();
            if(stack.isEmpty()) leftBig.put(cur,null);
            else leftBig.put(cur,stack.peek());
        }

        for(int i=arr.length-1;i>=0;i--){
            Node node=new Node(arr[i]);
            while(!stack.isEmpty()&&stack.peek().val<arr[i]){
                Node cur=stack.pop();
                if(stack.isEmpty()) rightBig.put(cur,null);
                else rightBig.put(cur,stack.peek());
            }
            stack.push(node);
        }
        while(!stack.isEmpty()){
            Node cur=stack.pop();
            if(stack.isEmpty()) rightBig.put(cur,null);
            else rightBig.put(cur,stack.peek());
        }

        Node root=null;
        for(int i=0;i<arr.length;i++){
            Node node=new Node(arr[i]);
            Node left=leftBig.get(node);
            Node right=rightBig.get(node);
            if(left==null&&right==null){
                root=node;
            }else if(left==null){
                if(right.left==null) right.left=node;
                else right.right=node;
            }else if(right==null){
                if(left.left==null) left.left=node;
                else left.right=node;
            }else{
                Node parent=left.val<right.val?left:right;
                if(parent.left==null) parent.left=node;
                else parent.right=node;
            }
        }
        return root;
    }
}


最小栈
//方案一：两个栈
public class MinStack {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;
    public MinStack(){
        dataStack=new Stack<>();
        minStack=new Stack<>();
    }
    //入栈
    public void push(int val){
        if(dataStack.isEmpty()){
            dataStack.push(val);
            minStack.push(val);
        }else{
            dataStack.push(val);
            if(val<=minStack.peek()) minStack.push(val);
        }
    }

    //出栈
    public int pop(){
        if(dataStack.isEmpty()) return -1;
        int key=dataStack.pop();
        if(minStack.peek()==key) minStack.pop();
        return key;
    }

    //获取最小元素
    public int getMin(){
        if(minStack.isEmpty()) return -1;
        return minStack.peek();
    }
}



//方案二:节点类
class MinStack1{
    private static class Node{
        private int val;
        private int min;
        public Node(int val,int min){
            this.val=val;
            this.min=min;
        }
    }
    private Stack<Node> stack;
    public MinStack1(){
        stack=new Stack<>();
    }

    //入栈
    public void push(int val){
        Node node=new Node(val,val);
        if(!stack.isEmpty()){
            if(stack.peek().min<val){
                node.min=stack.peek().min;
            }
        }
        stack.push(node);
    }

    //出栈
    public int pop(){
        if(stack.isEmpty()) return -1;
        return stack.peek().val;
    }

    //获取最小元素
    public int getMin(){
        if(stack.isEmpty()) return -1;
        return stack.peek().min;
    }
}


