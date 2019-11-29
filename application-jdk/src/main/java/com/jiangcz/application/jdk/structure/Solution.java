package com.jiangcz.application.jdk.structure;

import java.util.*;

/**
 * 类名称：Solution<br>
 * 类描述：<br>
 * 创建时间：2018年08月16日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Solution {
    /**
     * 栈的压入与弹出序列
     *
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     *
     * tip :这道题目主要采取的是模拟的思想来解决的。即通过创建一个栈，根据压入序列来压入数据，根据弹出序列来，弹出数据。如果最终整个栈为空，则弹出序列为正确的弹出序列，如果非空，则说明该弹出序列并不能将所有数据弹出。
     */
    public boolean isPopOrder(int [] pushA,int [] popA){
        Stack<Integer> stack = new Stack<>();
        if (pushA.length < 0){
            return false;
        }
        for (int i = 0 ,j = 0; i < pushA.length; ) {
            stack.push(pushA[i++]);//按照序列压入
            while (j < popA.length && stack.peek() == popA[j]) { //仅弹出与弹出序列相同的元素
                stack.pop();
                j++;//控制弹出序列前进
            }
        }
        return stack.isEmpty();
    }

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     *
     * 层序遍历二叉树
     *
     * 层序遍历二叉树，即从上往下打印二叉树，每层从左往右
     *
     * tip :用一个队列来存储每一层的节点，然后输出，完成层序遍历。因为队列的特点是先进先出，所以父节点会比子节点先进队列，也好久先输出父节点
     *
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll(); //获得第x层的根
            if (temp.left != null){
                queue.offer(temp.left);
            }
            if (temp.right != null){
                queue.offer(temp.right);
            }
            list.add(temp.val);
        }
        return list;
    }

    /**
     *
     * 二叉搜索树的后续遍历序列
     *
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同
     *
     * tip :二叉搜索树又叫二叉排序树。它的特点是，根节点左边的节点都小于根节点，根节点右边的节点都大于根节点，所以的子节点也遵循这个规律。
     *      知道这些，在解这道题目的时候，首先自己写一个二叉搜索树，将它的后续遍历序列写出来。会发现最后一个元素是根节点，而根节点会将前面的序列分为两部分，一部分全部小于根节点，一部分全部大于根节点，即两个序列为根节点的左右子树，左右子树也会遵序这个规律。
     *      所以就可以用递归的思想来不断深入，到最后叶子节点处，返回是否为二叉搜索树的后续遍历序列。
     *
     */
    public boolean verifySequanceOfBST(int [] sequence){
        if (sequence.length == 0 || sequence == null){
            return false;
        }
        return verifySequanceOfBST(sequence,0,sequence.length -1);
    }

    public boolean verifySequanceOfBST(int[] a,int f,int l){
        if (f >= l){
            return true;//递归终止
        }
        int i = l;
        while (i > f && a[i-1] > a[l]){
            i--;//找到子树分界点
        }
        for (int j = i-1;j >= f; --j){
            if (a[j] > a[l]){
                return false;
            }
        }
        return verifySequanceOfBST(a,f,i-1) && verifySequanceOfBST(a,i,l-1);//左右子树进入递归
    }

    /**
     *
     * 树的子结构
     *
     * 输入两个二叉树A，B判断B是不是A的子结构。
     *
     * tip :从根节点开始判断，如果A树的根节点和B树相同，再比较左右子节点，再以左右子节点为根节点进行递归，如果最终达到B的叶子节点，都没有不同的节点，则B是A的子结构。如果发现有不同节点，则回到A树，遍历到下一个节点，继续比较。
     *
     *
     */
    public boolean hasSubTree(TreeNode root1,TreeNode root2){
        boolean result = false;
        if (root1 != null && root2 != null){
            if (root1.val == root2.val){
                 result = isHave(root1,root2);
            }
            if (!result){
                result = isHave(root1.left,root2);
            }
            if (!result){
                result = isHave(root1.right,root2);
            }
        }
        return result;
    }
    public boolean isHave(TreeNode root1,TreeNode root2){
        if (root2 == null){
            return true;
        }
        if (root1 == null){
            return false;
        }
        if (root1.val != root2.val){
            return false;
        }
        return isHave(root1.left,root2.left) && isHave(root1.right,root2.right);
    }


    /**
     *
     * 全排列算法
     *
     * 全排列问题及给出一个序列如{a,b,c}输出它的全排列,一共有3!=6种排法.及一个n个元素的序列全排列有n!个排列方法.那么如何得到每种排列呢?
     *
     * tip :全排列问题是递归的一种典型问题.举个例子{1,2,3}的去全排列.首先我们会定第一个元素1不动,交换2,3的位置形成两种全排列,然后再让第一元素1与其他位置交换,重复上面的操作,由此得到整个序列的全排列.
     *      为什么会这样做呢?原因是其实上述步骤我们直接省略了一部,及在定下第一个元素后,我们再定下第二个元素,第三个元素则确定下来,因为单个元素的全排列是它自身,所以这个时候我们没有其他的排列方式了,就回到第二个元素,更换第二个元素{2,3},将第二个元素换成3,这时和上述情况一样,得到一种排序.
     *      我们在回想一下上述过程有有限推到无限.n个元素的全排列其实就是n*(n-1个元素的全排列),(这里的n的意思是,n次更换第一个元素)即
     *      p = {a1,a2,a3,a4….an},perm(p)为p的全排列则perm(p) = a1perm(p-a1)+a2perm(p-a2)…..+anperm(p-an)
     *
     * https://blog.csdn.net/yuwenhao07/article/details/71422829
     *
     */
    ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> permutation(String str){
        if (str.equals("")){
            return list;
        }
        char[] s = str.toCharArray();
        sort(s,0,s.length-1);
        Collections.sort(list);
        return list;
    }
    public void sort(char[] s,int first,int end){
        if (first >= end){
            list.add(String.valueOf(s));
        } else {
            for (int i = first;i <= end;i++ ){
                if (i == first || s[i] != s[first]){
                    swap(s,first,i);
                    sort(s,first+1,end);
                    swap(s,first,i);//恢复作用用于遍历
                }
            }
        }
    }
    public void swap(char[] s,int i,int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    /**
     *
     * 反转链表 **
     *
     * 反转链表,输入一个链表,输入该链表的反转
     *
     * tip :最基本的链表运算,但是牵扯了很多指针的变换
     *
     */
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val){
            this.val = val;
        }
    }
    public ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    /**
     *
     * 链表倒数第K个节点
     *
     * 输入一个链表，输出该链表中倒数第k个结点
     *
     * tip :t1，先遍历一遍得到这个链表的长度,然后第二次遍历得到第K个节点的;t2，设置两个指针,先让尾指针向后移动k-1个单位,然后两个指针同时移动,直到尾指针到链表的最后一个节点,那么头指针就指向的是第K个节点
     *      t2 以链表为底移动一个k长的模块一样
     *
     */
    public ListNode findKthToTail(ListNode head,int k){
        if (head == null||k <= 0){
            return null;
        }
        ListNode first = head;
        ListNode last = head;
        for (int i = 0;i < k-1 ; i++){
            if (last.next != null){
                last = last.next;
            } else {
                return null;
            }
        }
        while (last.next != null){
            first = first.next;
            last = last.next;
        }
        return first;
    }

    /**
     *
     * DP类题目汇总01
     *
     * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。n<=39.
     *
     * tip :斐波那契数列就是前两个数的和等于第三个数,一个典型的递归思想的数列,所以这道题也是用递归的思想来解决.但是只是递归的话,会产生很多的冗余,因为递归会把每种情况算很多遍,比如算n=5的时候,会把前面n=4.3.2.1所有的情况算一遍得到n=5,而在算n=4的时候,也会计算n=1.2.3的情况
     *      这样产生了巨大的冗余重复运算.所以这里使用了一个数组来记录每个情况的计算结果,就去除了冗余情况.
     *
     */

    public int fibonacci(int n){
        int[] result = new int[40];
        result[0] = 0;
        result[1] = 1;
        result[2] = 1;
        if (n > 0 && result[n] == 0){
            result[n] = fibonacci(n-1) + fibonacci(n-2);
        }
        return result[n];
    }

    /**
     *
     * DP类题目汇总02
     *
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     *
     * tip :这道题目依然是一个递归的问题,我们从后往前想,到达第N阶台阶有两种情况从n-1跳上来,和n-2跳上来.这个时候问题就可以分为达到第N-1阶,和第N-2阶.接下来技术递归调用.
     *
     */
    private static int[] result = new int[10000];
    public int jumpFloor(int target){
        result[0] = 0;
        result[1] = 1;
        result[2] = 2;
        if (target > 0 && result[target] == 0){
            result[target] = jumpFloor(target-1) + jumpFloor(target-2);
        }
        return result[target];
    }

    /**
     *
     * DP类题目汇总03
     *
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法
     *
     * tip :变态级就是把两种情况变成了n种情况.
     *
     */
    public int jumpFloor2(int target){
        result[0] = 1;
        result[1] = 1;
        result[2] = 2;
        if (target > 0 && result[target] == 0){
            for (int i = 0; i <= target; i++) {
                result[target] += jumpFloor2(target - i);
            }
        }
        return result[target];
    }


    /**
     *
     * 旋转数组中的最小数
     *
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     *
     * tip :从前往后遍历,找到后一个数字小于前一个数字.这个数字就是最小的那个数字.通过后发现这道题的时间复杂度是O(n),后来看了一下别人的思路发现这道题最好的解法是用二分搜索的思想.
     *      因为前一个序列肯定大于后一个序列的,所以如果中间点是大于前前序列的,说明最小数在后面序列,然后在在后面的序列进行二分搜索,直到两个指针相邻,这个时候找到最后的那个最小数
     *
     */
    public int minNumberInRotateArray(int [] array){
        if (array.length == 0){
            return 0;
        }
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        if (array[low] < array[high]){
            return array[low];
        }
        while (array[low] >= array[high]){
            if (high - low == 1){
                mid = high;
                break;
            }
            mid = low + (high - low)/2;
            if (array[low] <= array[mid]){
                low = mid;
            } else if (array[mid] <= array[high]){
                high = mid;
            }
        }
        return array[mid];
    }


    /**
     *
     * 两个栈实现队列
     *
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     *
     * tip :栈和队列的弹出序列是相反的这个两个数据结构的性质有关,一个是先进先出,一个是先进后出.所以如果用两个栈,相当于把数据的顺序倒了两次最后使得起顺序和队列一样,这是原理很简单,但是如何实现呢,这里需要注意的点有两个:
     *      1. 弹出栈不为空不能向里面压入数据
     *      2. 压入栈必须每次将所有的数据都压入弹出栈
     *
     */
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    public void push(int node){
        stack1.push(node);
    }
    public int pop(){
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     *
     * 重建二叉树
     *
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     *
     * tip :已知树的先序遍历和中序遍历,需要我们重建这个二叉树,其实大家都知道三种顺序遍历,我们只用只要其中两个就可以重建这个二叉树了.拿到这道题先分析一下,不用代码实现,我们自己如果要重建这个二叉树在纸上我们会怎么做.
     *      1. 找根节点
     *      2. 找出左右子树
     *      3. 在左右子树中继续找子树的根节点,直到节点为空,则二叉树重建完成
     *      首先怎么找根节点,这很简单,先序遍历的第一个节点就是根节点,如果是后续遍历就倒过来,最后一个是根节点.怎么找左右子树呢?在中序遍历中找到根节点的位置,然后根节点左边的序列就是左子树的中序遍历序列.右边同理.接下来就是一个不断的递归完成子问题的过程,在以子树的根节点为根继续向下
     *
     */
    public TreeNode reConstructBinaryTree(int[] pre,int[] in){
        TreeNode root = reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    private TreeNode reConstructBinaryTree(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
        if (preStart > preEnd || inStart > inEnd){
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        for (int i = inStart;i < inEnd; i++){
            if (in[i] == pre[preStart]){
                root.left = reConstructBinaryTree(pre,preStart+1,preStart+i-inStart,in,inStart,i-1);
                root.right = reConstructBinaryTree(pre,i-inStart+preStart+1,preEnd,in,i+1,inEnd);
            }
        }
        return root;
    }
}
