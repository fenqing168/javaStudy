package binay_tree.cn.fenqing168.study.code.test;

import binay_tree.cn.fenqing168.study.code.bean.Node;
import binay_tree.cn.fenqing168.study.code.definite.LinkedBinaryTree;
import binay_tree.cn.fenqing168.study.code.interfaces.BinaryTree;

public class LinkedBinaryTreeTest {

    public static void main(String[] args) {

        //创建一个二叉树
        Node node5 = new Node(5, null, null);
        Node node4 = new Node(4, null, node5);
        Node node3 = new Node(3, null, null);
        Node node7 = new Node(7, null, null);
        Node node6 = new Node(6, null, node7);
        Node node2 = new Node(2, node3, node6);
        Node node1 = new Node(1, node4, node2);
        BinaryTree binaryTree = new LinkedBinaryTree(node1);

        //二叉树是否为空树
        System.out.println("二叉树是否为空树：" + binaryTree.isEmpty());

        //先序遍历
        binaryTree.preOrderTraverse();

        //中序遍历
        binaryTree.inOrderTraverse();

        //后序遍历
        binaryTree.postOrderTraverse();

        //树的高度
        System.out.println("树的高度：" + binaryTree.getHeight());

        //树的元素的个数
        System.out.println("树的大小：" + binaryTree.size());

        //在树中找为某一个值为指定值的节点
        System.out.println("在树中找为某一个值为7的节点:" + binaryTree.findKey(7));

        //层次遍历
        binaryTree.levelOrderByStack();

        //非递归中序遍历
        binaryTree.inOrderByStack();

        //非递归先序遍历
        binaryTree.preOrderByStack();

        //非递归后序排序
        binaryTree.postOrderByStack();
    }
}
