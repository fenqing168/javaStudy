package binay_tree.cn.fenqing168.study.code.definite;

import binay_tree.cn.fenqing168.study.code.bean.Node;
import binay_tree.cn.fenqing168.study.code.interfaces.BinaryTree;

import java.util.*;

public class LinkedBinaryTree implements BinaryTree {

    private Node root;

    public LinkedBinaryTree() {
    }

    public LinkedBinaryTree(Node root) {
        this.root = root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    /**
     * 辅助树的大小递归方法
     * @return
     */
    private int size(Node node) {
        if(node == null){
            return 0;
        }
        return size(node.getLeftChilden()) + size(node.getRightChilden()) + 1;
    }


    @Override
    public int getHeight() {
        //以获取开始节点开始遍历
        return getHeight(root);
    }

    /**
     * 辅助递归的高度方法
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        if(node == null){
            return 0;
        }
        return Math.max(
                getHeight(node.getLeftChilden()),
                getHeight(node.getRightChilden())
        ) + 1;
    }

    @Override
    public Node findKey(Object value) {
        //开始遍历查找并返回
        return findKey(value, root);
    }

    /**
     * 辅助查找值的递归方法
     * @param value
     * @param node
     * @return
     */
    private Node findKey(Object value, Node node) {
        //如果分支到叶子还未发现发现元素返回null
        if(node == null){
            return node;
        }else if(node.getValue() != null && node.getValue() == value){//如果当前节点是目标节点，直接返回
            return node;
        }else{//如果当前节点不是最后节点，继续往孩子节点找，找到就返回，未找到返回null
            Node left = findKey(value, node.getLeftChilden());
            Node right = findKey(value, node.getRightChilden());
            if(left != null){
                return left;
            }
            if(right != null){
                return right;
            }
        }
        return null;
    }


    @Override
    public void preOrderTraverse() {
        System.out.println("先序遍历：");
        //以树的根为起始点开始递归
        preOrderTraverse(root);
        System.out.println();
    }

    /**
     * 辅助递归使用的先序遍历
     * @param root
     */
    private void preOrderTraverse(Node root){
        //当前节点为null则树的此枝遍历结束
        if(root == null){
            return;
        }
        //输出当前节点的值
        System.out.print(root.getValue() + " ");
        //遍历左子树
        preOrderTraverse(root.getLeftChilden());
        //遍历右子树
        preOrderTraverse(root.getRightChilden());

    }

    @Override
    public void inOrderTraverse() {
        System.out.println("中序遍历：");
        //以树的根为起始点开始递归
        inOrderTraverse(root);
        System.out.println();
    }

    /**
     * 辅助递归使用的中序遍历
     * @param root
     */
    private void inOrderTraverse(Node root){
        //当前节点为null则树的此枝遍历结束
        if(root == null){
            return;
        }
        //遍历左子树
        inOrderTraverse(root.getLeftChilden());
        //输出当前节点的值
        System.out.print(root.getValue() + " ");
        //遍历右子树
        inOrderTraverse(root.getRightChilden());
    }


    @Override
    public void postOrderTraverse() {
        System.out.println("后序遍历：");
        //以树的根为起始点开始递归
        postOrderTraverse(root);
        System.out.println();
    }

    @Override
    public void postOrderTraverse(Node node) {
        //当前节点为null则树的此枝遍历结束
        if(node == null){
            return;
        }
        //遍历左子树
        postOrderTraverse(node.getLeftChilden());
        //遍历右子树
        postOrderTraverse(node.getRightChilden());
        //输出当前节点的值
        System.out.print(node.getValue() + " ");
    }

    @Override
    public void inOrderByStack() {
        System.out.println("非递归中序遍历：");
        //准备栈对象和当前遍历的对象
        Deque<Node> stack = new LinkedList<>();
        Node temp = root;
        //如果栈里面或者当前遍历节点任意一个非空，继续操作
        while(temp != null || !stack.isEmpty()){
            //沿着节点一直将左孩子入栈，并将当前遍历节点（保留对于下一次循环时为上一个节点）赋值给temp,直到没有左孩子
            while(temp != null){
                stack.push(temp);
                temp = temp.getLeftChilden();
            }
            //将一路的左孩子全部入完栈之后，将左孩子出栈并输出值，将上一个节点的右孩子赋值给temp
            if(!stack.isEmpty()){
                temp = stack.poll();
                System.out.print(temp.getValue() + " ");
                temp = temp.getRightChilden();
            }
        }
        System.out.println();

    }

    @Override
    public void preOrderByStack() {
        System.out.println("非递归先序排序：");
        Deque<Node> stack = new LinkedList<>();
        Node temp;
        stack.push(root);
        while(!stack.isEmpty()){
            temp = stack.poll();
            System.out.print(temp.getValue() + " ");
            if(temp.getRightChilden() != null){
                stack.push(temp.getRightChilden());
            }
            if(temp.getLeftChilden() != null){
                stack.push(temp.getLeftChilden());
            }
        }
        System.out.println();
    }

    @Override
    public void postOrderByStack() {
        System.out.println("非递归后序排序：");
        if(root == null){
            return;
        }
        Deque<Node> stack = new LinkedList<>();
        List<Node> list = new ArrayList<>();
        Node temp;
        stack.push(root);
        while(!stack.isEmpty()){

            temp = stack.peek();
            if(list.contains(temp.getRightChilden()) || list.contains(temp.getLeftChilden())){
                System.out.print(temp.getValue() + " ");
                list.add(temp);
                stack.poll();
                continue;
            }
            if(temp.getRightChilden() != null){
                stack.push(temp.getRightChilden());
            }
            if(temp.getLeftChilden() != null){
                stack.push(temp.getLeftChilden());
            }
            if(temp.getRightChilden() == null && temp.getLeftChilden() == null){
                System.out.print(temp.getValue() + " ");
                list.add(temp);
                stack.poll();
            }
        }
    }

    @Override
    public void levelOrderByStack() {
        System.out.println("层次遍历(借助队列，先进先出):");
        Queue<Node> queue = new LinkedList<>();
        //先将根入队
        queue.add(root);
        //队列里面如果还有待遍历的节点，则继续
        while(queue.size() > 0){
            //遍历队列里面的节点，并依次弹出，输出里面的值，如果有孩子，在依次入队
            Node tmp;
            int len = queue.size();
            for(int i = 0; i < len; i++){
                tmp = queue.poll();
                System.out.print( tmp.getValue()+ " ");
                if(tmp.getLeftChilden() != null){
                    queue.add(tmp.getLeftChilden());
                }
                if(tmp.getRightChilden() != null){
                    queue.add(tmp.getRightChilden());
                }
            }
        }
        System.out.println();
    }
}
